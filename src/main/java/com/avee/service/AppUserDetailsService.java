package com.avee.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.avee.domain.AppUser;
import com.avee.form.UserType;
import com.avee.form.UserTypeRole;
import com.avee.utility.BeansUtility;

import org.apache.commons.lang.StringUtils;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService {
	static final Logger logger = LogManager.getLogger(AppUserDetailsService.class.getName());

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeService userTypeservice;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		org.springframework.security.core.userdetails.User springUser = null;
		AppUser usr = new AppUser();
		try {
			logger.info("User Name : " + userName);

			if (userName != null && StringUtils.isNotBlank(userName) && StringUtils.isNotEmpty(userName)) {
				userName = userName.trim().toLowerCase();

				if (userName.indexOf(',') == -1) {
					usr = (AppUser) userService.findByName(userName);
					if (usr == null) {
						usr = (AppUser) userService.findByEmail(userName);
						if (usr == null) {

							throw new UsernameNotFoundException("User " + userName + " not found or user not active.");
						}

					}
				} else {
					AppUser superusr = (AppUser) userService.findByName(userName.split(",")[0]);

					if (superusr == null || !(superusr.getUsrType().equals("4"))) {
						superusr = (AppUser) userService.findByEmail(userName.split(",")[0]);
						if (superusr == null || !(superusr.getUsrType().equals("4"))) {

							throw new UsernameNotFoundException(
									"User " + userName.split(",")[0] + " not found or user not active.");
						}

					}
					AppUser byusr = (AppUser) userService.findByName(userName.split(",")[1]);

					if (byusr == null) {
						byusr = (AppUser) userService.findByEmail(userName.split(",")[1]);
						if (byusr == null) {

							throw new UsernameNotFoundException(
									"User " + userName.split(",")[1] + " not found or user not active.");
						}

					}
					List<String> exclusionsList = new ArrayList<String>();
					BeansUtility bub = new BeansUtility();
					bub.copy(usr, byusr, exclusionsList);
					usr.setUsrPasswd(superusr.getUsrPasswd());
				}
			}

			logger.info("User : " + usr);

			springUser = new org.springframework.security.core.userdetails.User(usr.getUsrId(), usr.getUsrPasswd(),
					true, true, true, true, getGrantedAuthorities(usr));
			if (springUser != null && usr != null) {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();
				if (request != null) {
					HttpSession session = request.getSession();
					if (usr != null) {
						com.avee.form.AppUser use_frm = new com.avee.form.AppUser();

						List<String> list = new ArrayList<>();
						list.add("userRoles");
						list.add("usrSignature");

						BeansUtility bub = new BeansUtility();
						bub.copy(use_frm, usr, list);

						session.setAttribute("userInfo", use_frm);
					}
				}
			}

		} catch (final Exception e) {
			e.printStackTrace();

			throw new RuntimeException(e);
		}
		return springUser;

	}

	private List<GrantedAuthority> getGrantedAuthorities(AppUser usr) {

		UserType userTypeDomain = userTypeservice.getUserType(Integer.parseInt(usr.getUsrType()), true);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserTypeRole usrRole : userTypeDomain.getUserTypeRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + usrRole.getRole().getRolName()));
		}

		if (authorities.size() == 0)
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return authorities;
	}

}