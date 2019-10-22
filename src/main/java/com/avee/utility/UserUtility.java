package com.avee.utility;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.avee.form.AppUser;

public class UserUtility {
	
	public static AppUser getSessionUser() {
		AppUser usr = null;
		try{
			ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			
			HttpSession httpSession = request.getRequest().getSession();
			usr = (AppUser) httpSession.getAttribute("userInfo");
		}catch(Exception e) {
			
		}
		return usr;
	}
	
	
	public static String getSessionUserName() {
		AppUser usr = getSessionUser();
		String usrName = usr == null ? "APP_USER" : usr.getUsrName();
		return usrName;
	}
	
	public static String getSessionUserId() {
		AppUser usr = getSessionUser();
		String usrId = usr == null ? "APP_USER" : usr.getUsrId();
		return usrId;
	}
}
