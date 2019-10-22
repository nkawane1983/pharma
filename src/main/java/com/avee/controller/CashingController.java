package com.avee.controller;

import java.io.StringWriter;
import java.text.DateFormat;
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
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AmendmentTill;
import com.avee.form.AmendmentTillHO;
import com.avee.form.AppUser;
import com.avee.form.BranchDetails;
import com.avee.form.Cashing;
import com.avee.form.Denomination;
import com.avee.form.SearchForm;
import com.avee.form.TakingsCash;
import com.avee.service.BranchService;
import com.avee.service.CashingService;
import com.avee.service.CategoryService;
import com.avee.service.DefaultsService;
import com.avee.service.LookingService;
import com.avee.service.NHSService;
import com.avee.service.PaidOutsService;
import com.avee.service.SysParamService;
import com.avee.service.TakingCashService;
import com.avee.service.TakingsCardsService;
import com.avee.service.TakingsChequesService;
import com.avee.service.TakingsCouponsService;
import com.avee.service.UserService;
import com.avee.utility.EmailUtil;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.CashingValidator;

@Controller
@RequestMapping(value = "/cashing")
public class CashingController {

	static final Logger logger = LogManager.getLogger(CashingController.class.getName());

	@Autowired
	private StringUtility strUtility;

	@Autowired
	CashingService cashingService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	LookingService lookingService;

	@Autowired
	TakingCashService takingCashService;

	@Autowired
	TakingsChequesService takingsChequesService;

	@Autowired
	TakingsCardsService takingsCardsService;

	@Autowired
	TakingsCouponsService takingsCouponsService;

	@Autowired
	PaidOutsService paidoutsService;

	@Autowired
	UserService userService;

	@Autowired
	BranchService branchService;

	@Autowired
	SysParamService sysParamService;

	@Autowired
	NHSService nhsService;

	@Autowired
	DefaultsService defaultsService;

	@Autowired
	private EmailUtil emailUtil;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
		binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
		binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
		binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		// tell spring to set empty values as null instead of empty string.
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpSession session, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		logger.info(" searchPg : start ");

		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");

