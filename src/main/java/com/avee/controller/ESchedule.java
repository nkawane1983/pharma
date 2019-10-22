package com.avee.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.MonthYears;
import com.avee.form.Ppa500;
import com.avee.form.Ppa600;
import com.avee.form.Ppa800;
import com.avee.form.PpaData;
import com.avee.service.BranchService;
import com.avee.service.EScheduleService;
import com.avee.service.LookingService;
import com.avee.utility.StringUtility;

@Controller
@RequestMapping(value = "/eschedule")
public class ESchedule {
	static final Logger logger = LogManager.getLogger(BankingController.class.getName());
	@Autowired
	LookingService lookingService;
	@Autowired
	private StringUtility strUtility;
	@Autowired
	private BranchService branchService;
	@Autowired
	private EScheduleService eScheduleService;
	private Date pd;
	private String ca;
	private String spf;
	private String soc, scll, nms_num, branchName;
	private int entryNumber = 0;
	private int entryNumber2 = 0;
	private String SB_DISP_DATE_YEAR, SB_DISP_DATE_MONTH;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info(" eschedule SearchPg : start ");
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

			Map<Integer, List<String>> searchMap = new HashMap<>();
			List<String> monthList = null;
			List<String> yearList = null;
			String month = null;
			String year = null;
			String groupid = null;
			String branchid = null;
			month = request.getParameter("month");
			year = request.getParameter("year");

			groupid = request.getParameter("groupid");
			branchid = request.getParameter("branchid");

			String userid = usr.getUsrId();

