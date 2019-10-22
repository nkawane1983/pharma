package com.avee.controller;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avee.form.AppUser;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BranchDetails;
import com.avee.form.CareHomesReport;
import com.avee.form.Cashing;
import com.avee.form.NHS;
import com.avee.form.ScriptItems;
import com.avee.form.Version;
import com.avee.service.BankingService;
import com.avee.service.BranchService;
import com.avee.service.CareHomeService;
import com.avee.service.CashingService;
import com.avee.service.GroupService;
import com.avee.service.LookingService;
import com.avee.service.NHSService;
import com.avee.service.SysParamService;
import com.avee.service.UserService;
import com.avee.service.UserTypeService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.utility.chart.ChartData;
import com.google.gson.Gson;

@Controller
public class DashboardController {

	static final Logger logger = LogManager.getLogger(DashboardController.class.getName());

	@Autowired
	CashingService cashingService;

	@Autowired
	NHSService nhsService;

	@Autowired
	BankingService bankingService;

	@Autowired
	BranchService branchService;

	@Autowired
	UserTypeService userTypeService;

	@Autowired
	UserService userService;

	@Autowired
	private StringUtility strUtility;

	@Autowired
	GroupService groupService;

	@Autowired
	LookingService lookingService;
	@Autowired
	private CareHomeService careHomeService;
	@Autowired
	SysParamService sysParamService;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
		binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
		binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
		binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	}

	@RequestMapping("/dashboard")
	public String viewDashboard(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("in dashboard ...");
		Map<String, String> searchMap = new HashMap<>();
		try {

			Calendar cal = Calendar.getInstance();

			AppUser usr = (AppUser) session.getAttribute("userInfo");

			searchMap.put("uid", usr.getUsrId());
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			int viewNoOfDay = Integer.parseInt(sysParamService.getSystemParameter("dashboardViewPeriod").getParameterValue());
			List<AppUserGroupBranchMapping> groupDetails = new ArrayList<AppUserGroupBranchMapping>();
			List<AppUserGroupBranchMapping> branchDetails = new ArrayList<AppUserGroupBranchMapping>();
			groupDetails = groupService.getGroupDetailsAsList(searchMap);
			searchMap.put("groupId", "0");
			searchMap.put("branchID", "0");
			branchDetails = branchService.getBranchDetailsaAsList(searchMap);
			// System.out.println(branchDetails.size());
			String branchIdObj = null;
			String groupIdObj = null;
			String sDateObj = null;
			String endDateObj = null;
			String useridObj = usr.getUsrId();

			model.addAttribute("branchlist", branchDetails);
			model.addAttribute("groupDetails", groupDetails);

			String userTypeName = userTypeService.getUserType(Integer.parseInt(usr.getUsrType()), true).getName();

			if (userTypeName.equals("SuperAdmin") || userTypeName.equals("Admin")) {

				groupIdObj = String.valueOf(0);
				branchIdObj = String.valueOf(0);
				session.setAttribute("workingOn", "(Act as " + userTypeName + ")");

				sDateObj = strUtility.getDate(strUtility.getMonth());
				endDateObj = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				searchMap.put("branchIdObj", "0");//all branch
				searchMap.put("modeObj", "1"); 
				searchMap.put("fDate", sDateObj);
				searchMap.put("tDate", endDateObj);
					List<Object[]> notifications=cashingService.getNotification(searchMap);
				
				if(notifications!=null&& notifications.size()>0)
				{
					session.setAttribute("notifications", notifications);
					session.setAttribute("notificationsLength", notifications.size());
				}

			} else {

				groupIdObj = String.valueOf(groupDetails.get(0).getGroupId());
				branchIdObj = String.valueOf(branchDetails.get(0).getBrnachid());

				session.setAttribute("gId", groupIdObj);
				session.setAttribute("gName", groupDetails.get(0).getGroupName());
				if (userTypeName.equals("Users"))
					session.setAttribute("workingOn", "(Act as User)");
				else
					session.setAttribute("workingOn", "(Act as Manager)");

				int period = branchService.getBranchDetails(branchDetails.get(0).getBrnachid()).getPeriod();

				sDateObj = strUtility.getDate(period);
				endDateObj = strUtility.getEndDate(period);

				session.setAttribute("branchDetails",
						branchService.getBranchDetails(branchDetails.get(0).getBrnachid()));

				searchMap.put("fDate", sDateObj);
				searchMap.put("tDate", endDateObj);

				searchMap.put("branchIdObj", branchIdObj);
				searchMap.put("modeObj", "0"); 
				List<Object[]> notifications=cashingService.getNotification(searchMap);
				
				if(notifications!=null&& notifications.size()>0)
				{
					session.setAttribute("notifications", notifications);
					session.setAttribute("notificationsLength", notifications.size());
				}

				useridObj = "";
				if (strUtility.getMonth() != period) {
				int extendNoOfDay = Integer
						.parseInt(sysParamService.getSystemParameter("ExtendNoOfDay").getParameterValue());
				if(strUtility.extendDateForEndMonth(extendNoOfDay, period))
					session.setAttribute("MsgextendOrNot", "true");
				else
					session.setAttribute("MsgextendOrNot", "false");
				}
			}
			cal.add(Calendar.DATE, -viewNoOfDay);
			searchMap.put("branchIdObj", branchIdObj);
			searchMap.put("groupIdObj", groupIdObj);
			searchMap.put("useridObj", useridObj);
			searchMap.put("fDate", sDateObj);
			searchMap.put("tDate", endDateObj);
			model.addAttribute("dateBefore", new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
			model.addAttribute("nhsStats", nhsService.getNhsStats(searchMap));
			model.addAttribute("dashgroup", lookingService.getdashboard(searchMap));
			session.setAttribute("branchlist", branchDetails);
			session.setAttribute("groupDetails", groupDetails);
			model.addAttribute("fdate", sDateObj);
			model.addAttribute("enddate", endDateObj);
			Version version=lookingService.getLatestVersion();
			session.setAttribute("version",version);
			logger.info("in dashboard ... END");
		} catch (

		Exception e)

		{
			e.printStackTrace();
		}
		return ("dashboard");

	}

	@RequestMapping("/monthClosurePg")
	public String viewMonthColsure(Model model, HttpSession session) {
		logger.info("in MonthColsure ...:start");
		Map<String, String> searchMap = new HashMap<>();
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		try {

			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));

			searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			searchMap.put("tillno", String.valueOf(branchdetails.getNoOfTills()));
			searchMap.put("cp", String.valueOf(branchdetails.getPeriod()));
			List<Cashing> cashingList = cashingService.searchCashing(searchMap);
			model.addAttribute("cashingList", cashingList);
			model.addAttribute("unbanking", cashingService.unBankingCashingAsList("all", searchMap));
			model.addAttribute("endmonth", branchdetails.getPeriod());
			List<NHS> nhsList = nhsService.searchNHSScript(searchMap);
			List<CareHomesReport> scriptItemsList = careHomeService.getCareHomesScriptItemsAsList(searchMap);
			// System.out.println(nhsList.size());
			// System.out.println(nhsList.get(nhsList.size()-1).getDate());
			// System.out.println(nhsList.size());
			int nhsSize = nhsList.size();
			int carehomeSize = scriptItemsList.size();// carehome size
			int carehomeId = 0;
			int nhsId = 0;
			if (nhsSize != 0) {
				nhsId = nhsList.get(nhsList.size() - 1).getId();
			}
			if (carehomeSize != 0) {
				carehomeId = scriptItemsList.get(scriptItemsList.size() - 1).getId();
			}
			List<CareHomesReport> carehomeList = careHomeService.getByCareHomeScriptItems(searchMap);
			model.addAttribute("carehomeASList", careHomeService.getCareHomesScriptItemsAsList(searchMap));
			model.addAttribute("carehomeId", carehomeId);
			model.addAttribute("nhsId", nhsId);
			model.addAttribute("carehomeList", carehomeList);
			model.addAttribute("nhsList", nhsList);
			model.addAttribute("remainingCashingupList", cashingService.searchRemainingCashing(searchMap));
			model.addAttribute("remainingNHSList", nhsService.searchRemainingNHSScript(searchMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("in MonthColsure ...:end");
		return ("monthclosurePg");
	}

	@RequestMapping("/manageMonthPg")
	public String updateMonthColsure(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("in updateMonthColsure ...:start");

		AppUser usr = (AppUser) session.getAttribute("userInfo");
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");

		try {
			String nextMonth = request.getParameter("nextmonth");
			String substanceMisuseP = request.getParameter("SubstanceMisuseP");
			String repeatDispensing = request.getParameter("RepeatDispensingP");
			String careHomes = request.getParameter("CareHomesP");
			String communityMDS = request.getParameter("CommunityMDSP");
			String nhsId = request.getParameter("nhsId");
			String careHomeId = request.getParameter("careHomeId");
			if (substanceMisuseP == null && !StringUtils.isNotBlank(substanceMisuseP)
					&& !StringUtils.isNotEmpty(substanceMisuseP))
				substanceMisuseP = "0";
			if (repeatDispensing == null && !StringUtils.isNotBlank(repeatDispensing)
					&& !StringUtils.isNotEmpty(repeatDispensing))
				repeatDispensing = "0";
			if (careHomes == null && !StringUtils.isNotBlank(careHomes) && !StringUtils.isNotEmpty(careHomes))
				careHomes = "0";
			if (communityMDS == null && !StringUtils.isNotBlank(communityMDS) && !StringUtils.isNotEmpty(communityMDS))
				communityMDS = "0";
			if (strUtility.checkNullOrEmptyString(nextMonth)) {
				if (nextMonth.equals("13"))
					nextMonth = "1";
				branchdetails.setId(branchdetails.getId());
				branchdetails.setUpdatedBy(usr.getUsrId());
				branchdetails.setUpdatedDt(Calendar.getInstance().getTime());
				branchdetails.setPeriod(Integer.parseInt(nextMonth));
				branchService.updateBranchDetails(branchdetails, "update");
			}

			if (nhsId != null && !nhsId.equalsIgnoreCase("0")) {
				NHS nhs = new NHS();
				nhs.setId(Integer.parseInt(nhsId));
				nhs.setSubMisPatients(Integer.parseInt(substanceMisuseP));
				nhs.setRepDisPatients(Integer.parseInt(repeatDispensing));
				nhs.setUpdatedBy(usr.getUsrId());
				nhs.setUpdatedDt(Calendar.getInstance().getTime());
				nhsService.updateNHSScript(nhs, "nhsMonthEndUpdate");

			}
			if (careHomeId != null && !careHomeId.equalsIgnoreCase("0")) {
				ScriptItems scriptItems = new ScriptItems();
				scriptItems.setId(Integer.parseInt(careHomeId));
				scriptItems.setNoPatients(Integer.parseInt(communityMDS));
				scriptItems.setNoCareHomePatients(Integer.parseInt(careHomes));
				scriptItems.setUpdatedBy(usr.getUsrId());
				scriptItems.setUpdatedDt(Calendar.getInstance().getTime());
				careHomeService.updateScriptItems(scriptItems, "scriptItemsMonthEndUpdate");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("in updateMonthColsure ...:end");
		return ("redirect:dashboard");
	}

	@RequestMapping(value = "/getDashboardList")
	public @ResponseBody String getDashboardListByAjax(Model model, @RequestParam("gid") int id,
			@RequestParam("bid") int bid, HttpSession session) {
		logger.info("in getDashboardList - start");
		StringWriter writer = null;
		try {

			Map<String, String> searchMap = new HashMap<>();

			List<Object[]> branchList = new ArrayList<>();

			AppUser usr = (AppUser) session.getAttribute("userInfo");

			String branchIdObj = String.valueOf(bid);
			String groupIdObj = String.valueOf(id);
			String useridObj = usr.getUsrId();

			searchMap.put("branchIdObj", branchIdObj);
			searchMap.put("groupIdObj", groupIdObj);

			if (groupIdObj.equals("0") && branchIdObj.equals("0"))
				searchMap.put("useridObj", useridObj);
			else
				searchMap.put("useridObj", "");

			branchList = lookingService.getdashboard(searchMap);
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("branchlist", branchList);

			context.put("period", strUtility.getMonth());
			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/dashboardList.vm", "utf-8", context, writer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out getDashboardList - start");
		return writer == null ? "" : writer.toString();
	}

	@RequestMapping(value = "/getNhsStateList")
	public @ResponseBody String getNhsStateByAjax(Model model, @RequestParam("gid") int gid,
			@RequestParam("bid") int bid, @RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate,
			HttpSession session) {
		logger.info("in getNhsStateList - start");
		StringWriter writer = null;
		try {
			Map<String, String> searchMap = new HashMap<>();
			List<Object[]> nhsList = new ArrayList<>();

			AppUser usr = (AppUser) session.getAttribute("userInfo");

			String branchIdObj = String.valueOf(bid);
			String groupIdObj = String.valueOf(bid);
			String useridObj = usr.getUsrId();

			searchMap.put("fDate", strUtility.chageDateFormat(fdate));
			searchMap.put("tDate", strUtility.chageDateFormat(tdate));

			searchMap.put("branchIdObj", branchIdObj);
			searchMap.put("groupIdObj", groupIdObj);

			if (groupIdObj.equals("0") && branchIdObj.equals("0"))
				searchMap.put("useridObj", useridObj);
			else
				searchMap.put("useridObj", "");

			nhsList = nhsService.getNhsStats(searchMap);

			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("nhslist", nhsList);
			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/nhsStateList.vm", "utf-8", context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out getNhsStateList - start");
		return writer == null ? "" : writer.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/nhsChartDailyData.do")
	public @ResponseBody String nhsChartDailyData(Model model, @RequestParam("gid") String gid,
			@RequestParam("bid") String bid) {
		logger.info("in nhsChartDailyData - start");

		try {
			int cIndex = 0;

			String[] data = null;
			String[] labels = null;
			Map<String, String> colorMap = new HashMap<String, String>();
			colorMap.put("nhs", "#F7464A");
			ChartData chartData = new ChartData();
			ChartData.Datasets[] dataSets = new ChartData.Datasets[1];
			chartData.setDatasets(dataSets);
			Map<String, Object> map = lookingService.getNhsChartDailyData(gid, bid);
			Map<String, Object> dataMap = (Map<String, Object>) map.get("dateAndpaid");
			String[] arrBGColor = new String[8];
			Arrays.fill(arrBGColor, colorMap.get("nhs"));
			if (dataMap != null && dataMap.size() > 0) {
				data = new String[dataMap.size()];
				labels = new String[dataMap.size()];
				int i = 0;
				for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
					String temp[] = entry.getKey().split("-");
					String date = temp[0] + " " + temp[1] + "-" + temp[2];
					data[i] = entry.getValue().toString();

					labels[i] = date;
					i++;

				}
			}

			ChartData.Datasets dataSet = chartData.new Datasets();
			dataSet.setData(data);
			dataSet.setBackgroundColor(arrBGColor);
			dataSets[cIndex++] = dataSet;

			chartData.setLabels(labels);

			Gson gson = new Gson();
			String json = gson.toJson(chartData);

			logger.info("out nhsChartDailyData - End");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/takingChartDailyData.do")
	public @ResponseBody String takingChartDailyData(Model model, @RequestParam("gid") String gid,
			@RequestParam("bid") String bid) {
		logger.info("in takingChartDailyData - start");

		try {
			int cIndex = 0;

			String[] data = null;
			String[] labels = null;
			Map<String, String> colorMap = new HashMap<String, String>();
			colorMap.put("taking", "#4f5f6f");
			ChartData chartData = new ChartData();
			ChartData.Datasets[] dataSets = new ChartData.Datasets[1];
			chartData.setDatasets(dataSets);
			Map<String, Object> map = lookingService.getTakingChartDailyData(gid, bid);
			Map<String, Object> dataMap = (Map<String, Object>) map.get("dateAndTaking");
			String[] arrBGColor = new String[8];
			Arrays.fill(arrBGColor, colorMap.get("taking"));
			if (dataMap != null && dataMap.size() > 0) {
				data = new String[dataMap.size()];
				labels = new String[dataMap.size()];
				int i = 0;
				for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
					String temp[] = entry.getKey().split("-");
					String date = temp[0] + " " + temp[1] + "-" + temp[2];
					data[i] = entry.getValue().toString();

					labels[i] = date;
					i++;

				}
			}

			ChartData.Datasets dataSet = chartData.new Datasets();
			dataSet.setData(data);
			dataSet.setBackgroundColor(arrBGColor);
			dataSets[cIndex++] = dataSet;

			chartData.setLabels(labels);

			Gson gson = new Gson();
			String json = gson.toJson(chartData);

			logger.info("out takingChartDailyData--- End");
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