		Map<String, String> searchMap = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

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
					model.addAttribute("searchTitle", "Cashing");
					model.addAttribute("addBtnUrl", "cashing/newcashingPg.do");
					model.addAttribute("searchUrl", "cashing/searchCashing.do");
					return "cashingSearch";
				} else
					{
						searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
					}
			} else {
					searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			}

			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));
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
			List<Cashing> cashingList = cashingService.searchCashing(searchMap);
			model.addAttribute("cashingList", cashingList);
			model.addAttribute("fdate", sDate);
			model.addAttribute("enddate", endDate);
			model.addAttribute("searchTitle", "Cashing");
			model.addAttribute("addBtnUrl", "cashing/newcashingPg.do");
			model.addAttribute("searchUrl", "cashing/searchCashing.do");

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(" searchPg : end ");
		return "cashingSearch";
	}

	@RequestMapping("/searchCashing")
	public String searchCashing(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttributes) {
		logger.info(" searchCashing : start ");
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

			Map<String, String> searchMap = new HashMap<>();
			if (strUtility.getMonth() != branchdetails.getPeriod()) {
				boolean extendOrNot = false;
				String sessionObj = (String) session.getAttribute("extendOrNot");

				if (sessionObj != null)
					extendOrNot = Boolean.valueOf(sessionObj);
				if (extendOrNot == false) {
					model.addAttribute("alertMsgForMonthEnd",
							"Cannot enter any more data till End Of Month procedure has been run.");
					model.addAttribute("addBtnUrl", "cashing/newcashingPg.do");
					model.addAttribute("searchUrl", "cashing/searchCashing.do");
					return "cashingSearch";
				} else {
					String fDate = strUtility.chageDateFormat(request.getParameter("fDate"));
					String tDate = strUtility.chageDateFormat(request.getParameter("tDate"));

					searchMap.put("fDate", fDate);
					searchMap.put("tDate", tDate);
					searchMap.put("branchId", String.valueOf(branchdetails.getId()));
					List<Cashing> cashingList = cashingService.searchCashing(searchMap);
					// System.out.println(cashingList.size());
					model.addAttribute("cashingList", cashingList);
					model.addAttribute("fdate", fDate);
					model.addAttribute("enddate", tDate);
					model.addAttribute("searchTitle", "Cashing");
					model.addAttribute("addBtnUrl", "cashing/newcashingPg.do");
					model.addAttribute("searchUrl", "cashing/searchCashing.do");
					//
				}

			} else {
				String fDate = strUtility.chageDateFormat(request.getParameter("fDate"));
				String tDate = strUtility.chageDateFormat(request.getParameter("tDate"));

				searchMap.put("fDate", fDate);
				searchMap.put("tDate", tDate);
				searchMap.put("branchId", String.valueOf(branchdetails.getId()));
				List<Cashing> cashingList = cashingService.searchCashing(searchMap);
				// System.out.println(cashingList.size());
				model.addAttribute("cashingList", cashingList);
				model.addAttribute("fdate", fDate);
				model.addAttribute("enddate", tDate);
				model.addAttribute("searchTitle", "Cashing");
				model.addAttribute("addBtnUrl", "cashing/newcashingPg.do");
				model.addAttribute("searchUrl", "cashing/searchCashing.do");
				SearchForm searchForm = new SearchForm();
				searchForm.setStartDate(strUtility.covertStringToDate(fDate, "yyyy-MM-dd"));
				searchForm.setEndDate(strUtility.covertStringToDate(tDate, "yyyy-MM-dd"));
				session.setAttribute("searchForm", searchForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" searchCashing : end ");
		return "cashingSearch";
	}

	@RequestMapping("/newcashingPg")
	public String newCashingPg(@ModelAttribute("cashing") Cashing cashing, HttpServletRequest request, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info(" newCashing : start " + new Date());

		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");

		Map<String, String> searchMap = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			// Card List to See on page ManageCashing
			String till = request.getParameter("till");
			String date1 = request.getParameter("date1");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (strUtility.checkNullOrEmptyString(till) && strUtility.checkNullOrEmptyString(date1)) {// cashing.setDate(date1);
				cashing.setTillNo(Integer.parseInt(till));
				cashing.setDate(dateFormat.parse(strUtility.chageDateFormat(date1)));
				model.addAttribute("status", "m");
				searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			} else {

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
						model.addAttribute("addBtnUrl", "cashing/newcashingPg.do");
						model.addAttribute("searchUrl", "cashing/searchCashing.do");
						return "cashingSearch";
					} else {
						searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
						model.addAttribute("status", "add");
					}
				} else {
					model.addAttribute("status", "add");
					searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
				}
			}
			model.addAttribute("cards", categoryService.searchCategory("Cards"));
			model.addAttribute("cash", lookingService.getDenominationsAsList());
			// Coupons List to See on page ManageCashing
			model.addAttribute("couponslist", categoryService.searchCategory("Coupons"));
			// OtherIncome List to See on page ManageCashing
			model.addAttribute("otherIncomelist", "");
			// Expenses List to See on page ManageCashing
			model.addAttribute("paidoutlist", categoryService.searchCategory("PaidOuts"));

			model.addAttribute("tillno", branchdetails.getNoOfTills());

			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			searchMap.put("tillno", String.valueOf(branchdetails.getNoOfTills()));
			searchMap.put("cp",
					String.valueOf(branchdetails.getPeriod()) + "," + String.valueOf(branchdetails.getPeriod() + 1));

			List<Object[]> list = cashingService.searchRemainingCashing(searchMap);
			if(list!=null){
			logger.info("remainingCashingupList=>list.size()==>" + list.size());
			model.addAttribute("listSize",list.size());
			}
