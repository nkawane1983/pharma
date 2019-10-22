package com.avee.controller;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.velocity.tools.generic.NumberTool;
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
import com.avee.form.Banking;
import com.avee.form.BankingOutstanging;
import com.avee.form.BranchDetails;

import com.avee.service.BankingService;
import com.avee.service.BranchService;
import com.avee.service.CashingService;
import com.avee.service.CategoryService;
import com.avee.service.LookingService;
import com.avee.service.NHSService;
import com.avee.service.PaidOutsService;
import com.avee.service.SysParamService;
import com.avee.service.TakingCashService;
import com.avee.service.TakingsCardsService;
import com.avee.service.TakingsChequesService;
import com.avee.service.UserService;

import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.BankingValidator;

import com.avee.form.Cashing;
import com.avee.form.Denomination;
import com.avee.form.PaidOuts;
import com.avee.form.SearchForm;
import com.avee.form.TakingsCash;
import com.avee.form.TakingsCheques;

@Controller
@RequestMapping(value = "/banking")
public class BankingController {

	static final Logger logger = LogManager.getLogger(BankingController.class.getName());

	@Autowired
	CashingService cashingService;

	@Autowired
	LookingService lookingService;

	@Autowired
	BankingService bankingService;

	@Autowired
	private StringUtility strUtility;

	@Autowired
	TakingCashService takingCashService;

	@Autowired
	TakingsChequesService takingsChequesService;

	@Autowired
	TakingsCardsService takingsCardsService;

	@Autowired
	UserService userService;

	@Autowired
	BranchService branchService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	NHSService nhsService;

	@Autowired
	PaidOutsService paidoutsService;

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

