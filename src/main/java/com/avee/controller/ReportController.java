package com.avee.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avee.form.AppUser;
import com.avee.form.BranchDetails;
import com.avee.form.Category;
import com.avee.form.Report;
import com.avee.service.BranchService;
import com.avee.service.CategoryService;
import com.avee.service.ExcelReportService;
import com.avee.service.GroupService;
import com.avee.service.LookingService;
import com.avee.service.ReportService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

	static final Logger logger = LogManager.getLogger(ReportController.class.getName());

	@Autowired
	private GroupService groupService;

	@Autowired
	private ReportService reportService;

	@Autowired
	private StringUtility strUtility;

	@Autowired
	private BranchService branchService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ExcelReportService excelReportService;

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

	int gid = 0;

	@RequestMapping("/cashReportPg")
	public String cashReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" cashReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);

			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;
			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");
			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
				sDate = strUtility.getfirstdate(endDate);
			}

			map.put("fDate", sDate);
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", sDate);

			model.addAttribute("enddate", endDate);
			map.put("userid", userid);
			map.put("uid", userid);
			map.put("groupId", groupid);
			map.put("branchID", "0");
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));
			model.addAttribute("reportUrl", "cashReportPg.do");
			model.addAttribute("reportTitle", "Cash Report");
			model.addAttribute("reportTypeObj", "cash");
			model.addAttribute("sage", "sage");
			model.addAttribute("cashList", reportService.getCashReport(map));
			setCookiesValue(request, response, map);
		} catch (Exception e)

		{
			e.printStackTrace();
		}

		logger.info(" cashReportPg : end ");
		return "cashReport";

	}

	@RequestMapping("/nhsReportPg")
	public String nhsReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" nhsReportPg : start ");

		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;
			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
				sDate = strUtility.getfirstdate(endDate);
			}
			map.put("fDate", sDate);
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "0");
			model.addAttribute("reportType", "report");
			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", sDate);
			model.addAttribute("enddate", endDate);
			map.put("userid", userid);
			model.addAttribute("nhslist", reportService.getNHSReport(map));
			model.addAttribute("reportUrl", "nhsReportPg.do");
			model.addAttribute("reportTitle", "NHS Report");
			model.addAttribute("reportTypeObj", "monthlyNhs");
			setCookiesValue(request, response, map);

		} catch (Exception e)

		{
			e.printStackTrace();
		}
		logger.info(" nhsReportPg : end ");
		return "nhsReport";

	}

	@RequestMapping("/nhsMonthlyReportPg")
	public String nhsMonthlyReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" nhsReportPg : start ");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		Map<String, String> map = new HashMap<>();
		Map<String, String> cook = new HashMap<>();
		cook = getCookiesValue(request, response);
		String sDate = (String) cook.get("fDate");
		String endDate = (String) cook.get("tDate");
		String groupid = "0";
		String branchid = request.getParameter("id");

		if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
				&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "1");
		}
		map.put("userid", "");
		model.addAttribute("reportType", "report");
		model.addAttribute("reportUrl", "nhsReportPg.do");
		model.addAttribute("nhslist", reportService.getNHSReport(map));
		model.addAttribute("branch", branchService.getBranchDetails(Integer.parseInt(branchid)).getDescription());
		model.addAttribute("mode", "Edit");
		logger.info(" nhsReportPg : end ");
		return "nhsReport";
	}

	@RequestMapping("/monthlyCashReportPg")
	public String monthlyCashReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" monthlyCashReportPg : start ");

		Map<String, String> map = new HashMap<>();
		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;
			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& strUtility.checkNullOrEmptyString(request.getParameter("branchid"))) {
					branchid = request.getParameter("branchid");
				}
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");

				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}

				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("mode", "cashing");
			model.addAttribute("enddate", endDate);
			map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));

			model.addAttribute("monthlyReportList", reportService.getMonthlyCashReport(map));
			model.addAttribute("reportUrl", "monthlyCashReportPg.do");
			model.addAttribute("reportTitle", "Monthly Cash Report");
			model.addAttribute("reportTypeObj", "monthlyCash");

			setCookiesValue(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" monthlyCashReportPg : end ");
		return "monthlyCashReport";
	}

	@RequestMapping("/bankingReportPg")
	public String bankingReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" bankingReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "0");

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);

			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);

			map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));
			model.addAttribute("bankReportList", reportService.getBankingReport(map));
			model.addAttribute("reportUrl", "bankingReportPg.do");
			model.addAttribute("reportTitle", "Banking Report");
			model.addAttribute("reportTypeObj", "monthlyBanking");
			setCookiesValue(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" bankingReportPg : end ");
		return "bankingReport";
	}

	@RequestMapping("/bankingMonthlyReportPg")
	public String bankingMonthlyReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" bankingMonthlyReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		cook = getCookiesValue(request, response);
		String sDate = (String) cook.get("fDate");
		String endDate = (String) cook.get("tDate");
		String groupid = "0";
		String branchid = request.getParameter("branchId");

		if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
				&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "1");
			map.put("userid", "");
		}

		model.addAttribute("banklist", reportService.getBankingReport(map));
		model.addAttribute("branch", branchService.getBranchDetails(Integer.parseInt(branchid)).getDescription());
		model.addAttribute("mode", "Edit");
		logger.info(" bankingMonthlyReportPg : end ");
		return "bankingReport";
	}

	@RequestMapping("/monthlyBankingReport")
	public String monthlyBankingReport(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" monthlyBankingReport : start ");
		Map<String, String> map = new HashMap<>();
		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);

			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));

			model.addAttribute("enddate", endDate);
			map.put("userid", userid);
			List<BranchDetails> branchList = new ArrayList<>();
			if (groupid.equals("0") && branchid.equals("0")) {
				branchList = branchService.getBranchDetailsASList(groupid, "all", userid, "");
			}

			if (!groupid.equals("0") && branchid.equals("0")) {
				branchList = branchService.getBranchDetailsASList(groupid, "groupId", userid, "");
			}

			if (!groupid.equals("0") && !branchid.equals("0") || (groupid.equals("0") && !

			branchid.equals("0"))) {
				branchList = branchService.getBranchDetailsASList(branchid, "branchId", userid, "");
			}

			model.addAttribute("branchlist", branchList);

			model.addAttribute("monthlybankReportList", reportService.getMonthlyBankingReport(map, branchList));

			model.addAttribute("reportUrl", "monthlyBankingReport.do");
			model.addAttribute("reportTitle", "Monthly Banking Report");
			setCookiesValue(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" monthlyBankingReport : end ");
		return "monthlyBanking";
	}

	@RequestMapping("/carehomeReportPg")
	public String carehomeReporttPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" carehomeReporttPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "0");

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);

			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);

			 map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			//
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));
			model.addAttribute("carehomelist", reportService.getCareHomeReport(map));

			model.addAttribute("reportUrl", "carehomeReportPg.do");
			model.addAttribute("reportTitle", "Care Homes / MDS / ITEMS");
			model.addAttribute("reportTypeObj", "monthlycarehome");
			setCookiesValue(request, response, map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" carehomeReporttPg : End ");
		return "carehomeReport";
	}

	@RequestMapping("/monthlyCarehomeReportPg")
	public String monthlyCarehomeReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" monthlyCarehomeReportPg : start ");
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
			Map<String, String> cook = new HashMap<>();
			cook = getCookiesValue(request, response);
			// String sDate = cook.get("fDate");
			String endDate = cook.get("tDate");
			String groupid = "0";
			String branchid = request.getParameter("id");

			if (!strUtility.checkNullOrEmptyString(branchid))
				return "redirect:carehomeReportPg";

			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			model.addAttribute("mode", "edit");
			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);

			model.addAttribute("branch", branchService.getBranchDetails(Integer.parseInt(branchid)).getDescription());
			model.addAttribute("carehomesreportlist", reportService.getMonthlyCareHomesReport(map));

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" monthlyCarehomeReportPg : End ");
		return "carehomeReport";
	}

	@RequestMapping("/colldelvReportPg")
	public String colldelvReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" colldelvReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);

			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "0");
			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);

			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);

			map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));

			model.addAttribute("colldellist", reportService.getCollDelivReport(map));

			model.addAttribute("reportUrl", "colldelvReportPg.do");
			model.addAttribute("reportTitle", "Collection & Delivery");
			model.addAttribute("reportTypeObj", "monthlycolldel");
			setCookiesValue(request, response, map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" colldelvReportPg : End ");
		return "colldelvReport";
	}

	@RequestMapping("/monthlycolldelvReportPg")
	public String monthlycolldelvReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" monthlycolldelvReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);

			// String sDate = null;
			String endDate = null;
			String groupid = null;

			String userid = usr.getUsrId();

			String branchid = request.getParameter("id");

			if (!strUtility.checkNullOrEmptyString(branchid))
				return "redirect:colldelvReportPg";

			endDate = cook.get("tDate");
			groupid = "0";

			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);

			model.addAttribute("mode", "edit");
			map.put("uid", userid);
			map.put("groupId", groupid);
			map.put("branchID", "0");
			model.addAttribute("branch", branchService.getBranchDetails(Integer.parseInt(branchid)).getDescription());
			model.addAttribute("branchuser", branchService.branchDetailsAssolist(map));
			model.addAttribute("colldelreport", reportService.getMonthlyCollDevReport(map));

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" monthlycolldelvReportPg : End ");
		return "colldelvReport";
	}

	@RequestMapping("/expensesReportPg")
	public String expensesReportPg(Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		logger.info(" expensesReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			map.put("mode", "0");
			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);

			map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			//
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));

			List<Category> categorylist = categoryService.searchCategory("PaidOuts");

			model.addAttribute("categoryList", categorylist);

			model.addAttribute("expenseslist", reportService.getMonthlyExpensesReport(map, categorylist));
			model.addAttribute("reportUrl", "expensesReportPg.do");
			model.addAttribute("reportTitle", "Monthly Expenses");
			model.addAttribute("reportTypeObj", "monthlyexpenses");
			setCookiesValue(request, response, map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" expensesReportPg : End ");
		return "monthlyExpensesReport";
	}

	@RequestMapping("/montlyExpensesReportPg")
	public String montlyExpensesReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" montlyExpensesReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();

		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		cook = getCookiesValue(request, response);
		String sDate = (String) cook.get("fDate");
		String endDate = (String) cook.get("tDate");
		String groupid = "0";
		String reportKey = request.getParameter("reportKey");
		String branchid = "";
		if (reportKey != null)
			branchid = strUtility.decodeString(reportKey);

		if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
				&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
		}
		map.put("userid", "");
		List<Category> categorylist = categoryService.searchCategory("PaidOuts");

		model.addAttribute("categoryList", categorylist);
		model.addAttribute("monthlyExpenseslist",
				reportService.getMonthlyExpensesSummaryReportAsList(map, categorylist));
		model.addAttribute("branch", branchService.getBranchDetails(Integer.parseInt(branchid)).getDescription());
		model.addAttribute("mode", "Edit");
		logger.info(" montlyExpensesReportPg : end ");
		return "monthlyExpensesReport";
	}

	@RequestMapping("/vatTaxAnalysisReportPg")
	public String vatTaxAnalysisReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" vatTaxAnalysisReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			map.put("mode", "0");

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);
			map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			//
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));
			model.addAttribute("vatTaxReportList", reportService.getVatTaxReport(map));
			model.addAttribute("reportUrl", "vatTaxAnalysisReportPg.do");
			model.addAttribute("reportTitle", "VAT & TAX Analysis Report");
			model.addAttribute("reportTypeObj", "monthlyVat");
			setCookiesValue(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" vatTaxAnalysisReportPg : end ");
		return "vattaxReport";
	}

	@RequestMapping("/vatTaxMonthlyReportPg")
	public String vatTaxMonthlyReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" vatTaxMonthlyReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();

		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		cook = getCookiesValue(request, response);
		String sDate = (String) cook.get("fDate");
		String endDate = (String) cook.get("tDate");
		String groupid = "0";
		String branchid = request.getParameter("branchId");

		if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
				&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "1");
		}
		map.put("userid", "");
		model.addAttribute("vatTaxlist", reportService.getVatTaxReport(map));
		model.addAttribute("branch", branchService.getBranchDetails(Integer.parseInt(branchid)).getDescription());
		model.addAttribute("mode", "Edit");
		logger.info(" vatTaxMonthlyReportPg : end ");
		return "vattaxReport";
	}

	@RequestMapping("/couponsReportPg")
	public String couponsReportPg(Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		logger.info(" couponsReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			map.put("mode", "0");
			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);

			map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			//
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));

			List<Category> categorylist = categoryService.searchCategory("Coupons");
			// System.out.println(categorylist.size());
			model.addAttribute("categoryList", categorylist);

			model.addAttribute("couponlist", reportService.getMonthlyCouponReport(map, categorylist));
			model.addAttribute("reportUrl", "couponsReportPg.do");
			model.addAttribute("reportTitle", "Monthly Coupon");
			model.addAttribute("reportTypeObj", "monthlycoupon");
			setCookiesValue(request, response, map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" couponsReportPg : End ");
		return "couponsReport";
	}

	@RequestMapping("/customerStatsReportPg")
	public String customerStatsReportPg(Model model, HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		logger.info(" customerStatsReportPg : start ");
		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;

			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			map.put("mode", "0");

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("enddate", endDate);
			map.put("userid", userid);
			// map.put("uid", userid);
			// map.put("groupId", groupid);
			// map.put("branchID", "0");
			//
			// model.addAttribute("branchASlist",
			// branchService.getBranchDetailsaAsList(map));
			model.addAttribute("custstatslist", reportService.getCustomerStatsReport(map));
			model.addAttribute("reportUrl", "customerStatsReportPg.do");
			model.addAttribute("reportTitle", "Customer Stats Report");
			model.addAttribute("reportTypeObj", "customerStats");
			setCookiesValue(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" customerStatsReportPg : End ");
		return "customerStatsReport";
	}

	@RequestMapping("/getreport")
	public void getreport(@RequestParam("type") String type, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		logger.info(" getreport : start");
		Map<String, String> map = new HashMap<>();
		Map<String, String> mapp = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			String groupObj = request.getParameter("groupObj");
			String branchObj = request.getParameter("branchObj");
			String fdateObj = strUtility.chageDateFormat(request.getParameter("fdateObj"));
			String edateObj = strUtility.chageDateFormat(request.getParameter("edateObj"));
			String reportTypeObj = request.getParameter("reportTypeObj");
			String filename = null;
			String userid = usr.getUsrId();

			map.put("fdate", fdateObj);
			map.put("tdate", edateObj);
			map.put("type", type);
			map.put("userid", userid);
			if (groupObj.equalsIgnoreCase("0"))
				groupObj = "";
			if (branchObj.equalsIgnoreCase("0"))
				branchObj = "";
			map.put("groupId", groupObj);
			map.put("branchId", branchObj);
			if (groupObj.trim().length() == 0 && branchObj.trim().length() == 0) {
				filename = "ALL Group";
				map.put("groupname", filename);
			} else if (groupObj.trim().length() > 0 && branchObj.trim().length() == 0) {
				if (groupObj.trim().length() == 1)
					filename = groupService.getGroupDetails(Integer.parseInt(groupObj)).getDescription().trim();
				else
					filename = groupObj.split(",").length + " Group Selected";
				map.put("groupname", filename);
			} else {

				if ((groupObj.trim().length() > 0 && branchObj.split(",").length > 1)) {
					if (groupObj.split(",").length == 1)
						filename = groupService.getGroupDetails(Integer.parseInt(groupObj)).getDescription().trim();
					else
						filename = groupObj.split(",").length + " Group Selected";
					map.put("groupname", filename);
				} else {

					if (groupObj.trim().length() == 0 && branchObj.split(",").length > 1) {
						filename = branchObj.split(",").length + " Branch Selected";
						map.put("groupname", filename);
					} else {
						mapp.put("branchId", map.get("branchId"));
						List<BranchDetails> brnchdetails = branchService.searchBranchDetails(mapp);
						map.put("groupname",
								groupService.getGroupDetails(brnchdetails.get(0).getGroupId()).getDescription());
						filename = brnchdetails.get(0).getDescription()
								.substring(0, brnchdetails.get(0).getDescription().length() - 8).trim();
					}
				}
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			long timeStampLong = timestamp.getTime();
			if(reportTypeObj.equalsIgnoreCase("vat") ||reportTypeObj.equalsIgnoreCase("sage"))
			{
				filename = groupService.getGroupDetails(Integer.parseInt(groupObj)).getDescription().trim()+"_"+reportTypeObj.toUpperCase()+"_"+timeStampLong+"." + type;
			}else
			filename += " " + reportTypeObj.toUpperCase() +"_"+timeStampLong+ "." + type;
			
			filename = filename.replace(' ', '_');
			response.setContentType("application/" + type);
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);

			if (strUtility.checkNullOrEmptyString(reportTypeObj) && reportTypeObj.equals("monthlyCash")) {
				if (type.equals("xls"))
					response.getOutputStream().write(excelReportService.generateMonthlyCashReport(map, response));
				else
					response.getOutputStream().write(reportService.generateReport(map, response));
			}
			if (strUtility.checkNullOrEmptyString(reportTypeObj) && reportTypeObj.equals("cash")) {
				if (type.equals("xls"))
					response.getOutputStream().write(excelReportService.generateCashReport(map, response));
				else
					response.getOutputStream().write(reportService.generateReport(map, response));
			}
			if (strUtility.checkNullOrEmptyString(reportTypeObj)
					&& (!reportTypeObj.equals("monthlyCash") && !reportTypeObj.equals("cash"))) {
				List<Report> reportList = reportService.getReportNameASList(reportTypeObj);
				if (reportList != null && reportList.size() > 0) {
					if (type.equals("xls"))
						response.getOutputStream()
								.write(excelReportService.generatecommanFileReport(map, response, reportList));
					else
						response.getOutputStream().write(reportService.generateReport(map, response));
				}
			}
			/*
			 * if (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("monthlyNhs")) { if (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateMonthlyNhSReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); }
			 * 
			 * if (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("monthlyBanking")) { if (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateMonthlyBankingReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); } if
			 * (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("monthlycarehome")) { if
			 * (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateMonthlyCareHomesReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); } if
			 * (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("monthlyexpenses")) { if
			 * (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateMonthlyExpensesReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); } if
			 * (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("monthlycolldel")) { if (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateMonthlyCollDelvReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); } if
			 * (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("monthlyVat")) { if (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateMonthlyVATReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); } if
			 * (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("customerStats")) { if (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateCustomerStatsReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); } if
			 * (strUtility.checkNullOrEmptyString(reportTypeObj) &&
			 * reportTypeObj.equals("monthlycoupon")) { if (type.equals("xls"))
			 * response.getOutputStream().write(excelReportService.
			 * generateMonthlyCouponsReport(map, response)); else
			 * response.getOutputStream().write(reportService.generateReport(
			 * map, response)); }
			 */

		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info(" getreport : End ");
	}
	@RequestMapping("/amendmentTill.do")
	public String amendmentTillPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" monthlyCashReportPg : start ");

		Map<String, String> map = new HashMap<>();
		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;
			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& strUtility.checkNullOrEmptyString(request.getParameter("branchid"))) {
					branchid = request.getParameter("branchid");
				}
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");

				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}

				map.put("userid", userid);
			}
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", strUtility.getfirstdate(endDate));
			model.addAttribute("mode", "amend");
			model.addAttribute("enddate", endDate);
			map.put("userid", userid);

			model.addAttribute("monthlyReportList", reportService.getMonthlyCashReport(map));
			model.addAttribute("reportUrl", "amendmentTill.do");
			model.addAttribute("reportTitle", "Amendment Till Report");
			model.addAttribute("reportTypeObj", "monthlyCash");
			model.addAttribute("alertMsg", "If Pharmacy has done banking or End Of Month then HO will be able to do amendments.");
			setCookiesValue(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" monthlyCashReportPg : end ");
		return "amendmentTill";
	}
	@RequestMapping("/amendNhsReportPg")
	public String amendNhsReportPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" amendNhsReportPg : start ");

		Map<String, String> map = new HashMap<>();

		Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			cook = getCookiesValue(request, response);
			String sDate = null;
			String endDate = null;
			String groupid = null;
			String branchid = null;
			sDate = request.getParameter("sDate");
			endDate = request.getParameter("endDate");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
					&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
				sDate = strUtility.chageDateFormat(sDate);
				endDate = strUtility.chageDateFormat(endDate);
				if (groupid.equals("0")) {
					map.put("userid", userid);
					// branchid = "0";
				}
			} else {
				sDate = cook.get("fDate");
				endDate = cook.get("tDate");
				groupid = cook.get("groupId");
				branchid = cook.get("branchId");
				if (!strUtility.checkNullOrEmptyString(sDate) && !strUtility.checkNullOrEmptyString(endDate)
						&& !strUtility.checkNullOrEmptyString(groupid)
						&& !strUtility.checkNullOrEmptyString(branchid)) {
					endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
					groupid = "0";
					branchid = "0";
				}
				map.put("userid", userid);
				sDate = strUtility.getfirstdate(endDate);
			}
			map.put("fDate", sDate);
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "0");

			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("fdate", sDate);
			model.addAttribute("enddate", endDate);
			model.addAttribute("reportType", "amend");
			map.put("userid", userid);
			model.addAttribute("nhslist", reportService.getNHSReport(map));
			model.addAttribute("reportUrl", "amendNhsReportPg.do");
			model.addAttribute("reportTitle", "Amendments NHS Report");
			model.addAttribute("reportTypeObj", "monthlyNhs");
			setCookiesValue(request, response, map);

		} catch (Exception e)

		{
			e.printStackTrace();
		}
		logger.info(" nhsReportPg : end ");
		return "amendnhsReport";

	}
	@RequestMapping("/amendmentsNhsPg")
	public String amendmentsNhsPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" amendmentsNhsPg : start ");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		Map<String, String> map = new HashMap<>();
		Map<String, String> cook = new HashMap<>();
		cook = getCookiesValue(request, response);
		String sDate = (String) cook.get("fDate");
		String endDate = (String) cook.get("tDate");
		String groupid = "0";
		String branchid = request.getParameter("id");

		if (strUtility.checkNullOrEmptyString(sDate) && strUtility.checkNullOrEmptyString(endDate)
				&& strUtility.checkNullOrEmptyString(groupid) && strUtility.checkNullOrEmptyString(branchid)) {
			map.put("fDate", strUtility.getfirstdate(endDate));
			map.put("tDate", endDate);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("mode", "1");
		}
		map.put("userid", "");
		model.addAttribute("reportType", "amend");
		model.addAttribute("reportUrl", "amendNhsReportPg.do");
		model.addAttribute("nhslist", reportService.getNHSReport(map));
		model.addAttribute("branch", branchService.getBranchDetails(Integer.parseInt(branchid)).getDescription());
		model.addAttribute("mode", "Edit");
		logger.info(" nhsReportPg : end ");
		return "amendnhsReport";
	}
	public Map<String, String> getCookiesValue(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<>();
		Cookie cookie = null;
		Cookie[] cookies = null;
		cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];

				map.put(cookie.getName(), cookie.getValue());
			}
		}
		return map;
	}

	public void setCookiesValue(HttpServletRequest request, HttpServletResponse response, Map<String, String> map) {
		Cookie groupId = new Cookie("groupId", map.get("groupid"));
		Cookie branchId = new Cookie("branchId", map.get("branchid"));
		Cookie fDate = new Cookie("fDate", map.get("fDate"));
		Cookie tDate = new Cookie("tDate", map.get("tDate"));
		groupId.setMaxAge(60 * 60 * 24);
		branchId.setMaxAge(60 * 60 * 24);
		fDate.setMaxAge(60 * 60 * 24);
		tDate.setMaxAge(60 * 60 * 24);
		response.addCookie(groupId);
		response.addCookie(branchId);
		response.addCookie(fDate);
		response.addCookie(tDate);
	}
}
