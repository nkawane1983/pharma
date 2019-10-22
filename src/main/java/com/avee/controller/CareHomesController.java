package com.avee.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.BranchDetails;
import com.avee.form.CareHome;
import com.avee.service.BranchService;
import com.avee.service.CareHomeService;
import com.avee.service.LookingService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.CareHomeValidator;

@Controller
@RequestMapping(value = "/careHomes")
public class CareHomesController {

	static final Logger logger = LogManager.getLogger(CareHomesController.class.getName());
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	CareHomeService careHomeService;
	
	@Autowired
	private StringUtility strUtility;
	
	@Autowired
	LookingService lookingService;
	/*
	 * Display Care Homes Search Page
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
	    binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
	    binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
	    binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	}
	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpServletRequest request,HttpSession session) {
		logger.info(" care homes SearchPg : start ");
		model.addAttribute("branchDetailsList", branchService.getBranchDetailsList(0));
		String branchId = request.getParameter("branchId");
		String cname = request.getParameter("carehomename");
		Map<String, String> searchMap = new HashMap<>();
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		if (strUtility.checkNullOrEmptyString(branchId) || strUtility.checkNullOrEmptyString(cname)) {
			searchMap.put("branchId", branchId);
			searchMap.put("cname", cname);

		} else {
			searchMap.put("branchId", "0");
			searchMap.put("cname", "");
		}
		model.addAttribute("carehomelist", careHomeService.searchCareHome(searchMap));
		logger.info(" care homes SearchPg : end ");
		return "careHomesSearch";
	}

	@RequestMapping("/newCareHomePg")
	public String newCareHomePg(Model model, HttpServletRequest request,HttpSession session) {
		logger.info(" care homes newCareHomePg : start ");
		
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		model.addAttribute("careHome", new CareHome());
		model.addAttribute("branchDetailsList", branchService.getBranchDetailsList(0));
		
		
		logger.info(" care homes newCareHomePg : end ");
		return "managecarehome";
	}

	@RequestMapping("/manageCareHomePg")
	public String manageCareHomePg(@ModelAttribute("careHome") CareHome careHome, Model model,
			HttpServletRequest request, HttpSession session, BindingResult result,RedirectAttributes redirectAttributes) {
		logger.info(" care homes manageCareHomePg : start ");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			careHome.setCreatedBy(usr.getUsrName());
			careHome.setCreatedDt(Calendar.getInstance().getTime());
			careHome.setUpdatedDt(Calendar.getInstance().getTime());
			CareHomeValidator validator = new CareHomeValidator();
			validator.validate(careHome, result);

			if (result.hasErrors()) {
				return "managecarehome";
			} else {
				// save BranchDetails
				String successMessage = careHomeService.insertCareHomes(careHome);
				if (strUtility.checkNullOrEmptyString(successMessage)) {
					redirectAttributes.addFlashAttribute("successMessage", successMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(" care homes manageCareHomePg : end ");
		return "redirect:searchPg";
	}
	
	@RequestMapping("/editCareHomePg")
	public String editBranchDetails(Model model, HttpServletRequest request, HttpSession session) {

		logger.info("editcarehome - start");
		Map<String, String> searchMap = new HashMap<>();
		CareHome careHome  = new CareHome();
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
				careHome = careHomeService.getCareHome(Integer.parseInt(id));
				model.addAttribute("branchDetailsList", branchService.getBranchDetailsList(0));
				model.addAttribute("countrycode", lookingService.getAllCountryAsList());
			}
			
			if (careHome == null) {
				careHome = new CareHome();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("careHome", careHome);

		model.addAttribute("mode", "update");
		logger.info("editcarehome - end");
		return "managecarehome";
	}

}
