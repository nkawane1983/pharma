package com.avee.controller;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BranchDetails;

import com.avee.service.BranchService;
import com.avee.service.GroupService;
import com.avee.service.LookingService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.BranchValidator;

@Controller
@RequestMapping(value = "/branch")
public class BranchController {

	static final Logger logger = LogManager.getLogger(BranchController.class.getName());
	
	@Autowired
	BranchService branchService;

	@Autowired
	private StringUtility strUtility;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	LookingService lookingService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
	    binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
	    binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
	    binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	}

	/*
	 * Display BranchDetails Search Page
	 */
	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" Branch SearchPg : start ");
		Map<String, String> searchMap = new HashMap<>();

		AppUser usr = (AppUser) session.getAttribute("userInfo");
		String branchName = request.getParameter("branchName");
		String groupname = request.getParameter("groupname");
		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		List<AppUserGroupBranchMapping> branchDetails = new ArrayList<>();

		if (strUtility.checkNullOrEmptyString(branchName) || strUtility.checkNullOrEmptyString(groupname)) {
			searchMap.put("branchName", branchName);
			searchMap.put("groupname", groupname);
		}
		searchMap.put("uid", usr.getUsrId());
		searchMap.put("groupId", "0");
		searchMap.put("branchID", "0");
		branchDetails = branchService.getBranchDetailsaAsList(searchMap);
		model.addAttribute("branchDetailsList", branchDetails);
		model.addAttribute("countrycode", lookingService.getAllCountryAsList());

		logger.info(" Branch SearchPg : end ");
		return "branchSearch";
	}

	/*
	 * Add New BranchDetails Page
	 * 
	 */
	@RequestMapping("/newBranchDetails")
	public String newBranchDetails(@ModelAttribute("branchDetails") BranchDetails branchDetails,
			HttpServletRequest request, Model model, HttpSession session) {
		logger.info(" newBranchDetails : start ");
		Map<String, String> searchMap = new HashMap<>();
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		model.addAttribute("groupList", groupService.searchGroupDetails(searchMap));
		model.addAttribute("groupUserAssocs", session.getAttribute("groupUserAssocs"));
		model.addAttribute("countrycode", lookingService.getAllCountryAsList());
		logger.info(" newBranchDetails : end ");
		return "manageBranch";
	}

	/*
	 * Save New BranchDetails Page
	 * 
	 */
	@RequestMapping(value = "/manageBranchDetails", params = "add")
	public String insertBranch(@ModelAttribute("branchDetails") BranchDetails branchDetails, Model model,
			BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("insertBranch- start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			branchDetails.setCreatedBy(usr.getUsrName());
			branchDetails.setCreatedDt(Calendar.getInstance().getTime());
			branchDetails.setUpdatedDt(Calendar.getInstance().getTime());
			Calendar cal = Calendar.getInstance();
			branchDetails.setPeriod(cal.get(Calendar.MONTH) + 1);
			branchDetails.setStartDate(cal.getTime());
			BranchValidator validator = new BranchValidator();
			validator.validate(branchDetails, result);

			if (result.hasErrors()) {
				return "manageBranch";
			} else {
				// save BranchDetails
				String successMessage = branchService.insertBranchDetails(branchDetails);
				if (strUtility.checkNullOrEmptyString(successMessage)) {
					redirectAttributes.addFlashAttribute("successMessage", successMessage);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("insertBranch - end");
		return "redirect:searchPg";
	}

	/*
	 * view/Edit BranchDetails Page
	 */
	@RequestMapping("/editBranch")
	public String editBranchDetails(Model model, HttpServletRequest request, HttpSession session) {

		logger.info("editBranch - start");
		Map<String, String> searchMap = new HashMap<>();
		BranchDetails branchDetails = new BranchDetails();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String id = request.getParameter("id");
			if (strUtility.checkNullOrEmptyString(id)) {
				// get branchDetails by Id
				branchDetails = branchService.getBranchDetails(Integer.parseInt(id));
				model.addAttribute("groupList", groupService.searchGroupDetails(searchMap));
				model.addAttribute("countrycode", lookingService.getAllCountryAsList());
			}
			
			if (branchDetails == null) {
				branchDetails = new BranchDetails();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("branchDetails", branchDetails);

		model.addAttribute("mode", "update");
		logger.info("editBranch - end");
		return "manageBranch";
	}

	/*
	 * update BranchDetails Page
	 */
	@RequestMapping(value = "/manageBranchDetails", params = "edit")
	public String updateBranch(@ModelAttribute("branchDetails") BranchDetails branchDetails, Model model,
			BindingResult result, RedirectAttributes redirectAttributes) {
		logger.info("updateBranch - start");
		try {

			BranchValidator validator = new BranchValidator();
			validator.validate(branchDetails, result);

			if (result.hasErrors()) {
				return "manageBranch";
			} else {
				// update branchDetails by Id
				branchService.updateBranchDetails(branchDetails,"update");
				redirectAttributes.addFlashAttribute("successMessage", "Branch update successfully.");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Branch did not update.");
			e.printStackTrace();
		}
		logger.info("updateBranch - end");
		return "redirect:searchPg";
	}

	/*
	 * delete BranchDetails Page
	 */
	@RequestMapping(value = "/deleteBranch")
	public String deleteBranchDetails(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		logger.info("deleteBranch - start");

		try {
			BranchDetails branchDetails =new BranchDetails();
			String id = request.getParameter("id");
			if (strUtility.checkNullOrEmptyString(id)) {
				// delete branchDetails by Id
				branchDetails.setId(Integer.parseInt(id));
				branchService.updateBranchDetails(branchDetails,"delete");
				redirectAttributes.addFlashAttribute("successMessage", "Branch Delete successfully.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Branch did not Delete.");
		}

		logger.info("deleteBranch - end");
		return "redirect:searchPg";
	}

	@RequestMapping(value = "/getBranches")
	public @ResponseBody String getBranchesByAjax(Model model, @RequestParam("id") int id) {
		logger.info("in getBranche - start");
		StringWriter writer = null;
		try {
			// Map<String, String> searchMap = new HashMap<>();
			List<BranchDetails> branchList = new ArrayList<>();
			// System.out.println(id);

			branchList = branchService.getBranchDetailsList(id);
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("branchlist", branchList);
			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/BranchList.vm", "utf-8", context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out getBranche - start");
		return writer == null ? "" : writer.toString();
	}
	@RequestMapping(value = "/getBranchesList")
	public @ResponseBody String getBranchesListByAjax(Model model, @RequestParam("id") String id,HttpSession session) {
		logger.info("in getBranchesList - start");
		StringWriter writer = null;
		try {
			List<BranchDetails> branchList = new ArrayList<>();
			AppUser usr = (AppUser) session.getAttribute("userInfo"); 
			branchList = branchService.getBranchDetailsASList(id,"groupId",usr.getUsrId(),"dropdown");
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("branchlist", branchList);
			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/BranchListAsReport.vm", "utf-8", context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out getBranchesList - start");
		return writer == null ? "" : writer.toString();
	}
	@RequestMapping(value = "/checkBranchWorkingDayOrNo")
	public @ResponseBody String checkBranchWorkingDayOrNoByAjax(Model model, @RequestParam("eventDate") String eventDate,HttpSession session) {
		logger.info("in checkBranchWorkingDayOrNo - start");
		String writer = null;
		boolean val=false;
		Map<String, String> searchMap = new HashMap<>();
		try {
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			String eventate=strUtility.chageDateFormat(eventDate);
			searchMap.put("eventdate", eventate);
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			val=branchService.checkBranchWorkingDayOrNo(searchMap);
			writer=String.valueOf(val);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out checkBranchWorkingDayOrNo - start");
		return writer == null ? "" : writer.toString();
	}


}
