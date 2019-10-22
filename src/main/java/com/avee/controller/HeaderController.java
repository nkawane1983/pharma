package com.avee.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.BranchDetails;
import com.avee.service.BankingService;
import com.avee.service.BranchService;
import com.avee.service.CashingService;
import com.avee.service.CategoryService;
import com.avee.service.DefaultsService;
import com.avee.service.GroupService;
import com.avee.service.LookingService;
import com.avee.service.NHSService;
import com.avee.service.SysParamService;
import com.avee.service.UserService;
import com.avee.service.UserTypeService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;

@Controller
public class HeaderController {
	
	static final Logger logger = LogManager.getLogger(HeaderController.class.getName());
	
	@Autowired
	CashingService cashingService;
	
	@Autowired
	NHSService nhsService;
	
	@Autowired
	BankingService bankingService;
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserTypeService userTypeService;
	
	@Autowired
	private StringUtility strUtility;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	DefaultsService defaultsService;
	
	@Autowired
	SysParamService sysParamService;

	@Autowired
	GroupService groupService;
	
	@Autowired
	LookingService lookingService;
	
	@Autowired
	private UserTypeService userTypeservice;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
	    binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
	    binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
	    binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	}

	@RequestMapping("/helpPg")
	public String help(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("in help ...:start");
		Map<String, String> searchMap = new HashMap<>();
		try {

			AppUser usr = (AppUser) session.getAttribute("userInfo");
			searchMap.put("usertype", userTypeservice.getUserType(Integer.parseInt(usr.getUsrType()), true).getName());
			model.addAttribute("userguides", lookingService.userGuides(searchMap));

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out help ...:End");
		return "help";
	}

	@RequestMapping("/settingPg")
	public String settingPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("in settingPg ...:start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			Map<String, String> map = new HashMap<>();

			model.addAttribute("groupList", groupService.searchGroupDetails(map));

			String groupid = request.getParameter("groupid");

			if (!strUtility.checkNullOrEmptyString(groupid)) {

				groupid = String.valueOf(0);

			}
			model.addAttribute("groupid", groupid);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("out settingPg ...:End");
		return "setting";
	}

	@RequestMapping("/manageSettingPg")
	public String manageSettingPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("in manageSettingPg ...:start");

		logger.info("out manageSettingPg ...:End");
		return "redirect:setting";
	}

	@RequestMapping("/deleteSettingPg")
	public String deleteSettingPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("in deleteSettingPg ...:start");

		logger.info("out deleteSettingPg ...:End");
		return "redirect:setting";
	}

	

	@RequestMapping("/improtFilePg")
	public String improtPg(Model model, HttpSession session) {
		logger.info("out improtPg ...:start");
		AppUser usr = (AppUser) session.getAttribute("userInfo");

		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		logger.info("out improtPg ...:End");
		return ("importPg");
	}

	@RequestMapping("/downloadDemoFilePg")
	public void downloadDemoFilePg(Model model, HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		logger.info("out downloadDemoFilePg ...:start");
		String outfile = request.getParameter("id");
		if (strUtility.checkNullOrEmptyString(outfile)) {
			File file = null;
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();

			if (outfile.equals("cashing")) {
				file = new File(path + "cashing.xls");
			}
			if (outfile.equals("nhs")) {
				file = new File(path + "nhs.xls");
			}
			byte[] bytesArray = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(bytesArray);
			fis.close();
			response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment;filename=demo" + outfile + ".xls");
			response.getOutputStream().write(bytesArray);
		}
		logger.info("out downloadDemoFilePg ...:End");

	}

	@RequestMapping("/uploadFilePg")
	public String uploadFilePg(Model model, @RequestParam("excelfile") MultipartFile excelfile,
			@RequestParam("filetype") String filetype) {
		logger.info("out uploadFilePg ...:start");
		try {
			int i = 1;
			System.out.println("Hello");
			XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model
				i++;
				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i);
				if (row != null) {
					if (row.getCell(0).getDateCellValue().toString() != null)
						System.out
								.println(new SimpleDateFormat("dd/MM/yyyy").format(row.getCell(0).getDateCellValue()));
					// Sets the Read data to the model class
					/*
					 * if (strUtility.checkNullOrEmptyString(row.getCell(0).
					 * getStringCellValue())) {
					 * System.out.println(strUtility.chageDateFormat(row.getCell
					 * (0).getStringCellValue()) + "," +
					 * row.getCell(1).getStringCellValue() + "," +
					 * row.getCell(2).getStringCellValue() + "," +
					 * row.getCell(3).getStringCellValue() + "," +
					 * row.getCell(4).getStringCellValue()); }
					 */
					// row.getCell(1).getStringCellValue());
					// row.getCell(2).getDateCellValue());
					// persist data into database in here
				}
			}
			workbook.close();
			model.addAttribute("msg", "Inserted Row" + i + "count");
		} catch (

		IOException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException n) {
			n.printStackTrace();
			System.out.println("Null");
		}
		logger.info("out uploadFilePg ...:End");
		return ("importPg");

	}

	@RequestMapping("/notificationsPg")
	public String notificationsPg(Model model, HttpSession session) {
		logger.info(" notificationsPg ...:start");
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
			String userTypeName = userTypeService.getUserType(Integer.parseInt(usr.getUsrType()), true).getName();
			String sDateObj = null;
			String endDateObj = null;
			if (userTypeName.equals("SuperAdmin") || userTypeName.equals("Admin")) {
				sDateObj = strUtility.getDate(strUtility.getMonth());
				endDateObj = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				searchMap.put("branchIdObj", "0");//all branch
				searchMap.put("modeObj", "1"); 
				searchMap.put("fDate", sDateObj);
				searchMap.put("tDate", endDateObj);
				model.addAttribute("notifications", cashingService.getNotification(searchMap));
			}else{
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));
			searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			searchMap.put("branchIdObj", String.valueOf(branchdetails.getId()));
			searchMap.put("modeObj", "0"); 
			}
			model.addAttribute("notifications", cashingService.getNotification(searchMap));
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("out notificationsPg ...:End");
		return ("notificationpg");
	}
	
	
	@RequestMapping("/extendDayPg")
	public String extendDay(Model model, HttpSession session,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		logger.info(" notificationsPg ...:start");
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
			BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
			searchMap.put("fDate", strUtility.getDate(branchdetails.getPeriod()));
			searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
			searchMap.put("branchIdObj", String.valueOf(branchdetails.getId()));
			int extendNoOfDay = Integer
					.parseInt(sysParamService.getSystemParameter("ExtendNoOfDay").getParameterValue());
			//System.out.println(extendNoOfDay);
			if(strUtility.extendDateForEndMonth(extendNoOfDay, branchdetails.getPeriod()))
				session.setAttribute("extendOrNot", "false");
			else
				session.setAttribute("extendOrNot", "true");
			
			searchMap.put("tDate", strUtility.getEndDate(branchdetails.getPeriod()));
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
			String referer = request.getHeader("Referer");
	
			String url=referer.split("pharmadmin")[1];
			return "redirect:"+url;
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("out notificationsPg ...:End");
		return "redirect:cashing/dashboard.do";
	}

	
}
