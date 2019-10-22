package com.avee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.GroupDetails;
import com.avee.service.GroupService;
import com.avee.service.LookingService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.GroupValidator;

@Controller
@RequestMapping(value = "/group")
public class GroupController {

	static final Logger logger = LogManager.getLogger(GroupController.class.getName());
	
	@Autowired
	private StringUtility strUtility;
	
	@Autowired
	LookingService lookingService;
	
	@Autowired
	GroupService groupService;

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
	 * Display Group Search Page
	 */

	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpSession session, HttpServletRequest request) {
		logger.info(" Group SearchPg : start ");
		Map<String, String> searchMap = new HashMap<>();

		AppUser usr = (AppUser) session.getAttribute("userInfo");
		String groupname = request.getParameter("gname");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		List<AppUserGroupBranchMapping> groupDetails = new ArrayList<>();

		if (strUtility.checkNullOrEmptyString(groupname)) {
			searchMap.put("groupname", groupname);
		}
		searchMap.put("uid", usr.getUsrId());
		groupDetails = groupService.getGroupDetailsAsList(searchMap);
		model.addAttribute("groupList", groupDetails);
		model.addAttribute("countrycode", lookingService.getAllCountryAsList());

		logger.info(" Group SearchPg : end ");
		return "groupSearch";
	}

	@RequestMapping("/searchGroup")
	public String searchGroup(Model model, HttpServletRequest request) {
		logger.info(" Group searchGroup : start ");
		try {
			Map<String, String> searchMap = new HashMap<>();
			searchMap.put("groupName", request.getParameter("gname"));
			model.addAttribute("groupList", groupService.searchGroupDetails(searchMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" Group searchGroup : end ");
		return "groupSearch";
	}

	/*
	 * Add New Group Page
	 */
	@RequestMapping("/newGroupPg")
	public String newGroupPg(@ModelAttribute("groupDetails") GroupDetails groupDetails, Model model,
			HttpServletRequest request, HttpSession session) {
		logger.info(" Group newGroupPg : start ");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		model.addAttribute("countrycode", lookingService.getAllCountryAsList());

		logger.info(" Group newGroupPg : end ");
		return "manageGroup";
	}

	@RequestMapping(value = "/manageGroupDetails", params = "add")
	public String insertGroupDetails(@ModelAttribute("groupDetails") GroupDetails groupDetails, Model model,
			BindingResult result, HttpSession session,RedirectAttributes redirectAttributes) {
		logger.info("insertBranch- start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			groupDetails.setCreatedBy(usr.getUsrName());
			groupDetails.setCreatedDt(Calendar.getInstance().getTime());
			groupDetails.setUpdatedDt(Calendar.getInstance().getTime());

			GroupValidator validator = new GroupValidator();
			validator.validate(groupDetails, result);

			if (result.hasErrors()) {
				return "manageBranch";
			} else {
				// save BranchDetails
				String successMessage = groupService.insertGroupDetails(groupDetails);
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
	 * view/Edit GroupDetails Page
	 */
	@RequestMapping("/editGroupDetails")
	public String editGroupDetails(Model model, HttpServletRequest request,HttpSession session) {

		logger.info("GroupDetails - start");

		GroupDetails groupDetails = new GroupDetails();
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
				// get GroupDetails by Id
				groupDetails = groupService.getGroupDetails(Integer.parseInt(id));
				model.addAttribute("countrycode", lookingService.getAllCountryAsList());
			}
			if (groupDetails == null) {
				groupDetails = new GroupDetails();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("groupDetails", groupDetails);

		model.addAttribute("mode", "update");
		logger.info("GroupDetails - end");
		return "manageGroup";
	}
}
