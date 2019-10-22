package com.avee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.Role;
import com.avee.form.UserType;
import com.avee.form.UserTypeRole;
import com.avee.service.LookingService;
import com.avee.service.RoleService;
import com.avee.service.UserTypeService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.utility.UserUtility;
import com.avee.validator.UserTypeValidator;

@Controller
@RequestMapping(value = "/userType")
public class UserTypeController {

	static final Logger logger = LogManager.getLogger(UserTypeController.class.getName());

	@Autowired
	private UserTypeService userTypeservice;

	@Autowired
	private StringUtility strUtility;

	@Autowired
	private RoleService roleService;

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

	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" searchPg : start ");
		Map<String, String> searchMap = new HashMap<>();
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		List<UserType> list = userTypeservice.searchUserType(searchMap);
		model.addAttribute("userTypeList", list);
		logger.info(" searchPg : end ");
		return "userTypeSearch";
	}

	@RequestMapping(value = "/searchUserType", method = RequestMethod.GET)
	public String searchUserType(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("searchRole - start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String userTypeId = request.getParameter("userTypeId");
			String userTypeName = request.getParameter("userTypeName");

			Map<String, String> searchMap = new HashMap<>();
			searchMap.put("userTypeName", userTypeName);
			searchMap.put("userTypeId", userTypeId);

			List<UserType> list = userTypeservice.searchUserType(searchMap);
			model.addAttribute("userTypeList", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("searchRole - end");
		return "userTypeSearch";
	}

	@RequestMapping("/newUserType")
	public String newUserType(Model model, HttpSession session) {
		logger.info("newUserType - start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			model.addAttribute("userType", new UserType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("newUserType - end");
		return "manageUserType";
	}

	@RequestMapping("/editUserType")
	public String editUserType(Model model, HttpServletRequest request, HttpSession session) {

		logger.info("editUserType - start");

		UserType uType = new UserType();
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

				int userTypeId = Integer.parseInt(id);

				List<Role> roleList = roleService.getRoleList();
				model.addAttribute("roleList", roleList);

				List<UserTypeRole> userTypeRoleList = userTypeservice.getUserTypeRoleList(userTypeId);
				model.addAttribute("userTypeRoleList", userTypeRoleList);

				uType = userTypeservice.getUserType(userTypeId, false);
			}
			if (uType == null) {
				uType = new UserType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("userType", uType);
		model.addAttribute("mode", "update");

		logger.info("editUserType - end");
		return "manageUserType";
	}

	@RequestMapping(value = "/manageUserType", params = "add")
	public String insertUserType(@ModelAttribute("userType") UserType userType, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.info("insertUserType - start");
		try {

			UserTypeValidator validator = new UserTypeValidator();
			validator.validate(userType, result);

			if (result.hasErrors()) {
				return "manageUserType";
			} else {

				userType.setCreatedBy(UserUtility.getSessionUserId());
				userType.setCreatedDt(new Date());

				String successMessage = userTypeservice.insertUserType(userType);
				if (strUtility.checkNullOrEmptyString(successMessage)) {
					redirectAttributes.addFlashAttribute("successMessage", successMessage);
				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "User type can not be inserted.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("insertUserType - end");
		return "redirect:searchPg";
	}

	@RequestMapping(value = "/manageUserType", params = "edit")
	public String updateUserType(@ModelAttribute("userType") UserType userType, Model model, BindingResult result,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		logger.info("updateUserType - start");
		try {

			UserTypeValidator validator = new UserTypeValidator();
			validator.validate(userType, result);

			if (result.hasErrors()) {
				model.addAttribute("mode", "update");
				return "manageUserType";
			} else {

				userType.setUpdatedBy(UserUtility.getSessionUserId());
				userType.setUpdatedDt(new Date());

				String successMessage = userTypeservice.updateUserType(userType);
				;
				if (strUtility.checkNullOrEmptyString(successMessage)) {

					List<UserTypeRole> userRoleList = new ArrayList<>();
					String counter = request.getParameter("counter");

					if (strUtility.checkNullOrEmptyString(counter)) {

						int count = Integer.parseInt(counter) - 1;

						for (int i = 1; i <= count; i++) {

							String roleCheck = request.getParameter("roleCheck_" + i);

							String roleId = request.getParameter("roleId_" + i);

							if (strUtility.checkNullOrEmptyString(roleCheck)) {

								UserTypeRole userRole = new UserTypeRole();

								Role role = new Role();
								role.setRolId(Integer.parseInt(roleId));

								userRole.setRole(role);
								userRole.setUserType(userType);

								userRoleList.add(userRole);
							}
						}

						/**
						 * In this operation first we delete all data based on
						 * user id from phy user role table Than after we insert
						 * the record. crud operation
						 */
						userTypeservice.manageUserTypeRole(userRoleList, userType.getId());
					}

					redirectAttributes.addFlashAttribute("successMessage", successMessage);

				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "User type can not be updated.");
				}

			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "User type can not be updated.");
			e.printStackTrace();
		}
		logger.info("updateUserType - end");
		return "redirect:searchPg";
	}

	@RequestMapping("/deleteUserType")
	public String deleteUserType(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		logger.info("deleteUserType - start");

		try {

			String id = request.getParameter("id");
			if (strUtility.checkNullOrEmptyString(id)) {

				int userTypeId = Integer.parseInt(id);
				String message = userTypeservice.deleteUserType(userTypeId);

				if (strUtility.checkNullOrEmptyString(message)) {
					redirectAttributes.addFlashAttribute("successMessage", message);
				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "UserType can not be deleted.");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("deleteUserType - end");
		return "redirect:searchPg";
	}

}
