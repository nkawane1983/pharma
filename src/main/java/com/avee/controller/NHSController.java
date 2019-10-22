package com.avee.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.BranchDetails;

import com.avee.form.NHS;
import com.avee.form.SearchForm;
import com.avee.service.CashingService;
import com.avee.service.LookingService;
import com.avee.service.NHSService;
import com.avee.service.SysParamService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.NHSValidator;

@Controller
@RequestMapping(value = "/nhs")
public class NHSController {

	static final Logger logger = LogManager.getLogger(NHSController.class.getName());

	@Autowired
	private NHSService nhsService;

	@Autowired
	private CashingService cashingService;

	@Autowired
	private StringUtility strUtility;

	@Autowired
	private SysParamService sysParamService;

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
	public String searchPg(Model model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirectAttributes) {
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		Map<String, String> searchMap = new HashMap<>();

		logger.info(" searchPg : start ");
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
				boolean extendOrNot=false;
				String sessionObj=(String) session.getAttribute("extendOrNot");
				
				if(sessionObj!=null)
					extendOrNot=Boolean.valueOf(sessionObj);
				if (extendOrNot==false) {

					model.addAttribute("alertMsgForMonthEnd",
							"Cannot enter any more data till End Of Month procedure has been run.");
					model.addAttribute("searchTitle", "NHS");
					model.addAttribute("addBtnUrl", "nhs/newNHSPg.do");
					model.addAttribute("searchUrl", "nhs/searchNHS.do");
					return "nhsSearch";
				} else
					searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
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
				sDate=strUtility.covertDateToString(searchForm.getStartDate(), "yyyy-MM-dd");
				endDate=strUtility.covertDateToString(searchForm.getEndDate(), "yyyy-MM-dd");
				
			}
			searchMap.put("fDate", sDate);
			searchMap.put("tDate", endDate);
			List<NHS> nhsList = nhsService.searchNHSScript(searchMap);
			model.addAttribute("nhsList", nhsList);
			model.addAttribute("fdate", sDate);
			model.addAttribute("enddate", endDate);
			model.addAttribute("searchTitle", "NHS");
			model.addAttribute("addBtnUrl", "nhs/newNHSPg.do");
			model.addAttribute("searchUrl", "nhs/searchNHS.do");

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" searchPg : end ");
		return "nhsSearch";
	}

	@RequestMapping("/searchNHS")
	public String searchNHSScript(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" searchNHS : start ");
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
		
				boolean extendOrNot=false;
				String sessionObj=(String) session.getAttribute("extendOrNot");
				
				if(sessionObj!=null)
					extendOrNot=Boolean.valueOf(sessionObj);
				if (extendOrNot==false) {
					model.addAttribute("alertMsgForMonthEnd",
							"Cannot enter any more data till End Of Month procedure has been run.");
					model.addAttribute("searchTitle", "NHS");
					model.addAttribute("addBtnUrl", "nhs/newNHSPg.do");
					model.addAttribute("searchUrl", "nhs/searchNHS.do");
					return "nhsSearch";
				} else {
					String fDate = strUtility.chageDateFormat(request.getParameter("fDate"));
					String tDate = strUtility.chageDateFormat(request.getParameter("tDate"));

					searchMap.put("fDate", fDate);
					searchMap.put("tDate", tDate);
					searchMap.put("branchId", String.valueOf(branchdetails.getId()));
					List<NHS> nhsList = nhsService.searchNHSScript(searchMap);
					model.addAttribute("nhsList", nhsList);
					model.addAttribute("fdate", fDate);
					model.addAttribute("enddate", tDate);
					model.addAttribute("searchTitle", "NHS");
					model.addAttribute("addBtnUrl", "nhs/newNHSPg.do");
					model.addAttribute("searchUrl", "nhs/searchNHS.do");
				}

			} else {
				String fDate = strUtility.chageDateFormat(request.getParameter("fDate"));
				String tDate = strUtility.chageDateFormat(request.getParameter("tDate"));

				searchMap.put("fDate", fDate);
				searchMap.put("tDate", tDate);
				searchMap.put("branchId", String.valueOf(branchdetails.getId()));
				List<NHS> nhsList = nhsService.searchNHSScript(searchMap);
				model.addAttribute("nhsList", nhsList);
				model.addAttribute("fdate", fDate);
				model.addAttribute("enddate", tDate);
				model.addAttribute("searchTitle", "NHS");
				model.addAttribute("addBtnUrl", "nhs/newNHSPg.do");
				model.addAttribute("searchUrl", "nhs/searchNHS.do");
				SearchForm searchForm = new SearchForm();
				searchForm.setStartDate(strUtility.covertStringToDate(fDate, "yyyy-MM-dd"));
				searchForm.setEndDate(strUtility.covertStringToDate(tDate, "yyyy-MM-dd"));
				session.setAttribute("searchForm", searchForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" searchNHS : end ");
		return "nhsSearch";
	}

	@RequestMapping("/newNHSPg")
	public String newNHSPg(@ModelAttribute("nshscript") NHS nhs, HttpServletRequest request, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) {
		logger.info(" newNHSPg : start ");

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

			String date1 = request.getParameter("date1");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (strUtility.checkNullOrEmptyString(date1)) {

				nhs.setDate(dateFormat.parse(strUtility.chageDateFormat(date1)));
				model.addAttribute("status", "m");
			} else {
				if (strUtility.getMonth() != branchdetails.getPeriod()) {
					int extendNoOfDay = Integer
							.parseInt(sysParamService.getSystemParameter("ExtendNoOfDay").getParameterValue());
					boolean extendOrNot=false;
					String sessionObj=(String) session.getAttribute("extendOrNot");
					
					if(sessionObj!=null)
						extendOrNot=Boolean.valueOf(sessionObj);
					if (extendOrNot==false) {

						model.addAttribute("alertMsgForMonthEnd",
								"Cannot enter any more data till End Of Month procedure has been run.");
						model.addAttribute("searchTitle", "NHS");
						model.addAttribute("addBtnUrl", "nhs/newNHSPg.do");
						model.addAttribute("searchUrl", "nhs/searchNHS.do");
						return "nhsSearch";
					} else {
						searchMap.put("tDate", strUtility.extendDate(extendNoOfDay, branchdetails.getPeriod()));
						model.addAttribute("status", "add");
					}
				} else {
					model.addAttribute("status", "add");
					searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
				}

			}
			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));
			//searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			searchMap.put("cp", String.valueOf(branchdetails.getPeriod()));
			model.addAttribute("currentPrescriptionCharge",
					sysParamService.getSystemParameter("currentPrescriptionCharge"));
			model.addAttribute("previousPrescriptionCharge",
					sysParamService.getSystemParameter("previousPrescriptionCharge"));

			List<Object[]> list = nhsService.searchRemainingNHSScript(searchMap);
			
			logger.info("remainingNHSList=>list.size()==>"+list.size());
			model.addAttribute("listSize",list.size());
