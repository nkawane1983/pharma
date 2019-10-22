package com.avee.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.BranchDetails;
import com.avee.form.CollDelvSingup;
import com.avee.form.ScriptItems;
import com.avee.form.SearchForm;
import com.avee.service.CareHomeService;
import com.avee.service.CollDelvSingupService;
import com.avee.service.LookingService;
import com.avee.service.ReportService;
import com.avee.service.UserService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;

@Controller
@RequestMapping(value = "/proService")
public class ProfessionalServiceController {

	static final Logger logger = LogManager.getLogger(ProfessionalServiceController.class.getName());

	@Autowired
	private CareHomeService careHomeService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private UserService userService;

	@Autowired
	private StringUtility strUtility;

	@Autowired
	private CollDelvSingupService collDelvSingupService;

	@Autowired
	private LookingService lookingService;

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

		logger.info(" searchPg : end ");
		return "proServiceSearch";
	}

	@RequestMapping("/searchCareHomePg")
	public String searchCareHomePg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" searchCareHomePg : start ");
		Map<String, String> map = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			String sDate = strUtility.chageDateFormat(request.getParameter("fDate"));
			String endDate = strUtility.chageDateFormat(request.getParameter("tDate"));

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)) {
				map.put("fDate", sDate);
				map.put("tDate", endDate);
			} else {
				sDate = strUtility.getDate(strUtility.getMonth());
				endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				SearchForm searchForm = (SearchForm) session.getAttribute("searchForm");
				if (searchForm != null) {
					sDate = strUtility.covertDateToString(searchForm.getStartDate(), "yyyy-MM-dd");
					endDate = strUtility.covertDateToString(searchForm.getEndDate(), "yyyy-MM-dd");

				}

				map.put("fDate", sDate);
				map.put("tDate", endDate);
			}

			map.put("branchId", String.valueOf(branchdetails.getId()));
			map.put("groupid", "0");
			model.addAttribute("carhomeList", careHomeService.getCareHomesScriptItemsAsList(map));
			model.addAttribute("fdate", sDate);
			model.addAttribute("enddate", endDate);
			model.addAttribute("searchTitle", "Care Homes");
			model.addAttribute("addBtnUrl", "proService/newCareHomePg.do");
			model.addAttribute("searchUrl", "proService/searchCareHomePg.do");
			SearchForm searchForm = new SearchForm();
			searchForm.setStartDate(strUtility.covertStringToDate(sDate, "yyyy-MM-dd"));
			searchForm.setEndDate(strUtility.covertStringToDate(endDate, "yyyy-MM-dd"));
			session.setAttribute("searchForm", searchForm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info(" searchCareHomePg : end ");
		return "searchCareHome";
	}

	@RequestMapping("/ newCareHomePg")
	public String newCareHomePg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" care homes newCareHomePg : start ");
		Map<String, String> searchMap = new HashMap<>();
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		searchMap.put("fDate", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		searchMap.put("tDate", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		searchMap.put("branchId", String.valueOf(branchdetails.getId()));
		ScriptItems scriptItems = new ScriptItems();
		// scriptItems.setCareHomeScriptId(careHomeService.getByScriptItems(searchMap));
		// model.addAttribute("size", scriptItems.getScript().size());
		model.addAttribute("ScriptItems", scriptItems);
		model.addAttribute("carehome", careHomeService.getByBranchIdCareHome(searchMap));
		logger.info(" care homes newCareHomePg : end ");
		return "manageCareHome";
	}

	@RequestMapping("/ editCareHomePg")
	public String editCareHomePg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" care homes newCareHomePg : start ");
		Map<String, String> searchMap = new HashMap<>();
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		ScriptItems scriptItems = new ScriptItems();
		String id = request.getParameter("id");

		searchMap.put("fDate", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		searchMap.put("tDate", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));

		if (strUtility.checkNullOrEmptyString(id)) {
			// get branchDetails by Id
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			scriptItems = careHomeService.getScriptItemsById(Integer.parseInt(id));
			model.addAttribute("mode", "edit");
		}
		String reportid = request.getParameter("reportid");
		String branchId = request.getParameter("branchid");
		if (strUtility.checkNullOrEmptyString(reportid) && strUtility.checkNullOrEmptyString(branchId)) {
			searchMap.put("branchId", branchId);
			scriptItems = careHomeService.getScriptItemsById(Integer.parseInt(reportid));
			model.addAttribute("report", "report");
			model.addAttribute("branchId", branchId);
		}
		if (scriptItems == null) {
			scriptItems = new ScriptItems();
		}

		model.addAttribute("size", scriptItems.getCareHomeScriptId().size());
		model.addAttribute("ScriptItems", scriptItems);
		model.addAttribute("carehome", careHomeService.getByBranchIdCareHome(searchMap));
		logger.info(" care homes newCareHomePg : end ");
		return "manageCareHome";
	}

	@RequestMapping(value = "/manageCareHomePg", params = "save")
	public String manageCareHomePg(Model model, HttpServletRequest request,
			@ModelAttribute("ScriptItems") ScriptItems scriptItems, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info(" care homes manageCareHomePg : start ");
		AppUser usr = (AppUser) session.getAttribute("userInfo");
		try {

			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			scriptItems.setBranchId(branchdetails.getId());
			scriptItems.setCreatedBy(usr.getUsrName());
			scriptItems.setCreatedDt(Calendar.getInstance().getTime());
			scriptItems.setUpdatedDt(Calendar.getInstance().getTime());
			String status = "";
			if (careHomeService.checkScriptItemsExistOrNot(scriptItems) == false) {
				status = careHomeService.insertScriptItems(scriptItems);
				redirectAttributes.addFlashAttribute("successMessage", status);
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "This Record already Exists.");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" care homes newCareHomePg : end ");
		return "redirect:searchCareHomePg";
	}

	@RequestMapping(value = "/manageCareHomePg", params = "edit")
	public String editCareHomePg(Model model, HttpServletRequest request,
			@ModelAttribute("ScriptItems") ScriptItems scriptItems, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info(" care homes editCareHomePg : start ");
		AppUser usr = (AppUser) session.getAttribute("userInfo");
		try {

			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			scriptItems.setBranchId(branchdetails.getId());
			scriptItems.setUpdatedBy(usr.getUsrName());
			scriptItems.setUpdatedDt(Calendar.getInstance().getTime());
			String status = careHomeService.updateScriptItems(scriptItems, "scriptItemsUpdate");
			redirectAttributes.addFlashAttribute("successMessage", status);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" care homes newCareHomePg : end ");
		return "redirect:searchCareHomePg";
	}

	@RequestMapping("/newCDPg")
	public String newCDPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" newCDPg : start ");
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		Map<String, String> map = new HashMap<>();
		map.put("bid", String.valueOf(branchdetails.getId()));
		map.put("fDate", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		map.put("tDate", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));

		CollDelvSingup collDelvSingup = new CollDelvSingup();

		//collDelvSingup.setCollDelv(collDelvSingupService.getCollDelvSingupasList(map));
		model.addAttribute("collDelvSingup", collDelvSingup);
		model.addAttribute("userlist", userService.getBranchUserAssocsList(map));
		logger.info(" newCDPg : end ");
		return "manageCD";
	}

	@RequestMapping(value = "/manageCollDelvPg", params = "save")
	public String manageCollDelvPg(Model model, HttpServletRequest request,
			@ModelAttribute("collDelvSingup") CollDelvSingup collDelvSingup, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info(" CD manageCollDelvPg : start ");
		AppUser usr = (AppUser) session.getAttribute("userInfo");
		String eventdate = request.getParameter("eventdate");
		Map<String, String> map = new HashMap<>();
		try {
			if (strUtility.checkNullOrEmptyString(eventdate)) {
				BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
				eventdate = strUtility.chageDateFormat(eventdate);
				map.put("bid", String.valueOf(branchdetails.getId()));
				map.put("fDate", eventdate);
				map.put("tDate", eventdate);
				if (collDelvSingupService.checkCollDelvSingupExistOrNot(map)) {
					Date eventDate = strUtility.covertStringToDate(eventdate, "yyyy-MM-dd");

					for (CollDelvSingup CollDelvSingupform : collDelvSingup.getCollDelv()) {
						if (CollDelvSingupform.getId() == 0) {
							CollDelvSingupform.setBranchId(branchdetails.getId());
							CollDelvSingupform.setEventDate(eventDate);
							CollDelvSingupform.setCreatedBy(usr.getUsrName());
							CollDelvSingupform.setCreatedDt(Calendar.getInstance().getTime());
							CollDelvSingupform.setUpdatedDt(Calendar.getInstance().getTime());
							String status = collDelvSingupService.insertScriptItems(CollDelvSingupform);
							redirectAttributes.addFlashAttribute("successMessage", status);
						}
					}

				} else {
					redirectAttributes.addFlashAttribute("errorMessage",
							"Collection & Delivery  already inserted.");
				}
			} else {
				redirectAttributes.addFlashAttribute("successMessage", "Collection & Delivery  can not be inserted.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" CD manageCollDelvPg : end ");
		return "redirect:searchCDPg";
	}
	@RequestMapping(value = "/manageCollDelvPg", params = "edit")
	public String editCollDelvPg(Model model, HttpServletRequest request,
			@ModelAttribute("collDelvSingup") CollDelvSingup collDelvSingup, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info(" CD manageCollDelvPg : start ");
		AppUser usr = (AppUser) session.getAttribute("userInfo");
		String eventdate = request.getParameter("eventdate");
		Map<String, String> map = new HashMap<>();
		try {
			if (strUtility.checkNullOrEmptyString(eventdate)) {
				BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
				eventdate = strUtility.chageDateFormat(eventdate);
				map.put("bid", String.valueOf(branchdetails.getId()));
				map.put("fDate", eventdate);
				map.put("tDate", eventdate);
				
					Date eventDate = strUtility.covertStringToDate(eventdate, "yyyy-MM-dd");
					collDelvSingupService.deleteCollDelvSingup(collDelvSingup);
					for (CollDelvSingup CollDelvSingupform : collDelvSingup.getCollDelv()) {
						if(CollDelvSingupform.getUserId()!=null){
							CollDelvSingupform.setId(0);
							CollDelvSingupform.setBranchId(branchdetails.getId());
							CollDelvSingupform.setEventDate(eventDate);
							CollDelvSingupform.setUpdatedBy(usr.getUsrName());
							CollDelvSingupform.setCreatedBy(usr.getUsrName());
							CollDelvSingupform.setCreatedDt(Calendar.getInstance().getTime());
							CollDelvSingupform.setUpdatedDt(Calendar.getInstance().getTime());
							collDelvSingupService.insertScriptItems(CollDelvSingupform);
						}
							redirectAttributes.addFlashAttribute("successMessage", "Collection & Delivery updated successfully.");
				
					}

				} else {
					redirectAttributes.addFlashAttribute("errorMessage",
							"Collection & Delivery  can not be updated.");
				}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" CD manageCollDelvPg : end ");
		return "redirect:searchCDPg";
	}

	@RequestMapping("/editCDPg")
	public String editCDPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" editCDPg : start ");
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		String sDate = request.getParameter("id");
		String decodeId = strUtility.decodeString(sDate);

		// Base64.encode(strUtility.covertDateToString(collDelvSingupListDomain.getEventDate(),"yyyy-MM-dd").getBytes()).toString()
		Map<String, String> map = new HashMap<>();
		map.put("bid", String.valueOf(branchdetails.getId()));
		map.put("fDate", decodeId);
		map.put("tDate", decodeId);

		CollDelvSingup collDelvSingup = new CollDelvSingup();

		collDelvSingup.setCollDelv(collDelvSingupService.getCollDelvSingupasList(map));
		model.addAttribute("collDelvSingup", collDelvSingup);
		model.addAttribute("mode", "edit");
		model.addAttribute("userlist", userService.getBranchUserAssocsList(map));
		model.addAttribute("eventDate", strUtility.covertStringToDate(decodeId, "yyyy-MM-dd"));
		logger.info(" editCDPg : end ");
		return "manageCD";
	}

	@RequestMapping("/searchCDPg")
	public String searchCDPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" searchCDPg : start ");
		Map<String, String> map = new HashMap<>();
		try {
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String sDate = strUtility.chageDateFormat(request.getParameter("fDate"));
			String endDate = strUtility.chageDateFormat(request.getParameter("tDate"));
			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)) {
				map.put("fDate", sDate);
				map.put("tDate", endDate);
			} else {
				sDate = strUtility.getDate(strUtility.getMonth());
				endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

				SearchForm searchForm = (SearchForm) session.getAttribute("searchForm");
				if (searchForm != null) {
					sDate = strUtility.covertDateToString(searchForm.getStartDate(), "yyyy-MM-dd");
					endDate = strUtility.covertDateToString(searchForm.getEndDate(), "yyyy-MM-dd");

				}
				map.put("fDate", sDate);
				map.put("tDate", endDate);
			}

			map.put("bid", String.valueOf(branchdetails.getId()));
			model.addAttribute("colldevList", collDelvSingupService.searchCollDelvSingupasList(map));
			model.addAttribute("fdate", sDate);
			model.addAttribute("enddate", endDate);
			model.addAttribute("searchTitle", "Collection & Delivery");
			model.addAttribute("addBtnUrl", "proService/newCDPg.do");
			model.addAttribute("searchUrl", "proService/searchCDPg.do");
			SearchForm searchForm = new SearchForm();
			searchForm.setStartDate(strUtility.covertStringToDate(sDate, "yyyy-MM-dd"));
			searchForm.setEndDate(strUtility.covertStringToDate(endDate, "yyyy-MM-dd"));
			session.setAttribute("searchForm", searchForm);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info(" searchCDPg : end ");
		return "searchCD";
	}
}
