package com.avee.interceptors;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.avee.domain.AppUser;
import com.avee.form.UserType;
import com.avee.form.UserTypeRole;
import com.avee.service.UserService;
import com.avee.service.UserTypeService;
import com.avee.utility.StringUtility;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeService userTypeservice;

	@Autowired
	private StringUtility strUtility;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
	
		if (session.getAttribute("userInfo") == null && request.getRequestURI().endsWith(".do")
				&& !request.getRequestURI().endsWith("login.do")) {
			response.sendRedirect(request.getContextPath() + "/logout");
			return false;
		}

		com.avee.form.AppUser userForm = (com.avee.form.AppUser) session.getAttribute("userInfo");

		if (userForm != null) {

			AppUser usr = userService.findByName(userForm.getUsrName());

			if (strUtility.checkNullOrEmptyString(usr.getUsrType())) {

				UserType userTypeDomain = userTypeservice.getUserType(Integer.parseInt(usr.getUsrType()), true);
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				for (UserTypeRole usrRole : userTypeDomain.getUserTypeRoles()) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + usrRole.getRole().getRolName()));
				}

				if (authorities.size() == 0) {
					authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				}

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>(authorities); // put
																											// your
																											// updated
																											// authorities
																											// in
																											// a
																											// list
				Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
						auth.getCredentials(), updatedAuthorities);
				 
				 
				SecurityContextHolder.getContext().setAuthentication(newAuth);

			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}