			if (!strUtility.checkNullOrEmptyString(branchid) && !strUtility.checkNullOrEmptyString(groupid)) {
				groupid = "0";
				branchid = "0";

			}
			searchMap = eScheduleService.getMonthYearAsList(0);
			for (Map.Entry<Integer, List<String>> entry : searchMap.entrySet()) {
				if (entry.getKey() == 1)
					monthList = entry.getValue();
				else
					yearList = entry.getValue();

			}
			if (!strUtility.checkNullOrEmptyString(month) && !strUtility.checkNullOrEmptyString(year)) {
				month = monthList.get(0);
				year = yearList.get(0);
			}
			model.addAttribute("monthList", monthList);
			model.addAttribute("yearList", yearList);
			map.put("groupid", groupid);
			map.put("branchid", branchid);
			map.put("month", month);
			map.put("year", year);
			model.addAttribute("branchid", branchid);
			model.addAttribute("groupid", groupid);
			model.addAttribute("month", month);
			model.addAttribute("year", year);
			map.put("userid", userid);
			map.put("uid", userid);
			map.put("groupId", groupid);
			map.put("branchID", "0");
			model.addAttribute("branchASlist", branchService.getBranchDetailsaAsList(map));
			model.addAttribute("PpaMasterReportList", eScheduleService.getPpaMasterDataAsList(map));

		} catch (Exception e)

		{
			e.printStackTrace();
		}
		logger.info(" eschedule SearchPg : end ");
		return "escheduleSearch";
	}

	// manageschedule
	@RequestMapping("/manageFilePg")
	public String manageFilePg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		logger.info(" eschedule manageschedule : start ");
		// Map<String, String> map = new HashMap<>();

		// Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			model.addAttribute("ppaDataList", eScheduleService.getPpaMasterData(usr.getUsrId()));

		} catch (Exception e)

		{
			e.printStackTrace();
		}
		logger.info(" eschedule manageschedule : end ");
		return "manageschedule";
	}

	@RequestMapping("/uploadDatFilePg")
	public String uploadDatFilePg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam("datfile") MultipartFile datfile) {
		logger.info(" eschedule manageschedule : start ");
		// Map<String, String> map = new HashMap<>();
		// Map<Integer, String> strMap = new HashMap<>();
		// Map<String, String> cook = new HashMap<>();
		String groupId = request.getParameter("groupid");
		String msg = "";
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */Scanner sc = null;

			if (strUtility.checkNullOrEmptyString(groupId)) {
				int cout = 0;
				sc = new Scanner(datfile.getInputStream(), "UTF-8");
				if (checkFileInsertedOrNot(datfile, groupId, usr.getUsrId()) == true) {
					if (checkBranchCodeExistsOrNotPPA(datfile, groupId, usr.getUsrId()) == true) {
						eScheduleService.deleteOldImportData(usr.getUsrId());
						PpaData ppadata = new PpaData();// rst
						countTotalRecodeIntoMaster();
						while (sc.hasNextLine()) {
							String line = sc.nextLine();
							// System.out.println(line);
							if (line.trim() != null && line.trim().length() > 0) {
								String code = line.substring(0, 4).trim();
								switch (code) {
								case "100":
									ppadata = insertPpa100(ppadata, line);
									break;
								case "200":
									ppadata = insertPpa200(ppadata, line, groupId);
									break;
								case "300":
									ppadata = insertPpa300(ppadata, line, groupId);
									break;
								case "400":
									ppadata = insertPpa400(ppadata, line, groupId);
									break;
								case "500":
									ppadata = insertPpa500(ppadata, line);
									break;
								case "600":
									ppadata = insertPpa600(ppadata, line);
									break;
								case "700":
									ppadata = insertPpa700(ppadata, line);
									break;
								case "800":
									ppadata = insertPpa800(ppadata, line);
									break;
								case "9999":
									ppadata = insertPpa9999(ppadata, line);
									ppadata.setCreatedBy(usr.getUsrId());
									ppadata.setCreatedDate(new Date());
									ppadata.setUpdatedDate(new Date());
									msg = eScheduleService.insertPPADATA(ppadata);
									ppadata = new PpaData();
									break;

								default:
									break;
								}
							}

						}
						MonthYears monthYears = new MonthYears();
						Date eventdate = getDate(SB_DISP_DATE_MONTH, SB_DISP_DATE_YEAR);
						monthYears.setEventDate(eventdate);
						monthYears.setGroupId(Integer.parseInt(groupId));
						eScheduleService.insertMonthYears(monthYears);
						eScheduleService.UpdatePpaDataImport();
						model.addAttribute("successMessage", "File imported successfully ");
						model.addAttribute("ppaDataList", eScheduleService.getPpaMasterData(usr.getUsrId()));
					} else {
						System.out.println("File not imported successfully ");
						model.addAttribute("errorMessage", "File not imported successfully ");
					}
				} else {
					System.out.println("File already inserted ");
					model.addAttribute("errorMessage", "already inserted ");
				}

			}

		} catch (Exception e)

		{
			e.printStackTrace();
		}

		logger.info(" eschedule manageschedule : end ");
		return "manageschedule";
	}

	@RequestMapping("/importedAllDataPPAPg")
	public String importedAllDataPPAPg(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info(" eschedule importedAllDataPPAPg : start ");
		// Map<String, String> map = new HashMap<>();
		String msg = "";
		// Map<String, String> cook = new HashMap<>();
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			model.addAttribute("ppaDataList", eScheduleService.getPpaMasterData(usr.getUsrId()));
			msg = eScheduleService.copyAllDataIntoMasterPPA(usr.getUsrId());
			if (msg.equals("SUCCESS")) {
				redirectAttributes.addFlashAttribute("successMessage", "imported successfully ");
				eScheduleService.deleteOldImportData(usr.getUsrId());
			} else
				redirectAttributes.addFlashAttribute("errorMessage", "not imported successfully ");
		} catch (Exception e)

		{
			redirectAttributes.addFlashAttribute("errorMessage", "not imported successfully ");
			e.printStackTrace();
		}
		logger.info(" eschedule importedAllDataPPAPg : end ");
		return "redirect:searchPg";
	}

	@RequestMapping("/generateReportPDFPg")
	public void generateReportPDF(@RequestParam("type") String type, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes) {
		logger.info(" eschedule generateReportPDF : start ");
		// Map<String, String> map = new HashMap<>();
		String msg = "";
		// Map<String, String> cook = new HashMap<>();
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
			String branchid = request.getParameter("b");
			String groupid = request.getParameter("g");
			String monthObj = request.getParameter("m");
			String yearObj = request.getParameter("y");

			// String reportTypeObj = request.getParameter("reportTypeObj");
			String filename = null;
			String userid = usr.getUsrId();
			map.put("groupId", groupid);
			map.put("branchId", branchid);
			map.put("month", monthObj);
			map.put("year", yearObj);
			map.put("type", type);
			map.put("userid", userid);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			long timeStampLong = timestamp.getTime();
			if (type.equals("pdf")) {
				filename = "Eschedule" + timeStampLong + "." + type;
				response.setContentType("application/" + type);
				response.setHeader("Content-Disposition", "attachment;filename=" + filename);
				response.getOutputStream().write(eScheduleService.generateReportPDF(map, response));
			}

			if (type.equals("csv")) {
				filename = "ExportCSV" + timeStampLong + "." + type;
				response.setContentType("application/" + type);
				response.setHeader("Content-Disposition", "attachment;filename=" + filename);
				response.getOutputStream().write(eScheduleService.generateReportCSV(map, response));
			}
		} catch (Exception e)

		{
			redirectAttributes.addFlashAttribute("errorMessage", "not generate Report PDF ");
			e.printStackTrace();
		}
		logger.info(" eschedule generateReport : end ");

	}

	public void countTotalRecodeIntoMaster() {
		entryNumber = convertInt(eScheduleService.countTotalRecodeIntoMaster()) + 1;

	}

	public void getBranchNameByCodePPA(String groupid, String brnchcode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("brnchcode", brnchcode);
		map.put("groupId", groupid);
		branchName = eScheduleService.getBranchNameByCodePPA(map);
	}

	public boolean checkBranchCodeExistsOrNotPPA(MultipartFile datfile, String groupid, String userId) {
		boolean valid = false;
		Map<String, String> map = new HashMap<String, String>();
		Scanner sc;
		try {
			sc = new Scanner(datfile.getInputStream(), "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.trim() != null && line.trim().length() > 0) {
					String code = line.substring(0, 4).trim();
					if (code.equals("200")) {
						map.put("groupId", groupid);
						map.put("userId", userId);
						String brnchcode = line.substring(7, 14).trim();
						map.put("brnchcode", brnchcode);
						valid = eScheduleService.checkBranchCodeExistsOrNotPPA(map);
						//System.out.println(valid);
						if (valid == false)
							break;
					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return valid;
	}

	public boolean checkFileInsertedOrNot(MultipartFile datfile, String groupid, String userId) {
		boolean valid = false;
		Map<String, String> map = new HashMap<String, String>();
		Scanner sc;
		try {
			sc = new Scanner(datfile.getInputStream(), "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.trim() != null && line.trim().length() > 0) {
					String code = line.substring(0, 4).trim();
					if (code.equals("300")) {
						map.put("groupId", groupid);
						map.put("userId", userId);
						Date eventdate;
						try {
							SB_DISP_DATE_YEAR = line.substring(496, 500).trim();
							SB_DISP_DATE_MONTH = line.substring(492, 496).trim();
							eventdate = getDate(SB_DISP_DATE_MONTH, SB_DISP_DATE_YEAR);
							String edate = new SimpleDateFormat("dd/MMM/yyyy").format(eventdate);
							map.put("eventdate", edate);
							valid = eScheduleService.checkPPADataExistsOrNot(map);
							SB_DISP_DATE_YEAR = "";
							SB_DISP_DATE_MONTH = "";
							// System.out.println(valid);
							if (valid == false)
								break;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return valid;
	}

	public PpaData insertPpa100(PpaData ppadata, String line) {

		try {
			pd = null;
			System.out.println(line.substring(4, 16).trim());
			pd = convertDateIntoString(line.substring(4, 16).trim());

			ca = null;
			ca = line.substring(16, 20).trim();
			spf = null;
			spf = line.substring(20, 26).trim();
			System.out.println(entryNumber);
			ppadata.setId(entryNumber);
			ppadata.setSpProfFee(convertDouble(line.substring(21, 26).trim()));
			ppadata.setExpItemsLowerLimit((short) convertInt(line.substring(31, 36).trim()));
			ppadata.setExpItemsUpperLimit((short) convertInt(line.substring(36, 41).trim()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ppadata;
	}

	public PpaData insertPpa200(PpaData ppadata, String line, String groupid) {
		ppadata.setMbHaQcode(line.substring(4, 7).trim());
		ppadata.setMbOcsCode(line.substring(7, 14).trim());
		ppadata.setMbCorName(line.substring(14, 54).trim());
		getBranchNameByCodePPA(groupid, line.substring(7, 14).trim());
		if (line.substring(54, 95).trim().length() == 0 && line.substring(54, 94).trim() == null)
			ppadata.setMbTradename(branchName);
		else
			ppadata.setMbTradename(line.substring(54, 94).trim());

		ppadata.setMbCorAddress1(line.substring(94, 124).trim());
		ppadata.setMbCorAddress2(line.substring(124, 154).trim());
		ppadata.setMbCorAddress3(line.substring(154, 184).trim());
		ppadata.setMbCorAddress4(line.substring(184, 214).trim());
		ppadata.setMbCorPostcode(line.substring(214, 222).trim());
		ppadata.setMbName(line.substring(222, 262).trim());
		ppadata.setMbPremAdd1(line.substring(262, 292).trim());
		ppadata.setMbPremAdd2(line.substring(292, 322).trim());
		ppadata.setMbPremAdd3(line.substring(322, 352).trim());
		ppadata.setMbPremAdd4(line.substring(352, 382).trim());
		ppadata.setMbPremPcode(line.substring(382, 390).trim());
		ppadata.setMbFhsaCode(line.substring(390, 393).trim());
		ppadata.setMbPpaAccCode(line.substring(393, 397).trim());
		ppadata.setMbNameOfAccount(line.substring(397, 437).trim());
		ppadata.setMbBankAccountNumber(line.substring(437, 447).trim());
		ppadata.setMbBuildingSocRef(line.substring(447, 465).trim());
		ppadata.setMbSortCode(line.substring(465, 473).trim());
		ppadata.setMbTypeOfAccount(line.substring(473, 474).trim());
		ppadata.setMbPaymentMethod(line.substring(474, 478).trim());
		ppadata.setMbPayment(convertDouble(line.substring(478, 491).trim()));
		ppadata.setMbRemitDispDate(line.substring(491, 505).trim());
		return ppadata;
	}

	public PpaData insertPpa300(PpaData ppadata, String line, String groupid) {

		ppadata.setPaymentDate(pd);
		ppadata.setContRate(convertDouble(ca));
		ppadata.setStProfFee(convertDouble(spf));

		ppadata.setSbMbOcsCode(line.substring(4, 11).trim());
		ppadata.setSbHaQcode(line.substring(11, 14).trim());
		ppadata.setSbOcsCode(line.substring(14, 21).trim());
		soc = line.substring(14, 21).trim();
		if (line.substring(21, 61).trim() == null)
			ppadata.setSbTaName(branchName);
		else
			ppadata.setSbTaName(line.substring(21, 61).trim());
		ppadata.setSbName(line.substring(61, 101).trim());
		ppadata.setSbAddress1(line.substring(101, 131).trim());
		ppadata.setSbAddress2(line.substring(131, 161).trim());
		ppadata.setSbAddress3(line.substring(161, 191).trim());
		ppadata.setSbAddressPostCode(line.substring(191, 199).trim());
		ppadata.setSbHealthAuthority(line.substring(199, 239).trim());
		ppadata.setSbConPpaCode(line.substring(239, 243).trim());
		ppadata.setSbConLaLine(line.substring(243, 251).trim());
		scll = line.substring(243, 251).trim();
		ppadata.setSbPayment(convertDouble(line.substring(251, 264).trim()));
		ppadata.setSbConFormsRec((short) convertInt(line.substring(264, 270).trim()));
		ppadata.setSbConItemsSdr((short) convertInt(line.substring(270, 276).trim()));
		ppadata.setSbConItemsZd((short) convertInt(line.substring(276, 282).trim()));
		ppadata.setSbConFormsRb((short) convertInt(line.substring(282, 288).trim()));
		ppadata.setSbConItemsRb((short) convertInt(line.substring(288, 294).trim()));
		ppadata.setSbConDisc(convertDouble(line.substring(294, 299).trim()));
		ppadata.setSbConNumConAll((short) convertInt(line.substring(299, 304).trim()));
		ppadata.setSbConAdjText(line.substring(304, 344).trim());
		ppadata.setSbNoStproFees((short) convertInt(line.substring(344, 350).trim()));
		ppadata.setSbNoSprofFees((short) convertInt(line.substring(350, 356).trim()));
		ppadata.setSbNoEprescFees(convertDouble(line.substring(356, 362).trim()));
		ppadata.setSbRecovery(convertDouble(line.substring(362, 375).trim()));
		ppadata.setSbNoPrescNew((short) convertInt(line.substring(375, 381).trim()));
		ppadata.setSbPrescRateNew(convertDouble(line.substring(381, 386).trim()));
		ppadata.setSbNoPrescOld(convertDouble(line.substring(386, 392).trim()));
		ppadata.setSbPrescRateOld(convertDouble(line.substring(392, 397).trim()));
		ppadata.setSbAdvPayment(convertDouble(line.substring(397, 410).trim()));
		ppadata.setSbAdvMonth(line.substring(410, 419).trim());
		ppadata.setSbAdvItems((short) convertInt(line.substring(419, 425).trim()));
		ppadata.setSbAdvChrg((short) convertInt(line.substring(425, 431).trim()));
		ppadata.setSbOtherAdvReason(line.substring(431, 446).trim());
		ppadata.setSbRecOfAdvReason(line.substring(446, 466).trim());
		ppadata.setSbTotAuthByPpa(convertDouble(line.substring(466, 479).trim()));
		ppadata.setSbTotAuthByHa(convertDouble(line.substring(479, 492).trim()));
		SB_DISP_DATE_YEAR = line.substring(496, 500).trim();
		SB_DISP_DATE_MONTH = line.substring(492, 496).trim();
		ppadata.setSbDispDateMonth(line.substring(492, 496).trim());
		ppadata.setSbDispDateYear((short) convertInt(line.substring(496, 500).trim()));
		ppadata.setSbTotBasPrice(convertDouble(line.substring(500, 513).trim()));
		ppadata.setSbDiscount(convertDouble(line.substring(513, 526).trim()));
		ppadata.setSbTotBasPriceZd(convertDouble(line.substring(526, 539).trim()));
		ppadata.setSbOopExp(convertDouble(line.substring(539, 552).trim()));
		ppadata.setSbContAll(convertDouble(line.substring(552, 565).trim()));
		ppadata.setSbOxyCylMsk(convertDouble(line.substring(565, 578).trim()));
		ppadata.setSbOxyExp(convertDouble(line.substring(578, 591).trim()));
		ppadata.setSbOxyAdj(convertDouble(line.substring(591, 604).trim()));
		ppadata.setSbAdjVal(convertDouble(line.substring(604, 617).trim()));
		ppadata.setSbStProfFees(convertDouble(line.substring(617, 630).trim()));
		ppadata.setSbSpProfFees(convertDouble(line.substring(630, 643).trim()));
		ppadata.setSbMpPfees(convertDouble(line.substring(643, 652).trim()));
		ppadata.setSbPfees2a(convertDouble(line.substring(652, 661).trim()));
		ppadata.setSbPfees2b(convertDouble(line.substring(661, 670).trim()));
		ppadata.setSbPfees2d(convertDouble(line.substring(679, 688).trim()));
		ppadata.setSbPfees2e(convertDouble(line.substring(688, 697).trim()));
		ppadata.setSbPfees2f(convertDouble(line.substring(697, 710).trim()));
		ppadata.setSbPfees2g(convertDouble(line.substring(710, 724).trim()));
		ppadata.setSbExpPrescFees(convertDouble(line.substring(724, 737).trim()));
		ppadata.setSbAddProfPay(convertDouble(line.substring(737, 750).trim()));
		ppadata.setSbPrescChNew(convertDouble(line.substring(750, 763).trim()));
		ppadata.setSbPrescChOld(convertDouble(line.substring(763, 776).trim()));
		ppadata.setSbOtherAdj(convertDouble(line.substring(776, 789).trim()));
		ppadata.setSbSubBpTotCalc(convertDouble(line.substring(789, 802).trim()));
		ppadata.setSbReimbTotCalc(convertDouble(line.substring(802, 815).trim()));
		ppadata.setSbAddProfFees(convertDouble(line.substring(815, 828).trim()));
		ppadata.setSbRemunTot(convertDouble(line.substring(828, 841).trim()));
		ppadata.setSbTotDed(convertDouble(line.substring(841, 854).trim()));
		ppadata.setSbTotOfAcc(convertDouble(line.substring(854, 867).trim()));
		ppadata.setSbNetAmt(convertDouble(line.substring(867, 880).trim()));
		ppadata.setSbRecOfAdvCalc(convertDouble(line.substring(880, 893).trim()));
		ppadata.setSbOthAdjAdv(convertDouble(line.substring(893, 906).trim()));
		ppadata.setSbTotLaPay(convertDouble(line.substring(906, 919).trim()));
		ppadata.setSbTotLaOther(convertDouble(line.substring(919, 932).trim()));
		ppadata.setMurNum(convertDouble(line.substring(932, 938).trim()));
		ppadata.setFp57NumForms(convertDouble(line.substring(938, 944).trim()));
		ppadata.setPracticePayment(convertDouble(line.substring(944, 957).trim()));
		ppadata.setEstabFee(convertDouble(line.substring(957, 970).trim()));
		ppadata.setMurFee(convertDouble(line.substring(970, 983).trim()));
		ppadata.setRepeatSetup(convertDouble(line.substring(983, 996).trim()));
		ppadata.setSubTotalOfPrescFee(convertDouble(line.substring(996, 1010).trim()));
		ppadata.setSbPfees2c(convertDouble(line.substring(1022, 1035).trim()));

		if (line.length() <= (1062 + 13) && line.length() > 1062) {

			ppadata.setSbPfees2bHome(convertDouble(line.substring(1022, 1035).trim()));
			ppadata.setAurHomeFee(convertDouble(line.substring(1048, 1061).trim()));
			ppadata.setAurPremFee(convertDouble(line.substring(1061, 1075).trim()));
			ppadata.setStomaCustValue(convertDouble(line.substring(1035, 1048).trim()));

		} else {
			ppadata.setSbPfees2bHome(0);
			ppadata.setAurHomeFee(0);
			ppadata.setAurPremFee(0);
			ppadata.setStomaCustValue(0);
		}
		ppadata.setNmsFee(convertDouble(line.substring(1086, 1098).trim()));
	
		String nms_num = line.substring(1099, 1104).trim();
		if (nms_num.isEmpty() || nms_num.length() == 0 || nms_num == null)
			ppadata.setNmsNum(0);
		
			if (convertInt(groupid) != 1) 
			ppadata.setNmsNum((short) convertInt(line.substring(1100, 1105).trim()));
		

		if (convertInt(groupid) == 1) {
			ppadata.setNmsItem((short) convertInt(line.substring(1105, 1112).trim()));
			ppadata.setDispItemsNum(convertDouble(line.substring(1105, 1112).trim()));
		} else {
			ppadata.setNmsItem((short) convertInt(line.substring(1105, 1113).trim()));
			ppadata.setDispItemsNum(convertDouble(line.substring(1105, 1113).trim()));
		}
		ppadata.setSbContCon(convertDouble(line.substring(1126, 1138).trim()));
		return ppadata;
	}

	public PpaData insertPpa400(PpaData ppadata, String line, String groupid) {

		ppadata.setSbElhoseValue(convertDouble(line.substring(5, 18).trim()));
		ppadata.setSbAvgItemValue(convertDouble(line.substring(18, 27).trim()));
		//ppadata.setSbEtpForms((short) convertInt(line.substring(27, 31).trim()));
		//ppadata.setSbEtpItems(convertDouble(line.substring(31, 36).trim()));

		// update by groupid
		if (convertInt(groupid) == 1) {
			ppadata.setSbEtpForms((short) convertInt(line.substring(27, 31).trim()));
			ppadata.setSbEtpItems(convertDouble(line.substring(31, 36).trim()));
		} else {
			ppadata.setSbEtpForms((short) convertInt(line.substring(27, 33).trim()));
			ppadata.setSbEtpItems(convertDouble(line.substring(33, 40).trim()));
		}
		return ppadata;
	}

	public PpaData insertPpa500(PpaData ppadata, String line) {
		String szPayLineId = null;
		szPayLineId = line.substring(4, 8).trim();
		if (szPayLineId != null && szPayLineId.length() > 0) {
			if (szPayLineId.equals("146"))
				ppadata.setRecAddAdvPay(convertDouble(line.substring(78, 91).trim()));
			else if (szPayLineId.equals("145"))
				ppadata.setAddAdvPay(convertDouble(line.substring(78, 91).trim()));
			else {
				ppadata.setLocalPayLineId((short) convertInt(line.substring(4, 8).trim()));
				ppadata.setLocalPayLineDesc(line.substring(8, 78).trim());
				ppadata.setLocalPayLineValue(convertDouble(line.substring(78, 91).trim()));

				Ppa500 ppa500 = new Ppa500();

				ppa500.setId(entryNumber);
				ppa500.setPaymentDate(pd);
				ppa500.setSbOcsCode(soc);
				ppa500.setSbConLaLine(scll);
				ppa500.setLocalPayLineId((short) convertInt(line.substring(4, 8).trim()));
				ppa500.setLocalPayLineDesc(line.substring(8, 78).trim());
				ppa500.setLocalPayLineValue(convertDouble(line.substring(78, 91).trim()));
				eScheduleService.insertPPA500(ppa500);
			}
		}
		// insert500

		return ppadata;
	}

	public PpaData insertPpa600(PpaData ppadata, String line) {
		// ppa600

		ppadata.setWgAeLineType((short) convertInt(line.substring(4, 5).trim()));
		ppadata.setWgAeIdentifier(line.substring(5, 8).trim());
		ppadata.setWgAeReasonCode(line.substring(7, 8).trim());
		ppadata.setWgAeDescription(line.substring(8, 52).trim());
		ppadata.setWgAeNumPxs((short) convertInt(line.substring(52, 59).trim()));

		Ppa600 ppa600 = new Ppa600();
		ppa600.setId(entryNumber);
		ppa600.setPaymentDate(pd);
		ppa600.setSbOcsCode(soc);
		ppa600.setSbConLaLine(scll);
		ppa600.setWgAeLineType((short) convertInt(line.substring(4, 5).trim()));
		ppa600.setWgAeIdentifier(line.substring(5, 8).trim());
		ppa600.setWgAeReasonCode(line.substring(7, 8).trim());
		ppa600.setWgAeDesc(line.substring(8, 52).trim());
		ppa600.setWgAeNumPxs((short) convertInt(line.substring(52, 59).trim()));
		eScheduleService.insertPPA600(ppa600);
		// insert600
		// ppa600 = new Ppa600();
		return ppadata;
	}

	public PpaData insertPpa700(PpaData ppadata, String line) {

		ppadata.setExpItemsLimit2((short) convertInt(line.substring(5, 11).trim()));
		ppadata.setExpValueLimit2(convertDouble(line.substring(11, 23).trim()));
		ppadata.setExpItemsLimit1((short) convertInt(line.substring(23, 29).trim()));
		ppadata.setExpValueLimit1(convertDouble(line.substring(29, 41).trim()));
		ppadata.setExpTotItems((short) convertInt(line.substring(41, 47).trim()));
		ppadata.setExpTotValue(convertDouble(line.substring(47, 59).trim()));

		return ppadata;
	}

	public PpaData insertPpa800(PpaData ppadata, String line) {
		ppadata.setExpensiveItemLine(line.substring(5, 12).trim());
		ppadata.setExpItemDescription(line.substring(12, 72).trim());
		ppadata.setExpItemPack(convertDouble(line.substring(72, 80).trim()));
		ppadata.setExpItemQty((short) convertInt(line.substring(80, 87).trim()));
		ppadata.setExpItemValue(convertDouble(line.substring(87, 97).trim()));

		Ppa800 ppa800 = new Ppa800();
		ppa800.setId(entryNumber);
		ppa800.setPaymentDate(pd);
		ppa800.setSbOcsCode(soc);
		ppa800.setSbConLaLine(scll);
		ppa800.setExpensiveItemLine(line.substring(5, 12).trim());
		ppa800.setExpItemDescription(line.substring(12, 72).trim());
		ppa800.setExpItemPack(convertDouble(line.substring(72, 80).trim()));
		ppa800.setExpItemQty((short) convertInt(line.substring(80, 87).trim()));
		ppa800.setExpItemValue(convertDouble(line.substring(87, 97).trim()));
		ppa800.setExpensiveItemForm(line.substring(97, 103).trim());
		// insert800
		// ppa800 = new Ppa800();
		eScheduleService.insertPPA800(ppa800);
		return ppadata;
	}

	public PpaData insertPpa9999(PpaData ppadata, String line) {

		ppadata.setTrlrBranchCount((short) convertInt(line.substring(4, 11).trim()));
		ppadata.setTrlrTotalValue(convertDouble(line.substring(11, 25).trim()));
		entryNumber = entryNumber + 1;
		entryNumber2 = entryNumber;
		return ppadata;
	}

	public Date convertDateIntoString(String pdate) throws ParseException {
		pdate = pdate.replace(" ", "/");
		Date paymentdate = new SimpleDateFormat("dd/MMM/yyyy").parse(pdate);
		return paymentdate;
	}

	public Date getDate(String monthName, String year) throws ParseException {
		Date date = new SimpleDateFormat("MMM").parse(monthName);// put your
																	// month
																	// name here
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// int monthNumber=cal.get(Calendar.MONTH);
		// System.out.println();
		return cal.getTime();
	}

	public int convertInt(String val) {
		int tempval = 0;
		if (val != null && val.length() > 0)
			tempval = Integer.parseInt(val);
		return tempval;
	}

	public double convertDouble(String val) {
		double tempval = 0.0;
		if (val != null && val.length() > 0)
			tempval = Double.parseDouble(val);
		return tempval;
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

	@RequestMapping(value = "/getMonthYearAsListByAjax")
	public @ResponseBody String getMonthYearAsListByAjax(Model model, @RequestParam("groupid") String groupid,
			HttpSession session) {
		logger.info("in getMonthYearAsListByAjax - start");
		StringWriter writer = null;

		// get branchDetails by branchId

		Map<Integer, List<String>> searchMap = new HashMap<>();
		List<String> monthList = null;
		List<String> yearList = null;

		try {
			if (groupid != null && !groupid.equalsIgnoreCase("0")) {

				searchMap = eScheduleService.getMonthYearAsList(Integer.parseInt(groupid));
				for (Map.Entry<Integer, List<String>> entry : searchMap.entrySet()) {
					if (entry.getKey() == 1)
						monthList = entry.getValue();
					else
						yearList = entry.getValue();

				}

			}

			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("monthList", monthList);
			context.put("yearList", yearList);

			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/monthYearList.vm", "utf-8", context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out getMonthYearAsListByAjax - start");
		return writer == null ? "" : writer.toString();
	}
}
