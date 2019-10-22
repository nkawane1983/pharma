package com.avee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avee.form.AppUser;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BranchDetails;
import com.avee.form.BranchUserAssocs;
import com.avee.form.GroupDetails;
import com.avee.form.GroupUserAssocs;
import com.avee.form.UserType;
import com.avee.service.BranchService;
import com.avee.service.CategoryService;
import com.avee.service.GroupService;
import com.avee.service.LookingService;
import com.avee.service.UserService;
import com.avee.service.UserTypeService;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;
import com.avee.validator.UserValidator;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	static final Logger logger = LogManager.getLogger(UserController.class.getName());

	@Autowired
	private UserService userService;

	@Autowired
	private StringUtility strUtility;

	@Autowired
	private UserTypeService userTypeservice;

	@Autowired
	BranchService branchService;

	@Autowired
	GroupService groupService;

	@Autowired
	LookingService lookingService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserTypeService userTypeService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    // Allow for null values in date fields.
	    binder.registerCustomEditor( Date.class, new CustomDateEditor( new SimpleDateFormat( "dd/MM/yyyy"), true ));
	    // tell spring to set empty values as null instead of empty string.
	    binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
	
	}

	@RequestMapping("/searchPg")
	public String searchPg(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" searchPg : start ");
		Map<String, String> searchMap = new HashMap<>();
		AppUser usr = (AppUser) session.getAttribute("userInfo");
		/*
		 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
		 */
		model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
		/*
		 * end Get Menu
		 */
		String usrName = request.getParameter("usrName");
		String usrEmail = request.getParameter("usrEmail");
		List<AppUserGroupBranchMapping> list = new ArrayList<>();

		if (strUtility.checkNullOrEmptyString(usrName) || strUtility.checkNullOrEmptyString(usrEmail)) {
			searchMap.put("userId", usrName);
			searchMap.put("emailId", usrEmail);
		}
		searchMap.put("uid", usr.getUsrId());

		list = userService.getUserDetailsaAsList(searchMap);
		model.addAttribute("usersList", list);
		logger.info(" searchPg : end ");
		return "userSearch";
	}

	@RequestMapping("/userProfile")
	public String viewUserProfile(Model model, HttpServletRequest request, HttpSession session) {
		logger.info(" userProfile : start ");
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
			if (branchdetails != null) {
				model.addAttribute("branchdetails", branchdetails);
				model.addAttribute("groupDetails", groupService.getGroupDetails(branchdetails.getGroupId()));
			}
			model.addAttribute("countrycode", lookingService.getAllCountryAsList());
			model.addAttribute("jobtitle", categoryService.searchCategory("JOB_TITLE"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" userProfile : end ");
		return "profilePg";
	}

	@RequestMapping("/newUser.do")
	public String newUser(Model model, HttpSession session) {
		logger.info("newUser	-	 start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");

			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			model.addAttribute("appUser", new AppUser());
			List<UserType> userTypeList = userTypeservice.getUserTypeList();
			model.addAttribute("branchlist", session.getAttribute("branchlist"));
			model.addAttribute("groupDetails", session.getAttribute("groupDetails"));
			model.addAttribute("userTypeList", userTypeList);
			model.addAttribute("jobtitle", categoryService.searchCategory("JOB_TITLE"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("newUser	-	End");
		return "manageUser";
	}

	@RequestMapping("/editUser.do")
	public String editUserData(@ModelAttribute("appUser") AppUser appUser, Model model,
			@RequestParam("usrId") String usrid, HttpSession session, HttpServletRequest request) {
		Map<String, String> searchMap = new HashMap<>();
		logger.info("editUserData -- start");
		try {
			AppUser usr = (AppUser) session.getAttribute("userInfo");
			appUser = userService.getUser(usrid);
			/*
			 * get Menu List By userRole By Naresh update 11/01/2018 11:30:00
			 */
			model.addAttribute("menuList", lookingService.getMenuListByUserRole(usr.getUsrType()));
			/*
			 * end Get Menu
			 */
			String msg = request.getParameter("msg");

			String userTypeName = userTypeservice.getUserType(Integer.parseInt(appUser.getUsrType()), true).getName();
			searchMap.put("uid", appUser.getUsrId());

			List<AppUserGroupBranchMapping> groupDetails = new ArrayList<AppUserGroupBranchMapping>();
			List<AppUserGroupBranchMapping> branchDetails = new ArrayList<AppUserGroupBranchMapping>();

			groupDetails = groupService.getGroupDetailsAsList(searchMap);
			searchMap.put("groupId", "0");
			searchMap.put("branchID", "0");
			branchDetails = branchService.getBranchDetailsaAsList(searchMap);

			if (userTypeName.equals("Users") || userTypeName.equals("Managers")) {
				appUser.setBid(String.valueOf(branchDetails.get(0).getBrnachid()));
				appUser.setGid(String.valueOf(groupDetails.get(0).getGroupId()));
			}
			
			model.addAttribute("branchlist", branchDetails);
			model.addAttribute("groupdetails", groupDetails);

			// appUser.setGid("1,2,");
			model.addAttribute("groupDetails", session.getAttribute("groupDetails"));
			model.addAttribute("appUser", appUser);
			model.addAttribute("mode", "update");
			List<UserType> userTypeList = userTypeservice.getUserTypeList();
			model.addAttribute("userTypeList", userTypeList);
			model.addAttribute("jobtitle", categoryService.searchCategory("JOB_TITLE"));
			if (strUtility.checkNullOrEmptyString(msg))
				model.addAttribute("msg", "user");

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("editUserData -- End");

		return "manageUser";
	}

	@RequestMapping(value = "/manageUser.do", params = "add")
	public String insertUserData(@ModelAttribute("appUser") AppUser appUser, Model model, BindingResult result,
			HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("insertUserData	--	start");
		try {

			String pwd = appUser.getUsrPasswd();
			String reTypePwd = request.getParameter("reTypePwd");
			String pwdMsg = "";

			if (!pwd.equals(reTypePwd)) {
				pwdMsg = "Your password and retype password do not match.";
				model.addAttribute("pwdMsg", pwdMsg);
			}

			UserValidator userValidator = new UserValidator();
			userValidator.validateInsertUser(appUser, result);

			if (result.hasErrors() || strUtility.checkNullOrEmptyString(pwdMsg)) {
				List<UserType> userTypeList = userTypeservice.getUserTypeList();
				model.addAttribute("userTypeList", userTypeList);
				return "manageUser";
			}

			AppUser usr = (AppUser) session.getAttribute("userInfo");
			if (usr != null) {
				logger.info("logged In User : " + usr.getUsrName());
				appUser.setCreatedBy(usr.getUsrName());
				appUser.setCreatedDt(new Date());
			}

			if (strUtility.checkNullOrEmptyString(appUser.getUsrName())) {
				appUser.setUsrName(appUser.getUsrName().trim().toLowerCase());
				logger.info("user name in lower case = " + appUser.getUsrName());
			}

			String status = userService.insertUpdateUserData(appUser, "insert");
			// System.out.println(appUser.getGid());
			String userTypeName = userTypeService.getUserType(Integer.parseInt(appUser.getUsrType()), true).getName();

			if (userTypeName.equals("SuperAdmin") && strUtility.checkNullOrEmptyString(appUser.getUsrType())) {

				userService.manageGroupUserAssocs(setGroupUserAssocs("0", appUser));

			}
			if (userTypeName.equals("Admin") && strUtility.checkNullOrEmptyString(appUser.getUsrType())) {

				userService.manageGroupUserAssocs(setGroupUserAssocs(appUser.getGid(), appUser));

			}
			if ((userTypeName.equals("Managers") || userTypeName.equals("Users"))
					&& strUtility.checkNullOrEmptyString(appUser.getUsrType())) {

				if (strUtility.checkNullOrEmptyString(appUser.getGid())
						&& strUtility.checkNullOrEmptyString(appUser.getBid())) {

					userService.manageBranchUserAssocs(setBranchUserAssocs(appUser.getBid(), appUser));

				} else {

					userService.manageGroupUserAssocs(setGroupUserAssocs(appUser.getGid(), appUser));

				}
			}

			model.addAttribute("appUser", appUser);
			redirectAttributes.addFlashAttribute("successMessage", status);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("insertUserData	--	end");
		return "redirect:searchPg";

	}

	@RequestMapping(value = "/manageUser.do", params = "edit")
	public String updateUserData(@ModelAttribute("appUser") AppUser appUser, Model model, HttpSession session,
			BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {

		logger.info("updateUserData --	start");
		logger.debug("appUser.getUsrIsactiveYn()   -  " + appUser.getUsrIsactiveYn());
		try {

			UserValidator userValidator = new UserValidator();
			userValidator.validateUpdateUser(appUser, result);

			if (result.hasErrors()) {

				List<UserType> userTypeList = userTypeservice.getUserTypeList();
				model.addAttribute("userTypeList", userTypeList);

				model.addAttribute("mode", "update");
				return "manageUser";
			}

			AppUser usr = (AppUser) session.getAttribute("userInfo");
			if (usr != null) {
				appUser.setUpdatedBy(usr.getUsrName());
				appUser.setUpdatedDt(new Date());
			}
			System.out.println(appUser.getGid());
			String msg = request.getParameter("msg");
			String status = userService.insertUpdateUserData(appUser, "update");
			String userTypeName = userTypeService.getUserType(Integer.parseInt(appUser.getUsrType()), true).getName();

			if (userTypeName.equals("SuperAdmin") && strUtility.checkNullOrEmptyString(appUser.getUsrType())) {
				appUser.setCreatedBy(usr.getUsrName());
				appUser.setCreatedDt(new Date());
				userService.updateGroupUserAssocs(appUser);
				userService.manageGroupUserAssocs(setGroupUserAssocs("0", appUser));

			}
			if (userTypeName.equals("Admin") && strUtility.checkNullOrEmptyString(appUser.getUsrType())) {
				System.out.println(appUser.getGid());
				appUser.setCreatedBy(usr.getUsrName());
				appUser.setCreatedDt(new Date());
				userService.updateGroupUserAssocs(appUser);
				userService.manageGroupUserAssocs(setGroupUserAssocs(appUser.getGid(), appUser));

			}
			if ((userTypeName.equals("Managers") || userTypeName.equals("Users"))
					&& strUtility.checkNullOrEmptyString(appUser.getUsrType())) {
				userService.updateBranchUserAssocs(appUser);
			}

			model.addAttribute("appUser", appUser);
			redirectAttributes.addFlashAttribute("successMessage", status);
			if (msg.equals("user"))
				return "redirect:/dashboard";

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("updateUserData --	end");
		return "redirect:searchPg";
	}

	@RequestMapping("deactiveUser")
	public String deactiveUser(Model model, @RequestParam("userId") String userId, HttpSession session,RedirectAttributes redirectAttributes) {
		logger.info("User Id = " + userId);
		String loginUser = "";
		try {
			if (strUtility.checkNullOrEmptyString(userId)) {
				AppUser usr = (AppUser) session.getAttribute("userInfo");
				if (usr != null) {
					loginUser = usr.getUsrName();
				}
				userService.deactiveUser(userId.trim(), loginUser);
				redirectAttributes.addFlashAttribute("successMessage", "User delete successfully.");
			} else {
				logger.info("User Id not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage","User not delete  successfully.");
		}
		return "redirect:searchPg";
	}

	public List<GroupUserAssocs> setGroupUserAssocs(String gId, AppUser appUser) {
		List<GroupUserAssocs> groupId1 = new ArrayList<GroupUserAssocs>();
		Map<String, String> searchMap = new HashMap<>();
		try {
			String gid[] = gId.split(",");
			if (!gId.equals("0")) {
				for (int i = 0; i < gid.length; i++) {
					GroupUserAssocs groupUserAssocs = new GroupUserAssocs();
					GroupDetails groupDetails = groupService.getGroupDetails(Integer.parseInt(gid[i]));
					groupUserAssocs.setAppUser(appUser);
					groupUserAssocs.setGroupDetails(groupDetails);
					groupId1.add(groupUserAssocs);
				}
			} else {

				List<GroupDetails> list = groupService.searchGroupDetails(searchMap);
				for (int i = 0; i < list.size(); i++) {
					GroupUserAssocs groupUserAssocs = new GroupUserAssocs();
					groupUserAssocs.setAppUser(appUser);
					groupUserAssocs.setGroupDetails(list.get(i));
					groupId1.add(groupUserAssocs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupId1;

	}

	public List<BranchUserAssocs> setBranchUserAssocs(String bId, AppUser appUser) {
		List<BranchUserAssocs> branchId = new ArrayList<BranchUserAssocs>();
		try {
			String bid[] = bId.split(",");

			for (int i = 0; i < bid.length; i++) {
				BranchUserAssocs branchUserAssocs = new BranchUserAssocs();
				BranchDetails branchDetails = branchService.getBranchDetails(Integer.parseInt(bid[i]));
				branchUserAssocs.setAppUser(appUser);
				branchUserAssocs.setBranchdetails(branchDetails);
				branchId.add(branchUserAssocs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchId;

	}

}