//			if (list.size() == 0) {
//				redirectAttributes.addFlashAttribute("successMessage", "NHS data upto date.");
//				return "redirect:searchPg.do";
//			} 
//			
			if (!strUtility.checkNullOrEmptyString(date1)) {
			if (list != null && list.size() > 0) {
				nhs.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(list.get(list.size() - 1))));
			}
			}
			model.addAttribute("nhsReminderPeriod", sysParamService.getSystemParameter("NhsReminderPeriod"));
			model.addAttribute("nhsRemDay", nhsService.checkRemainingNHS(searchMap));
			model.addAttribute("remainingNHSList", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(" newNHSPg : end ");
		return "managenhs";
	}

	@RequestMapping(value = "/manageNHSScript", params = "add")
	public String insertNHSScript(@ModelAttribute("nshscript") NHS nhs, Model model, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info("insertNHSScript- start");
		AppUser usr = (AppUser) session.getAttribute("userInfo");
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		try {

			nhs.setUserId(usr.getUsrId());
			nhs.setBranchId(branchdetails.getId());
			nhs.setCompanyId(Integer.parseInt(branchdetails.getInternalBranchId()));
			nhs.setCreatedBy(usr.getUsrName());
			nhs.setCreatedDt(Calendar.getInstance().getTime());
			nhs.setUpdatedDt(Calendar.getInstance().getTime());

			Map<String, String> existCashingMap = new HashMap<>();
			existCashingMap.put("nhsdate", new SimpleDateFormat("yyyy-MM-dd").format(nhs.getDate()));
			existCashingMap.put("branchid", String.valueOf(nhs.getBranchId()));

			if (nhsService.nhsExistOrNot(existCashingMap) == true) {
				redirectAttributes.addFlashAttribute("errorMessage", "This Record already Exists.");
				return "redirect:newNHSPg";
			} else {
				NHSValidator validator = new NHSValidator();
				validator.validate(nhs, result);

				if (result.hasErrors()) {
					return "managenhs";
				} else {

					String successMessage = nhsService.insertNHSScript(nhs);
					if (strUtility.checkNullOrEmptyString(successMessage)) {
						redirectAttributes.addFlashAttribute("successMessage", successMessage);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("insertNHSScript - end");
		return "redirect:searchPg";
	}

	@RequestMapping("/editNHSPg")
	public String editNHSPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" editNHSPg : start ");

		NHS nshscript = new NHS();
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
				nshscript = nhsService.getNHSScript(Integer.parseInt(id));
				model.addAttribute("mode", "update");
			}
			String reportid = request.getParameter("reportid");
			if (strUtility.checkNullOrEmptyString(reportid)) {
				nshscript = nhsService.getNHSScript(Integer.parseInt(reportid));
				model.addAttribute("report", "report");
			}
			if (nshscript == null) {
				nshscript = new NHS();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("nshscript", nshscript);

		logger.info(" editNHSPg : end ");
		return "managenhs";
	}

	@RequestMapping(value = "/manageNHSScript", params = "edit")
	public String updateNHSScript(@ModelAttribute("nhs") NHS nhs, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.info("updateNHSScript- start");
		try {

			NHSValidator validator = new NHSValidator();
			validator.validate(nhs, result);

			if (result.hasErrors()) {
				return "managenhs";
			} else {
				nhsService.updateNHSScript(nhs, "nhsUpdate");

				redirectAttributes.addFlashAttribute("successMessage", "NHSScript Update successfully.");

			}
		} catch (Exception e) {
			e.printStackTrace();

			redirectAttributes.addFlashAttribute("errorMessage", "NHSScript did not Update.");
		}
		logger.info("updateNHSScript - end");
		return "redirect:searchPg";
	}

	@RequestMapping(value = "/deleteNHS")
	public String deleteNHSScript(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		logger.info("deleteNHSScript - start");

		try {

			String id = request.getParameter("id");
			if (strUtility.checkNullOrEmptyString(id)) {
				nhsService.deleteNHSScript(Integer.parseInt(id));

				redirectAttributes.addFlashAttribute("successMessage", "NHSScript Delete successfully.");
			}

		} catch (Exception e) {
			e.printStackTrace();

			redirectAttributes.addFlashAttribute("errorMessage", "NHSScript did not Delete.");
		}

		logger.info("deleteNHSScript - end");
		return "redirect:searchPg";
	}

	@RequestMapping(value = "/getPrivatePrescriptionValueForNHS.do")
	public @ResponseBody String getPrivatePrescriptionValueForNHS(Model model, HttpSession session,
			HttpServletRequest request) {
		logger.info("in getPrivatePrescriptionValueForNHS - start");
		Map<String, String> searchMap = new HashMap<>();
		try {
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			String fDate = strUtility.chageDateFormat(request.getParameter("fDate"));
			searchMap.put("fDate", fDate);
			searchMap.put("branchId", String.valueOf(branchdetails.getId()));
			return nhsService.getPrivatePrescriptionValueForNHS(searchMap);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "NHSScript did not Update.");
		}
		return null;
	}
	
	@RequestMapping("/amendmentsNHSPg")
	public String amendmentsNHSPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" amendmentsNHSPg : start ");

		NHS nshscript = new NHS();
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
				nshscript = nhsService.getNHSScript(Integer.parseInt(id));
				model.addAttribute("mode", "update");
			}
			String reportid = request.getParameter("reportid");
			if (strUtility.checkNullOrEmptyString(reportid)) {
				nshscript = nhsService.getNHSScript(Integer.parseInt(reportid));
				model.addAttribute("report", "report");
			}
			if (nshscript == null) {
				nshscript = new NHS();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("nshscript", nshscript);

		logger.info(" amendmentsNHSPg : end ");
		return "amendNhs";
	}
	
	@RequestMapping(value = "/manageNHSScript", params = "amendUpdate")
	public String amendNHSScript(@ModelAttribute("nhs") NHS nhs, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.info("amendNHSScript update- start");
		String strObj="ERROR";
		try {

			NHSValidator validator = new NHSValidator();
			validator.validate(nhs, result);

			if (result.hasErrors()) {
				return "managenhs";
			} else {
				strObj=nhsService.amendUpdateNHSScript(nhs);
				//System.out.println(strObj+"---"+nhs.getId());
				if(strObj.equalsIgnoreCase("SUCCESS"))
				redirectAttributes.addFlashAttribute("alertMsg", "NHSScript update successfully.");
				else
				redirectAttributes.addFlashAttribute("alertMsg", "NHSScript did not Update.");
			}
		} catch (Exception e) {
			e.printStackTrace();

			redirectAttributes.addFlashAttribute("alertMsg", "NHSScript did not update.");
		}
		logger.info("amendNHSScript - end");
		return "redirect:/report/amendmentsNhsPg.do?id="+nhs.getBranchId();
	}

}
