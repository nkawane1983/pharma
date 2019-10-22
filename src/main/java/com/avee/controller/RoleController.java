package com.avee.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.avee.service.LookingService;
import com.avee.service.RoleService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.RoleValidator;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

	static final Logger logger = LogManager.getLogger(RoleController.class.getName());

	@Autowired
	private RoleService roleService;

	@Autowired
	private StringUtility strUtility;

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
	public String searchPg(Model model, HttpSession session) {
		logger.info("Role searchPage - start");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		logger.info("Role searchPage - end");
		return "roleSearch";
	}

	@RequestMapping(value = "/searchRole", method = RequestMethod.GET)
	public String searchRole(Model model, HttpServletRequest request, HttpSession session) {
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
			String roleName = request.getParameter("roleName");
			String roleId = request.getParameter("roleId");
			logger.info("searchRole - start" + roleName);
			Map<String, String> searchMap = new HashMap<>();
			searchMap.put("role_Name", roleName);
			searchMap.put("role_Id", roleId);

			List<Role> roleList = roleService.searchRole(searchMap);
			model.addAttribute("roleList", roleList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("searchRole - end");
		return "roleSearch";
	}

	@RequestMapping("/newRole")
	public String newRole(Model model, HttpSession session) {
		logger.info("newRole - start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			model.addAttribute("role", new Role());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("newRole - end");
		return "manageRole";
	}

	@RequestMapping(value = "/manageRole", params = "add")
	public String insertRole(@ModelAttribute("role") Role role, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.info("insertRole - start");
		try {

			RoleValidator validator = new RoleValidator();
			validator.validate(role, result);

			if (result.hasErrors()) {
				return "manageRole";
			} else {
				String successMessage = roleService.insertRole(role);
				if (strUtility.checkNullOrEmptyString(successMessage)) {
					redirectAttributes.addFlashAttribute("successMessage", successMessage);
				}
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Role did not Insert.");
			e.printStackTrace();
		}
		logger.info("insertRole - end");
		return "redirect:searchRole";
	}

	@RequestMapping("/editRole")
	public String editRole(Model model, HttpServletRequest request, HttpSession session) {

		logger.info("editRole - start");

		Role role = new Role();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String roleId = request.getParameter("rolId");
			if (strUtility.checkNullOrEmptyString(roleId)) {
				role = roleService.getRole(Integer.parseInt(roleId));
			}
			if (role == null) {
				role = new Role();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("role", role);

		model.addAttribute("mode", "update");
		logger.info("editRole - end");
		return "manageRole";
	}

	@RequestMapping(value = "/manageRole", params = "edit")
	public String updateRole(@ModelAttribute("role") Role role, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.info("updateRole - start");
		try {

			RoleValidator validator = new RoleValidator();
			validator.validate(role, result);

			if (result.hasErrors()) {
				model.addAttribute("mode", "update");
				return "manageRole";
			} else {

				if (StringUtils.isEmpty(role.getRolIsactive())) {
					role.setRolIsactive("N");
				}

				roleService.updateRole(role);
				redirectAttributes.addFlashAttribute("successMessage", "Role update successfully.");
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Role did not update.");
			e.printStackTrace();
		}
		logger.info("updateRole - end");
		return "redirect:searchRole";
	}

	@RequestMapping(value = "/deleteRole")
	public String deleteRole(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		logger.info("deleteBranch - start");

		try {

			String id = request.getParameter("rolId");
			if (strUtility.checkNullOrEmptyString(id)) {
				roleService.deleteRole(Integer.parseInt(id));
				redirectAttributes.addFlashAttribute("successMessage", "Role Delete successfully.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Role did not Delete.");
		}

		logger.info("deleteRole - end");
		return "redirect:searchRole";
	}
}