//			if (list.size() == 0) {
//				redirectAttributes.addFlashAttribute("successMessage", "Cashing data upto date.");
//				return "redirect:searchPg.do";
//			}
			if (!strUtility.checkNullOrEmptyString(till) && !strUtility.checkNullOrEmptyString(date1)) {
				if (list != null && list.size() > 0) {
					cashing.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(list.get(list.size() - 1)[0].toString()));
					cashing.setTillNo(Integer.parseInt(list.get(list.size() - 1)[1].toString()));
				}
			}
			model.addAttribute("remainingCashingupList", list);
			int cashingRemDay = cashingService.checkRemainingCashingUp(searchMap);

			model.addAttribute("cashsize", cashingRemDay);

			model.addAttribute("rembanking", cashingService.getUnBankingCashAndCheques(searchMap));
			// calculation vat
			model.addAttribute("stdRateVate", sysParamService.getSystemParameter("Std VAT Rate"));
			model.addAttribute("lowRateVate", sysParamService.getSystemParameter("Lower VAT Rate"));
			model.addAttribute("zeroRateVate", sysParamService.getSystemParameter("Zero VAT Rate"));
			model.addAttribute("exRateVate", sysParamService.getSystemParameter("Ex VAT Rate"));
			model.addAttribute("CashReminderPeriod", sysParamService.getSystemParameter("CashReminderPeriod"));
			searchMap.put("groupid", String.valueOf(branchdetails.getGroupId()));
			searchMap.put("defaultName", "floatvalue");
			cashing.setFloatvalue(Integer.parseInt(defaultsService.getdefaultsGroup(searchMap).get(0)[1].toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" newCashing : end " + new Date());
		return "manageCashing";
	}

	@RequestMapping(value = "/managecashing", params = "add")
	public String insertCashing(@ModelAttribute("cashing") Cashing cashing, Model model, HttpSession session,
			BindingResult result, RedirectAttributes redirectAttributes) {
		logger.info("insertCashing- start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");

			cashing.setUserId(usr.getUsrId());
			cashing.setBranchId(branchdetails.getId());

			cashing.setCreatedBy(usr.getUsrName());
			cashing.setCreatedDt(Calendar.getInstance().getTime());
			cashing.setUpdatedDt(Calendar.getInstance().getTime());
			cashing.setDeleteComment("");
			// System.out.println(cashing.getDate());
			Map<String, String> existCashingMap = new HashMap<>();
			existCashingMap.put("cashingdate", new SimpleDateFormat("yyyy-MM-dd").format(cashing.getDate()));
			existCashingMap.put("branchid", String.valueOf(cashing.getBranchId()));
			existCashingMap.put("tillno", String.valueOf(cashing.getTillNo()));

			if (cashingService.cashingExistOrNot(existCashingMap) == true) {
				redirectAttributes.addFlashAttribute("errorMessage", "This Record already Exists.");
				redirectAttributes.addFlashAttribute("tillno", branchdetails.getNoOfTills());
				return "redirect:newcashingPg";
			} else {
				CashingValidator validator = new CashingValidator();
				validator.validate(cashing, result);

				if (result.hasErrors()) {
					return "manageCashing";
				} else {
					cashing.setTillStatus(1);
					String successMessage = cashingService.insertCashing(cashing);
					if (strUtility.checkNullOrEmptyString(successMessage)) {
						redirectAttributes.addFlashAttribute("successMessage", successMessage);
					}
				}
			}

		} catch (NullPointerException nullPointer) {
			// System.out.print("NullPointerException caught");
			redirectAttributes.addFlashAttribute("errorMessage", "Cashing can not be inserted.");
		} catch (Exception e) {
			// System.out.print("NullPointerException caught");
			redirectAttributes.addFlashAttribute("errorMessage", "Cashing can not be inserted.");
		}
		logger.info("insertCashing - end");
		return "redirect:searchPg";
	}

	@SuppressWarnings("unused")
	@RequestMapping("/editCashingPg")
	public String editCashingPg(Model model, HttpSession session, HttpServletRequest request) {
		logger.info(" editCashingPg : start ");
		List<TakingsCash> takingCashlist = new ArrayList<>(12);
		List<Denomination> denomination = null;
		Cashing cashing = new Cashing();
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		if (branchdetails != null) {
			model.addAttribute("editmode", "editmode");
		} else {
			model.addAttribute("report", "report");
		}

		Map<String, String> searchMap = new HashMap<>();

		try {

			String id = request.getParameter("id");
			String msg = request.getParameter("msg");
			if (strUtility.checkNullOrEmptyString(id)) {
				if (branchdetails != null) {
					cashing = cashingService.getCashing(Integer.parseInt(id));
				} else {
					cashing = cashingService.getCashSummaryById(Integer.parseInt(id));
				}
				takingCashlist = takingCashService.searchTakingCash(Integer.parseInt(id));
				denomination = lookingService.getDenominationsAsList();
				model.addAttribute("cash", strUtility.initializeDenomination(denomination, takingCashlist));
				cashing.setTakingscash(strUtility.initialize(takingCashlist));
				cashing.setTakingscheques(takingsChequesService.searchTakingsCheques(Integer.parseInt(id)));
				cashing.setTakingscards(takingsCardsService.searchTakingsCard(Integer.parseInt(id)));
				cashing.setTakingcoupons(takingsCouponsService.searchTakingsCoupon(Integer.parseInt(id)));
				cashing.setPaidouts(paidoutsService.searchPaidOuts(Integer.parseInt(id)));
				model.addAttribute("cards", categoryService.searchCategory("Cards"));
				model.addAttribute("couponslist", categoryService.searchCategory("Coupons"));
				model.addAttribute("paidoutlist", categoryService.searchCategory("PaidOuts"));

				if (branchdetails != null) {
					model.addAttribute("tillno", branchdetails.getNoOfTills());
					searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));
					searchMap.put("branchId", String.valueOf(branchdetails.getId()));
					searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
					model.addAttribute("rembanking", cashingService.getUnBankingCashAndCheques(searchMap));

				}

				model.addAttribute("stdRateVate", sysParamService.getSystemParameter("Std VAT Rate"));
				model.addAttribute("lowRateVate", sysParamService.getSystemParameter("Lower VAT Rate"));
				model.addAttribute("zeroRateVate", sysParamService.getSystemParameter("Zero VAT Rate"));
				model.addAttribute("exRateVate", sysParamService.getSystemParameter("Ex VAT Rate"));
				if (strUtility.checkNullOrEmptyString(msg)) {
					model.addAttribute("msg", "Incomplete Till ");
				}
			}
			if (cashing == null) {
				cashing = new Cashing();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("cashing", cashing);

		model.addAttribute("mode", "update");
		logger.info(" editCashingPg : end ");
		return "manageCashing";
	}

	@RequestMapping(value = "/managecashing", params = "edit")
	public String updateCashing(@ModelAttribute("cashing") Cashing cashing, Model model, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		logger.info("updateCashing- start");
		String strObj = "";
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");

			CashingValidator validator = new CashingValidator();
			validator.validate(cashing, result);

			if (result.hasErrors()) {
				return "redirect:editCashingPg?id=" + cashing.getId();
			} else {
				String tillStatusIncmp=request.getParameter("tillStatusIncomplete");
				cashing.setUserId(usr.getUsrId());
				cashing.setBranchId(branchdetails.getId());
				cashing.setUpdatedBy(usr.getUsrName());
				cashing.setUpdatedDt(Calendar.getInstance().getTime());
				cashing.setTillStatus(1);
				if(strUtility.checkNullOrEmptyString(tillStatusIncmp)){
					boolean boolObj=cashingService.checkChashingCompleteOrNot(cashing.getId(),cashing.getBranchId());
					if(boolObj)
						strObj = cashingService.updateCashing(cashing);
					else
					{
						redirectAttributes.addFlashAttribute("errorMessage", "Incomplete Till already done. please refresh page.");
						return "redirect:searchPg";
					}
				}else
					strObj = cashingService.updateCashing(cashing);
				
				if (strObj.equalsIgnoreCase("SUCCESS"))
					redirectAttributes.addFlashAttribute("successMessage", "Cashing update successfully.");
				else
					redirectAttributes.addFlashAttribute("errorMessage", "Cashing did not update.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cashing did not Update.");
		}
		logger.info("updateCashing - end");
		return "redirect:searchPg";
	}

	@RequestMapping(value = "/managecashing", params = "update")
	public String editCashing(@ModelAttribute("cashing") Cashing cashing, Model model, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("updateCashing- start");

		try {

			CashingValidator validator = new CashingValidator();
			validator.validate(cashing, result);

			if (result.hasErrors()) {
				return "managenhs";
			} else {
				// AppUser usr = (AppUser) session.getAttribute("userInfo");
				String comment = cashing.getDeleteComment();
				cashing.setDeleteComment(comment);
				String status = cashingService.editCashing(cashing);
				if (status.equalsIgnoreCase("SUCCESS"))
					redirectAttributes.addFlashAttribute("successMessage", "Cashing Update successfully.");
				else
					redirectAttributes.addFlashAttribute("errorMessage", "Cashing did not Update.");

			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cashing did not Update.");
		}
		logger.info("updateCashing - end");
		return "redirect:editCashingPg.do?id=" + cashing.getId();
	}

	@RequestMapping(value = "/deleteCashing")
	public String deleteCashing(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		logger.info("deleteCashing - start");

		try {

			String id = request.getParameter("id");
			if (strUtility.checkNullOrEmptyString(id)) {
				cashingService.deleteCashing(Integer.parseInt(id));
				redirectAttributes.addFlashAttribute("successMessage", "Cashing Delete successfully.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cashing did not Delete.");
		}

		logger.info("deleteCashing - end");
		return "redirect:searchPg";
	}

	@SuppressWarnings("unused")
	@RequestMapping("/updateCashingTillPg")
	public String updateCashingTillPg(Model model, HttpSession session, HttpServletRequest request) {
		logger.info(" updateCashingTillPg : start ");
		List<TakingsCash> takingCashlist = new ArrayList<>(12);
		List<Denomination> denomination = null;
		Cashing cashing = new Cashing();
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */

		//Map<String, String> searchMap = new HashMap<>();

		try {

			String id = request.getParameter("id");
			String amdId = request.getParameter("msg");
			String modeId = request.getParameter("modeid");
			if (strUtility.checkNullOrEmptyString(modeId)) {
				model.addAttribute("mode", "amend");
				cashing = cashingService.getCashSummaryById(Integer.parseInt(modeId));

				takingCashlist = takingCashService.searchTakingCash(Integer.parseInt(modeId));
				denomination = lookingService.getDenominationsAsList();
				model.addAttribute("cash", strUtility.initializeDenomination(denomination, takingCashlist));
				cashing.setTakingscash(strUtility.initialize(takingCashlist));
				cashing.setTakingscheques(takingsChequesService.searchTakingsCheques(Integer.parseInt(modeId)));
				cashing.setTakingscards(takingsCardsService.searchTakingsCard(Integer.parseInt(modeId)));
				cashing.setTakingcoupons(takingsCouponsService.searchTakingsCoupon(Integer.parseInt(modeId)));
				cashing.setPaidouts(paidoutsService.searchPaidOuts(Integer.parseInt(modeId)));
				model.addAttribute("cards", categoryService.searchCategory("Cards"));
				model.addAttribute("couponslist", categoryService.searchCategory("Coupons"));
				model.addAttribute("paidoutlist", categoryService.searchCategory("PaidOuts"));

				model.addAttribute("stdRateVate", sysParamService.getSystemParameter("Std VAT Rate"));
				model.addAttribute("lowRateVate", sysParamService.getSystemParameter("Lower VAT Rate"));
				model.addAttribute("zeroRateVate", sysParamService.getSystemParameter("Zero VAT Rate"));
				model.addAttribute("exRateVate", sysParamService.getSystemParameter("Ex VAT Rate"));
				model.addAttribute("cashing", cashing);

			} else {
				if (strUtility.checkNullOrEmptyString(id) && strUtility.checkNullOrEmptyString(amdId)) {

					cashing = cashingService.getCashing(Integer.parseInt(id));
					AmendmentTill amendmentTill = new AmendmentTill();
					amendmentTill.setStatus(1);
					amendmentTill.setUpdatedBy(usr.getUsrId());
					amendmentTill.setWorkingBy(usr.getUsrId());
					amendmentTill.setUpdatedDt(Calendar.getInstance().getTime());
					amendmentTill.setId(Integer.parseInt(amdId));
					if (cashing != null) {
						String msg = cashingService.updateAmendmentTilRequest(amendmentTill);
						model.addAttribute("amdId", amdId);
						model.addAttribute("mode", "amendTill");
					}
				}
				if (cashing == null) {
					cashing = new Cashing();
				}
				model.addAttribute("cashing", cashing);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(" updateCashingTillPg : end ");
		return "updateCashingTill";
	}

	@RequestMapping(value = "/managecashing", params = "updateTill")
	public String updateCashingTillPg(@ModelAttribute("cashing") Cashing cashing, Model model, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		logger.info("updateCashingTillPg- start");
		String bodyObj="";
		try {

			CashingValidator validator = new CashingValidator();
			validator.validate(cashing, result);

			if (result.hasErrors()) {
				return "managenhs";
			} else {
				String noteRequest = request.getParameter("noteRequest");
				String modeAmd = request.getParameter("modeAmd");
				String status = "";
				String message = "Cashing did not Update.";
				AppUser usr = (AppUser) session.getAttribute("userInfo");
				if (strUtility.checkNullOrEmptyString(modeAmd) && strUtility.checkNullOrEmptyString(noteRequest)) {

					AmendmentTillHO amendmentTillHO = new AmendmentTillHO();
					AmendmentTillHO amedUpdate = new AmendmentTillHO();
					amedUpdate=cashingService.getAmendmentTillByHO(cashing.getId(), cashing.getBranchId());
					if (amedUpdate.getId()==0) {
						amendmentTillHO.setBranchId(cashing.getBranchId());
						amendmentTillHO.setCommentsByHO(noteRequest);
						amendmentTillHO.setCashId(cashing.getId());
						amendmentTillHO.setActionDate(Calendar.getInstance().getTime());
						amendmentTillHO.setCreatedBy(usr.getUsrId());
						amendmentTillHO.setCreatedDt(Calendar.getInstance().getTime());
						amendmentTillHO.setUpdatedDt(Calendar.getInstance().getTime());
						amendmentTillHO.setUserId(usr.getUsrId());
						status = cashingService.saveOrUpdateAmendmentTilByHO(amendmentTillHO, cashing,"Insert");
					}else
					{
						amedUpdate.setCommentsByHO(amedUpdate.getCommentsByHO()+","+noteRequest);
						amedUpdate.setUpdatedBy(usr.getUsrId());
						amedUpdate.setUpdatedDt(Calendar.getInstance().getTime());
						status = cashingService.saveOrUpdateAmendmentTilByHO(amedUpdate, cashing,"Update");
					}
					if (status.equalsIgnoreCase("SUCCESS")) {
						redirectAttributes.addFlashAttribute("alertMsg", "Cashing Update successfully.");
					} else {
						redirectAttributes.addFlashAttribute("alertMsg", "Cashing did not Update.");
					}
					return "redirect:/report/amendmentTill.do";
				} else {

					String amdId = request.getParameter("amdId");
					
					BranchDetails branchdetails = branchService.getBranchDetails(cashing.getBranchId());
					if (strUtility.checkNullOrEmptyString(noteRequest) && strUtility.checkNullOrEmptyString(amdId)) {
						AmendmentTill amendmentTill=cashingService.getAmendmentTilRequest(Integer.parseInt(amdId));
						if(amendmentTill.getStatus()!=2){
						cashing.setUpdatedDt(Calendar.getInstance().getTime());
						cashing.setUpdatedBy(usr.getUsrId());
						cashing.setTillStatus(0);
						status = cashingService.updateCashingByRequset(cashing, noteRequest, amdId);
						message="Cashing Update successfully.";
						bodyObj="<table><tr><td><strong>Date:</strong>"+strUtility.chageDateFormatPart(strUtility.covertDateToString(cashing.getDate(), "yyyy-MM-dd"),"yyyy-MM-dd","dd/MM/yyyy")+""
								+ "</td></tr><tr><td><strong>Till No:</strong>"
								+ ""+cashing.getTillNo()+"</td></tr><tr><td><strong>Reference No:</strong>"
								+cashing.getRefNo()+"</td></tr></table>";
						}
						else
						{
							message="Cashing Updated by unother.";
						}
					}

					if (status.equalsIgnoreCase("SUCCESS")) {
						String body = "We have done amendments as per your request "
								+ "Please open the pharmadmin and Complete the TILL.";

						sendMail(branchdetails, body, 1,bodyObj);
						redirectAttributes.addFlashAttribute("alertMsg",message);
					} else {
						redirectAttributes.addFlashAttribute("alertMsg", "message");
					}
					return "redirect:/notificationsPg.do";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMsg", "Cashing did not Update.");
		}
		logger.info("updateCashingTillPg - end");
		return "redirect:/dashboard";
	}

	/**
	 * This class working only send request from branch to H.O form
	 * AmendmentTill EPOST system data only
	 */
	@RequestMapping(value = "/sendRequstToHO")
	public String sendRequstToHOCashing(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,
			HttpSession session) {

		logger.info("sendRequstToHOCashing - start");
		String msg = "ERROR";
		String bodyObj="";
		boolean boolObj;
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			String managerName = request.getParameter("manager");
			String noteRequest = request.getParameter("noteRequest");
			String cashId = request.getParameter("cashId");

			if (strUtility.checkNullOrEmptyString(managerName) && strUtility.checkNullOrEmptyString(noteRequest)
					&& strUtility.checkNullOrEmptyString(cashId)) {
				boolObj=cashingService.checkAmendmentTilRequestExistOrNot(cashId, branchdetails.getId());
				if(boolObj){
				AmendmentTill amendmentTill = new AmendmentTill();
				amendmentTill.setBranchId(branchdetails.getId());
				amendmentTill.setUserId(usr.getUsrId());
				amendmentTill.setActionDate(Calendar.getInstance().getTime());
				amendmentTill.setCashId(Integer.parseInt(cashId));
				amendmentTill.setStatus(0);
				amendmentTill.setNotes(noteRequest);
				amendmentTill.setManager(managerName);
				amendmentTill.setCreatedBy(usr.getUsrId());
				amendmentTill.setCreatedDt(Calendar.getInstance().getTime());
				amendmentTill.setUpdatedDt(Calendar.getInstance().getTime());
				String[]objArray = cashingService.insertAmendmentTilRequest(amendmentTill);
				
				msg=objArray[0];
				bodyObj=objArray[1];
				
			
			
			if (msg.equalsIgnoreCase("SUCCESS")) {
				String body = "The Pharmacist has requested to do amendments on TILL "
						+ "for Pharmadmin which the Pharmacist was unable to process.";
				sendMail(branchdetails, body, 0,bodyObj);
				redirectAttributes.addFlashAttribute("alertMsg", "Your requset send successfully.");
			} else
				{
					redirectAttributes.addFlashAttribute("alertMsg", "Your requset not accepted.");
				}
			}else
				{
					redirectAttributes.addFlashAttribute("alertMsg", "Already  requset send. please refresh page.");
				}
		}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMsg", "Your requset not accepted.");
		}

		logger.info("sendRequstToHOCashing - end");
		return "redirect:/notificationsPg.do";
	}

	public void sendMail(BranchDetails branchdetails, String msg, int mode,String bodyObj) {
		Map<String, Object> map = new HashMap<>();
		String username = "";
		String createdBy = "";
		String to ="";
		String from = sysParamService.getSystemParameter("EMAIL_FROM").getParameterValue();
		String host = sysParamService.getSystemParameter("HOST").getParameterValue();
		String port = sysParamService.getSystemParameter("PORT").getParameterValue();
		String pwd = sysParamService.getSystemParameter("SMTP_Password").getParameterValue();
		String subject = "";
		if (mode == 0) {
			 to = sysParamService.getSystemParameter("TO").getParameterValue();
			map.put("to", to);
			username = "Head Office";
			subject = "AmendmentTill Request By " + branchdetails.getDescription();
			createdBy = branchdetails.getDescription();
		}
		if (mode == 1) {
			to = branchdetails.getEmail()+","+sysParamService.getSystemParameter("TO").getParameterValue();
			map.put("to", to);
			username = branchdetails.getDescription();
			subject = "AmendmentTill Approved By Head Office";
			createdBy = "Head Office";

		}

		map.put("from", from);
		map.put("host", host);
		map.put("port", port);
		String emailBody = getRestString(username, msg.trim(), createdBy,bodyObj);
		map.put("url", emailBody);
		map.put("subject", subject);
		map.put("pwd", pwd);
		if(to !=null)
			emailUtil.sendMailConfig(map);
	}

	public String getRestString(String username, String msg, String createdBy,String bodyObj) {
		StringWriter writer = null;
		try {
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("user", username);
			context.put("msg", msg);
			context.put("bodyObj", bodyObj);
			context.put("createdBy", createdBy);
			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/mailtamp.vm", "utf-8", context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer == null ? "" : writer.toString();
	}
}