	/*
	 * Display Banking Search Page
	 */

	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info(" searchPg : start " + new Date());
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		AppUser usr = (AppUser) session.getAttribute("userInfo");
		Map<String, String> searchMap = new HashMap<>();
		try {
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			if (strUtility.getMonth() != branchdetails.getPeriod()) {
				int extendNoOfDay = Integer
						.parseInt(sysParamService.getSystemParameter("ExtendNoOfDay").getParameterValue());
				boolean extendOrNot = false;
				String sessionObj = (String) session.getAttribute("extendOrNot");

				if (sessionObj != null)
					extendOrNot = Boolean.valueOf(sessionObj);
				if (extendOrNot == false) {

					model.addAttribute("alertMsgForMonthEnd",
							"Cannot enter any more data till End Of Month procedure has been run.");
					return "bankingSearch";
				} else
					searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
			} else {
				searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			}

			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));
			// searchMap.put("tDate",
			// strUtility.getEndDate(branchdetails.getPeriod()));
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			searchMap.put("tillno", String.valueOf(branchdetails.getNoOfTills()));
			searchMap.put("cp", String.valueOf(branchdetails.getPeriod()));

			// get CashingRemaining days
			int cashingRemDay = cashingService.checkRemainingCashingUp(searchMap);

			int cashreminder = Integer
					.parseInt(sysParamService.getSystemParameter("CashReminderPeriod").getParameterValue());

			if (cashingRemDay > 0) {
				redirectAttributes.addFlashAttribute("alertMsg", "Cash Data has not been entered for " + cashingRemDay
						+ " days. All Data upto the last " + cashreminder + " days must first be entered.");
				return "redirect:/cashing/newcashingPg.do";

			}

			int nshRemDay = nhsService.checkRemainingNHS(searchMap);
			int nhsreminder = Integer
					.parseInt(sysParamService.getSystemParameter("NhsReminderPeriod").getParameterValue());
			if (nshRemDay > 0) {
				redirectAttributes.addFlashAttribute("alertMsg", "NHS Data has not been entered for " + nshRemDay
						+ " days. All Data upto the last " + nhsreminder + " days must first be entered.");
				return "redirect:/nhs/newNHSPg.do";

			}

			int bankingSize = cashingService.unBankingCashingAsList("all", searchMap).size();
			int bankingreminder = Integer
					.parseInt(sysParamService.getSystemParameter("BankingReminderPeriod").getParameterValue());
			if (bankingSize > bankingreminder) {

				redirectAttributes.addFlashAttribute("alertMsg", "Banking Data has not been entered for " + bankingSize
						+ " days. All Data upto the last " + bankingreminder + " days must first be entered.");
				return "redirect:/banking/newBankingPg.do?cashid=all";

			}

			String sDate = strUtility.getDate(strUtility.getMonth());
			String endDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			SearchForm searchForm = (SearchForm) session.getAttribute("searchForm");
			if (searchForm != null) {
				sDate = strUtility.covertDateToString(searchForm.getStartDate(), "yyyy-MM-dd");
				endDate = strUtility.covertDateToString(searchForm.getEndDate(), "yyyy-MM-dd");

			}
			searchMap.put("fDate", sDate);
			searchMap.put("tDate", endDate);
			List<Banking> bankingList = bankingService.searchBanking(searchMap);
			model.addAttribute("bankingList", bankingList);
			model.addAttribute("fdate", sDate);
			model.addAttribute("enddate", endDate);
			model.addAttribute("searchTitle", "Banking");
			model.addAttribute("addBtnUrl", "banking/newBankingPg.do");
			model.addAttribute("searchUrl", "banking/searchBanking.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" searchPg : end " + new Date());
		return "bankingSearch";
	}

	/*
	 * Display Banking search by From Date to End date
	 */
	@RequestMapping("/searchBanking")
	public String searchBanking(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" searchBanking : start ");
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		try {

			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String fDate = strUtility.chageDateFormat(request.getParameter("fDate"));
			String tDate = strUtility.chageDateFormat(request.getParameter("tDate"));
			String refNo = request.getParameter("refNo");

			Map<String, String> searchMap = new HashMap<>();
			searchMap.put("fDate", fDate);
			searchMap.put("tDate", tDate);
			searchMap.put("refNo", refNo);
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			// get banking data by date
			List<Banking> bankingList = bankingService.searchBanking(searchMap);
			model.addAttribute("bankingList", bankingList);
			model.addAttribute("fdate", fDate);
			model.addAttribute("enddate", tDate);
			model.addAttribute("searchTitle", "Banking");
			model.addAttribute("addBtnUrl", "banking/newBankingPg.do");
			model.addAttribute("searchUrl", "banking/searchBanking.do");
			SearchForm searchForm = new SearchForm();
			searchForm.setStartDate(strUtility.covertStringToDate(fDate, "yyyy-MM-dd"));
			searchForm.setEndDate(strUtility.covertStringToDate(tDate, "yyyy-MM-dd"));
			session.setAttribute("searchForm", searchForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" searchBanking : end ");
		return "bankingSearch";
	}

	/*
	 * Create New Banking Page
	 * 
	 * see all unBanking Cash-summary on list
	 */
	@RequestMapping("/newBankingPg")
	public String newBankingPg(@ModelAttribute("banking") Banking banking, HttpServletRequest request, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info(" newBankingPg : start ");

		// get branchDetails
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");

		Map<String, String> searchMap = new HashMap<>();
		List<Cashing> unBanking = null;
		List<Denomination> denomination = null;
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String monthclosemsg = request.getParameter("monthclosemsg");
			if (strUtility.checkNullOrEmptyString(monthclosemsg)) {
				searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
				model.addAttribute("status", "m");
			} else {
				if (strUtility.getMonth() != branchdetails.getPeriod()) {
					int extendNoOfDay = Integer
							.parseInt(sysParamService.getSystemParameter("ExtendNoOfDay").getParameterValue());
					boolean extendOrNot = false;
					String sessionObj = (String) session.getAttribute("extendOrNot");

					if (sessionObj != null)
						extendOrNot = Boolean.valueOf(sessionObj);
					if (extendOrNot == false) {
						System.out.println("hello");
						model.addAttribute("alertMsgForMonthEnd",
								"Cannot enter any more data till End Of Month procedure has been run.");
						return "bankingSearch";
					} else
						searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
				} else {
					searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
				}
			}
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));

			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));

			// searchMap.put("tDate",
			// strUtility.getEndDate(branchdetails.getPeriod()));
			// get unBanking Data into cash-Summary as List

			unBanking = cashingService.unBankingCashingAsList("all", searchMap);
			String bname = branchdetails.getDescription();
			String ref = strUtility.getReferenceNumber(bname,
					bankingService.countTotalBanking(strUtility.getReferenceNum(bname), 0, branchdetails.getId()));
			banking.setBankingRef(ref);
			model.addAttribute("cashid", "all");
			model.addAttribute("BankingdisLimit",
					sysParamService.getSystemParameter("BankingdiscrepancyLimit").getParameterValue());
			model.addAttribute("cfamountLimitMAX",
					sysParamService.getSystemParameter("cfamountLimitMAX").getParameterValue());
			model.addAttribute("cfamountLimitMin",
					sysParamService.getSystemParameter("cfamountLimitMin").getParameterValue());
			// get Denomination As list into Denomination table
			denomination = lookingService.getDenominationsAsList();

			if (unBanking.size() == 0) {
				redirectAttributes.addFlashAttribute("errorMessage", "There is no New Banking");
				return "redirect:searchPg.do";
			} else {
				model.addAttribute("unbanking", unBanking);
				/*
				 * initialize Denomination into denomination list this is
				 * Utility class to initialize denomination
				 */

				for (int i = 0; i < unBanking.size(); i++) {
					if (unBanking.get(i).getCash() != 00 && unBanking.get(i).getTakingscash() != null) {

						denomination = strUtility.initializeDenomination(denomination,
								unBanking.get(i).getTakingscash());
					}
				}
			}
			// Expenses List to See on page ManageCashing
			model.addAttribute("paidoutlist", categoryService.searchCategory("PaidOuts"));
			model.addAttribute("cash", denomination);

			banking.setId(
					(bankingService.countTotalBanking(strUtility.getReferenceNum(bname), 2, branchdetails.getId())));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		logger.info(" newBankingPg : end ");
		return "manageBanking";

	}

	/*
	 * Save Banking Page
	 * 
	 * this banking Data save by Branch Id
	 */
	@RequestMapping(value = "/managebanking", params = "add")
	public String insertCashing(@ModelAttribute("banking") Banking banking, HttpServletRequest request, Model model,
			HttpSession session, BindingResult result, RedirectAttributes redirectAttributes) {
		logger.info("manageBanking- start");
		Map<String, String> searchMap = new HashMap<>();
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		// get branchDetails
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		try {

			banking.setUserId(usr.getUsrId());
			banking.setBranchId(branchdetails.getId());
			banking.setCreatedBy(usr.getUsrId());
			banking.setCreatedDt(Calendar.getInstance().getTime());
			banking.setUpdatedDt(Calendar.getInstance().getTime());
			banking.setDeleteComment("");
			String cashid = request.getParameter("cashid");
			banking.setDeleteComment("");
			String outsatanding = request.getParameter("outsatanding");
			String cfamount = request.getParameter("cfamount");
			String outstandingId = request.getParameter("outstandingId");
			BankingValidator validator = new BankingValidator();
			validator.validate(banking, result);

			if (result.hasErrors()) {
				return "manageBanking";
			} else {

				String messageObj = "";

				/*
				 * this is use for update Cash-summary
				 * ,takingCheques,takingCards by cashid
				 * 
				 */
				List<Cashing> unBanking = null;
				if (cashid.equals("all")) {

					try {
						searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));// convert
																								// number
																								// to
																								// Start
																								// date
						// searchMap.put("tDate",
						// strUtility.getEndDate(branchdetails.getPeriod()));//
						// convert
						// number
						// to
						// end
						// Date

						if (strUtility.getMonth() != branchdetails.getPeriod()) {
							int extendNoOfDay = Integer
									.parseInt(sysParamService.getSystemParameter("ExtendNoOfDay").getParameterValue());
							boolean extendOrNot = false;
							String sessionObj = (String) session.getAttribute("extendOrNot");

							if (sessionObj != null)
								extendOrNot = Boolean.valueOf(sessionObj);
							if (extendOrNot == false)
								searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
							else
								searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
						} else {
							searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
						}
						searchMap.put("branchId", String.valueOf(branchdetails.getId()));
						unBanking = cashingService.unBankingCashingAsList(cashid, searchMap);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if (unBanking != null && unBanking.size() > 0)
						messageObj = bankingService.insertBanking(banking, outsatanding, cfamount, cashid, unBanking,
								outstandingId);
				} else {
					int unbankingCount = bankingService.checkBankingByStringComma(cashid, branchdetails.getId());
					String[] commObj = cashid.split(",");
					if (commObj.length == unbankingCount)
						messageObj = bankingService.insertBanking(banking, outsatanding, cfamount, cashid, unBanking,
								outstandingId);
				}

				if (messageObj.equalsIgnoreCase("SUCCESS")) {
					redirectAttributes.addFlashAttribute("alertMsg", "The BankingID for this transaction is "
							+ banking.getBankingRef() + " .Please write this ID on the Giro Slip.");
					redirectAttributes.addFlashAttribute("successMessage", "Banking inserted successfully.");
				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "Banking can not be inserted.");
				}

			}
		} catch (NullPointerException nullPointer) {
			// System.out.print("NullPointerException caught");
			redirectAttributes.addFlashAttribute("errorMessage", "Banking can not be inserted.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Banking can not be inserted.");
		}
		logger.info("managebanking - end");
		return "redirect:searchPg";
	}

	/*
	 * View/Edit Banking Page
	 * 
	 * get banking Data by banking Id
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/editBanking")
	public String editBanking(Model model, HttpServletRequest request, HttpSession session) {
		List<Cashing> unbanking = null;
		List<Denomination> denomination = null;
		logger.info("editBanking - start");

		Banking banking = new Banking();
		denomination = lookingService.getDenominationsAsList();
		BankingOutstanging bankingOutstanging = new BankingOutstanging();
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
			String reportid = request.getParameter("reportid");
			if (id == null && reportid != null) {
				id = reportid;
				model.addAttribute("report", "update");
				String branchid = request.getParameter("branchid");
				if (strUtility.checkNullOrEmptyString(branchid) && branchid != null) {
					model.addAttribute("branchid", branchid);
				}
			}
			if (id != null && reportid == null) {
				model.addAttribute("mode", "update");
			}
			if (strUtility.checkNullOrEmptyString(id)) {
				banking = bankingService.getBanking(Integer.parseInt(id));// get
																			// banking
																			// by
																			// id
				bankingOutstanging = bankingService.getCarryForwordBanking(0, Integer.parseInt(id));
				/*
				 * get cash-summary by branchId,and bankingId as List
				 */
				unbanking = cashingService.bankingCashingAsList(banking.getBranchId(), banking.getId());
				/*
				 * initialize Denomination into denomination list this is
				 * Utility class to initialize denomination
				 */
				for (int i = 0; i < unbanking.size(); i++) {
					if (unbanking.get(i).getCash() != 00 && unbanking.get(i).getTakingscash() != null) {
						denomination = strUtility.initializeDenomination(denomination,
								unbanking.get(i).getTakingscash());
					}
				}

			}
			if (banking == null) {
				banking = new Banking();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("paidoutlist", categoryService.searchCategory("PaidOuts"));
		model.addAttribute("BankingdisLimit",
				sysParamService.getSystemParameter("BankingdiscrepancyLimit").getParameterValue());
		model.addAttribute("cfamountLimitMAX",
				sysParamService.getSystemParameter("cfamountLimitMAX").getParameterValue());
		model.addAttribute("cfamountLimitMin",
				sysParamService.getSystemParameter("cfamountLimitMin").getParameterValue());
		model.addAttribute("unbanking", unbanking);
		model.addAttribute("cash", denomination);
		model.addAttribute("banking", banking);
		model.addAttribute("outstangingAmount", bankingOutstanging);

		logger.info("editBanking - end");
		return "manageBanking";
	}

	@RequestMapping(value = "/getbankingList")
	public @ResponseBody String getbankingListByAjax(Model model, @RequestParam("cashid") String cashid,
			HttpSession session) {
		logger.info("in getbankingList - start");
		StringWriter writer = null;

		// get branchDetails by branchId

		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");

		Map<String, String> searchMap = new HashMap<>();
		List<Cashing> unBanking = null;
		List<TakingsCash> uncash = null;
		List<TakingsCheques> unCheques = null;
		List<PaidOuts> unPaidout = null;
		List<Denomination> denomination = null;
		denomination = lookingService.getDenominationsAsList();
		try {
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));

			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));

			if (strUtility.getMonth() != branchdetails.getPeriod()) {
				int extendNoOfDay = Integer
						.parseInt(sysParamService.getSystemParameter("ExtendNoOfDay").getParameterValue());
				boolean extendOrNot = false;
				String sessionObj = (String) session.getAttribute("extendOrNot");

				if (sessionObj != null)
					extendOrNot = Boolean.valueOf(sessionObj);
				if (extendOrNot == false)
					searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
				else
					searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
			} else {
				searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			}
			// searchMap.put("tDate",
			// strUtility.getEndDate(branchdetails.getPeriod()));

			searchMap.put("cashid", cashid);

			unBanking = cashingService.unBankingCashingAsList(cashid, searchMap);

			uncash = takingCashService.unBankingCashAsList(searchMap);
			unCheques = takingsChequesService.unBankingChequesAsList(searchMap);
			unPaidout = paidoutsService.unBankingPaidOutsAsList(searchMap);
			bankingService.getOutstangingBanking(branchdetails.getId(), 0);
			denomination = strUtility.initializeDenomination(denomination, uncash);
			model.addAttribute("cashid", cashid);
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("cashlist", denomination);
			context.put("unBanking", unBanking);
			context.put("unCheques", unCheques);
			context.put("unPaidout", unPaidout);
			context.put("outstangingAmount", bankingService.getOutstangingBanking(branchdetails.getId(), 0));
			context.put("numberTool", new NumberTool());
			context.put("paidoutlist", categoryService.searchCategory("PaidOuts"));
			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/cashList.vm", "utf-8", context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out getCashList - start");
		return writer == null ? "" : writer.toString();
	}

}
