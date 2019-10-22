package com.avee.controller;

import java.text.SimpleDateFormat;
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
import com.avee.form.SystemParameter;
import com.avee.service.LookingService;
import com.avee.service.SysParamService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.utility.UserUtility;
import com.avee.validator.SysParamValidator;

@Controller
@RequestMapping(value = "/sysParameter")
public class SystemParameterController {

	static final Logger logger = LogManager.getLogger(SystemParameterController.class.getName());

	@Autowired
	private SysParamService sysParamService;

	@Autowired
	private LookingService lookingService;
	
	@Autowired
	private StringUtility strUtility;

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
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		String sysParamName = request.getParameter("sysParamName");
		String sysId = request.getParameter("sysId");
		String sysParamValue = request.getParameter("sysParamValue");

		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("sysParamName", sysParamName);
		searchMap.put("sysParamValue", sysParamValue);
		searchMap.put("sysId", sysId);
		List<SystemParameter> list = sysParamService.searchSysParam(searchMap);
		model.addAttribute("sysParamList", list);
		logger.info(" searchPg : end ");
		return "sysParamSearch";
	}

	@RequestMapping(value = "/searchSysParameter", method = RequestMethod.GET)
	public String searchSysParameter(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("searchSysParameter - start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String sysParamName = request.getParameter("sysParamName");
			String sysId = request.getParameter("sysId");
			String sysParamValue = request.getParameter("sysParamValue");

			Map<String, String> searchMap = new HashMap<>();
			searchMap.put("sysParamName", sysParamName);
			searchMap.put("sysParamValue", sysParamValue);
			searchMap.put("sysId", sysId);
			List<SystemParameter> list = sysParamService.searchSysParam(searchMap);
			model.addAttribute("sysParamList", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("searchSysParameter - end");
		return "sysParamSearch";
	}

	@RequestMapping("/newSysParam")
	public String newSysParam(Model model, HttpSession session) {
		logger.info("newSysParam - start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			model.addAttribute("systemParameter", new SystemParameter());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("newSysParam - end");
		return "manageSysParam";
	}

	@RequestMapping("/editSysParam")
	public String editSysParam(Model model, HttpServletRequest request,HttpSession session) {
		logger.info("editSysParam - start");

		SystemParameter sysParam = new SystemParameter();

		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
						 * get Menu List By userRole
						 * By Naresh update 11/01/2018 11:30:00
						 */
						model.addAttribute("menuList",lookingService.getMenuListByUserRole(usr.getUsrType()));
						/*
						 * end Get Menu
						 */
			String id = request.getParameter("id");

			if (strUtility.checkNullOrEmptyString(id)) {

				sysParam = sysParamService.getSystemParameter(Integer.parseInt(id));

				if (sysParam == null) {
					sysParam = new SystemParameter();
				} else {
					model.addAttribute("mode", "update");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("systemParameter", sysParam);
		logger.info("editSysParam - end");

		return "manageSysParam";
	}

	@RequestMapping(value = "/manageSysParam", params = "add")
	public String insertSysParam(@ModelAttribute("systemParameter") SystemParameter systemParameter, Model model,
			BindingResult result,RedirectAttributes redirectAttributes) {

		try {

			SysParamValidator validator = new SysParamValidator();
			validator.validate(systemParameter, result);

			if (result.hasErrors()) {
				return "manageSysParam";
			} else {

				systemParameter.setCreatedBy(UserUtility.getSessionUserId());
				systemParameter.setCreatedDt(new Date());

				String message = sysParamService.crudSysParam(systemParameter, "insert");
				if (strUtility.checkNullOrEmptyString(message)) {
					redirectAttributes.addFlashAttribute("successMessage", message);
				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "System parameter can not be inserted.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "System parameter can not be inserted.");
		}

		return "redirect:searchSysParameter";
	}

	@RequestMapping(value = "/manageSysParam", params = "edit")
	public String updateSysParam(@ModelAttribute("systemParameter") SystemParameter systemParameter, Model model,
			BindingResult result,RedirectAttributes redirectAttributes) {
		try {

			SysParamValidator validator = new SysParamValidator();
			validator.validate(systemParameter, result);

			if (result.hasErrors()) {
				return "manageSysParam";
			} else {

				systemParameter.setUpdatedBy(UserUtility.getSessionUserId());
				systemParameter.setUpdatedDt(new Date());

				String message = sysParamService.crudSysParam(systemParameter, "update");

				if (strUtility.checkNullOrEmptyString(message)) {
					redirectAttributes.addFlashAttribute("successMessage", message);
				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "System parameter can not be updated.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "System parameter can not be updated.");
		}

		return "redirect:searchSysParameter";
	}

	@RequestMapping(value = "/deleteSysParam")
	public String deleteSysParam(Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {

			String id = request.getParameter("id");
			SystemParameter systemParameter = new SystemParameter();
			if (strUtility.checkNullOrEmptyString(id)) {
				systemParameter.setId(Integer.parseInt(id));
				String message = sysParamService.crudSysParam(systemParameter, "delete");

				if (strUtility.checkNullOrEmptyString(message)) {
					redirectAttributes.addFlashAttribute("successMessage", message);
				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "System parameter can not be deleted.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "System parameter can not be deleted.");
		}

		return "redirect:searchSysParameter";
	}
}
