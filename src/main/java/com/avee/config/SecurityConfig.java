package com.avee.config;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@PropertySource(value = { "classpath:ds.properties" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	static final Logger logger = LogManager.getLogger(SecurityConfig.class.getName());
		
	@Autowired
	@Qualifier("appUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
    DataSource dataSource;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//        	.antMatchers("/resources/**").permitAll()
        	.antMatchers("/", "/dashboard.do").permitAll()
//        	.antMatchers("/polAgent/**").access("hasRole('AGENT')")
//        	.antMatchers("/user/**").access("hasRole('CLIENT')")
        ;
        http.csrf().disable();
        
        //login configuration
    	http.formLogin().loginPage("/login")
      		.defaultSuccessUrl("/dashboard.do")
      		.usernameParameter("username")
      		.passwordParameter("password");
      		

      	//remember me configuration
  		http.rememberMe()
			.rememberMeParameter("remember-me")
			.tokenRepository(persistentTokenRepository())
			.rememberMeCookieName("myRememberMe")
			.tokenValiditySeconds(86400);
      	
      	http.exceptionHandling().accessDeniedPage("/accessDenied");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}