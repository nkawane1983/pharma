package com.avee.controller;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avee.domain.AppUser;
import com.avee.form.BranchDetails;
import com.avee.service.BranchService;
import com.avee.service.LookingService;
import com.avee.service.SysParamService;
import com.avee.service.UserService;
import com.avee.utility.EmailUtil;
import com.avee.utility.EncryptDecryptUtility;
import com.avee.utility.MyCustomNumberEditor;
import com.avee.utility.StringUtility;

@Controller
public class LoginController {
	
	static final Logger logger = LogManager.getLogger(LoginController.class.getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	LookingService lookingService;

	@Autowired
	BranchService branchService;

	@Autowired
	private StringUtility strUtility;
	@Autowired
	private EmailUtil emailUtil;
	
	
	@Autowired
	SysParamService sysParamService;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
	    binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
	    binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
	    binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	}
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(HttpSession session) {
		logger.info(" login : start ");

		return "loginPg";
	}

	// @RequestMapping("/logout.do")
	// public String logout(HttpServletRequest request, Model model){
	// logger.info("logout - start");
	//
	// request.getSession(false).invalidate();
	//
	// logger.info("logout - end");
	// return "loginForm";
	// }

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		logger.info(" logout : start ");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		handleLogOutResponse(response, request);
		logger.info(" logout : End ");
		return "redirect:/login?logout";
	}

	private void handleLogOutResponse(HttpServletResponse response, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {

			cookie.setMaxAge(0);

			cookie.setValue(null);

			cookie.setPath("/");

			response.addCookie(cookie);

		}

	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}

		return userName;
	}

	@RequestMapping(value = "/managersAuth")
	public @ResponseBody String managers(HttpServletRequest request) {
		logger.info(" managersAuthPg : start ");
		StringWriter writer = null;

		try {

			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);

			VelocityContext context = new VelocityContext();

			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/Manager's_Authorisation.vm", "utf-8", context, writer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(" managersAuthPg : End ");
		return writer == null ? "" : writer.toString();
	}

	@RequestMapping(value = "/managersLogin")
	public @ResponseBody String loginManager(@RequestParam String username, @RequestParam String password,
			HttpSession session) {
		String str = null;
		Map<String, String> searchMap = new HashMap<>();
		BranchDetails branchdetails = (BranchDetails) session.getAttribute("branchDetails");
		logger.info("managersLogin- start");
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			AppUser usr = (AppUser) userService.findByName(username);
			if (usr == null) {
				str = "error";
			} else {
				if (!(passwordEncoder.matches(password, usr.getUsrPasswd()))) {
					str = "error";
				} else {
					if (usr.getUsrType().equals("1") || usr.getUsrType().equals("3")) {
						searchMap.put("groupId", String.valueOf(branchdetails.getGroupId()));
						searchMap.put("userId", usr.getUsrId());
						searchMap.put("branchId", String.valueOf(branchdetails.getId()));
						if (lookingService.verifyGroupAndBranch(searchMap)) {
							str = "done";
						} else {
							str = "error";
						}

					} else {
						if (usr.getUsrType().equals("4")) {
							str = "done";
						} else {
							str = "error";
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("managersLogin - end");
		return str;

	}

	@RequestMapping("/forgotPwdPg")
	public String forgotPwdPg(Model model, HttpSession session) {
		logger.info("in forgotPwdPg ...");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ("forgotPwd");
	}

	@RequestMapping(value = "/forgotPwd", method = RequestMethod.POST)
	public String forgotPwd(HttpServletRequest request, Model model) {
		logger.info("forgotPwd - start");
		String returnStr = "loginPg";
		String emailId = request.getParameter("emailId");
		if (strUtility.checkNullOrEmptyString(emailId)) {
			AppUser usr = (AppUser) userService.findByEmail(emailId);
			if (usr != null) {
				logger.info("email : " + usr.getUsrEmail());
				String userId = usr.getUsrId();
				// ======================================================
				String hour = sysParamService.getSystemParameter("VALID_HOUR_FOR_LINK").getParameterValue();
				int intHour = 1;
				try {
					intHour = Integer.parseInt(hour);
				} catch (Exception e) {
					intHour = 1; // set default to one hour
				}
				Calendar c = Calendar.getInstance();
				c.add(Calendar.HOUR, intHour);

				String info = userId + "#" + c.getTime();
				String encInfo = EncryptDecryptUtility.encryptPhrase(info);
				try {
					encInfo = URLEncoder.encode(encInfo, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				String url = sysParamService.getSystemParameter("APPLICATION_URL").getParameterValue();
				url = url + "/forgotChangePwdPg?id=" + encInfo;

				String from = sysParamService.getSystemParameter("EMAIL_FROM").getParameterValue();
				String host = sysParamService.getSystemParameter("HOST").getParameterValue();
				String port = sysParamService.getSystemParameter("PORT").getParameterValue();
				String pwd = sysParamService.getSystemParameter("SMTP_Password").getParameterValue();
				Map<String, Object> map = new HashMap<>();
				map.put("to", usr.getUsrEmail());
				map.put("from", from);
				map.put("host", host);
				map.put("port", port);
				String emailBody = getRestString(usr.getUsrFirstName() + " " + usr.getUsrLastName(), url);
				map.put("url", emailBody);
				map.put("subject", "Forgot Password");
				map.put("pwd", pwd);
				// System.out.println(emailBody);

				emailUtil.sendMailConfig(map);
				model.addAttribute("message", "Reset password link send to your email.");
			} else {
				model.addAttribute("pwdMessage", "Invalid Email ID");
				returnStr = "forgotPwd";
			}
		} else {
			model.addAttribute("pwdMessage", "Invalid Email ID");
			returnStr = "forgotPwd";
		}

		logger.info("forgotPwd - end");
		return returnStr;
	}

	@RequestMapping(value = "/forgotChangePwdPg", method = RequestMethod.GET)
	public String forgotChangePwdPg(HttpServletRequest request, Model model) {
		logger.info("forgotChangePwdPg - start");

		String info = request.getParameter("id");

		String decInfo = EncryptDecryptUtility.decryptPhrase(info);
		String[] arr = decInfo.split("#");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
		try {
			Date date = sdf.parse(arr[1]);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = cal.compareTo(Calendar.getInstance());
		if (result == -1) {
			model.addAttribute("pwdMessage", "Link expired! Click on Forgot Password to send request again.");
			return "forgotPwd";
		}

		model.addAttribute("usrId", arr[0]);
		logger.info("forgotChangePwdPg - end");
		return "forgotChangePwd";
	}

	@RequestMapping("/supportPg")
	public String supportPg(Model model, HttpSession session) {
		logger.info("in Support Login Start: ...");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ("supportloginPg");
	}

	@RequestMapping("/testpg")
	public String testpg(Model model, HttpSession session) {
		logger.info("in Support Login Start: ...");
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ("test");
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(Map<String, Object> map) {
		String host = (String) map.get("host");
		String port = (String) map.get("port");
		String to = (String) map.get("to");
		String from = (String) map.get("from");
		String url = (String) map.get("url");
		String strSubject = (String) map.get("subject");
		String pwd = (String) map.get("pwd");
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.ssl.trust", host);
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		javaMailSender.setHost(host);
		javaMailSender.setPort(Integer.parseInt(port));
		javaMailSender.setUsername(from);
		javaMailSender.setPassword(pwd);
		javaMailSender.setJavaMailProperties(properties);

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(to);
			helper.setSubject(strSubject);
			mimeMessage.setContent(url, "text/html; charset=utf-8");
			mimeMessage.setFrom(new InternetAddress(from));
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest request, Model model) {
		logger.info("changePassword - start");

		model.addAttribute("usrId", request.getParameter("usrId"));
		model.addAttribute("usrName", request.getParameter("usrName"));

		String usrId = request.getParameter("usrId");
		String usrName = request.getParameter("usrName");
		String newPw = request.getParameter("newpwd");

		Map<String, String> map = new HashMap<>();
		map.put("usrId", usrId);
		map.put("usrName", usrName);
		map.put("newPw", newPw);

		String msg = userService.changeUserPassword(map);
		String returnStr = "loginPg";

		if (msg != null && StringUtils.isNotEmpty(msg) && StringUtils.isNotBlank(msg)) {
			model.addAttribute("message", msg);
		} else {
			msg = "Password cannot be changed.";
			model.addAttribute("message", msg);
			returnStr = "changePasswordPage";
		}
		logger.info("changePassword - end");
		return returnStr;
	}

	public String getRestString(String username, String url) {
		StringWriter writer = null;
		try {
			Properties properties = new Properties();
			properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
			VelocityEngine velocityEngine = new VelocityEngine(properties);
			VelocityContext context = new VelocityContext();
			context.put("user", username);
			context.put("url", url);
			writer = new StringWriter();
			velocityEngine.mergeTemplate("vm/resetPwd.vm", "utf-8", context, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer == null ? "" : writer.toString();
	}

}