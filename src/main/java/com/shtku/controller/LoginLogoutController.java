package com.shtku.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shtku.security.CustomerJdbcDaoImpl;
import com.shtku.security.IChangePassword;

@Controller
public class LoginLogoutController{
	
//	@Autowired
//	@Qualifier("customerJdbcDaoImpl")
//	private CustomerJdbcDaoImpl customerJdbcDaoImpl;
	
	@Autowired
	@Qualifier("jdbcUserService")
	private UserDetailsManager userDetailsManager;
	
	@RequestMapping("/home")
	public String home(Map<String, Object> map){
		
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Map<String, Object> map ,HttpServletRequest request){
		
		
		return "login";
	}
	
	@RequestMapping("/changePassword")
	public String changePassword(){
		return "changePassword";
	}
	
	@RequestMapping("/savePassword")
	public String savePassword(String oldPassword,String newPassword){
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = principal.toString();
//		if(principal instanceof UserDetails){
//			username = ((UserDetails)principal).getUsername();
//		}
//		System.out.println(username);
//		customerJdbcDaoImpl.changePassword(username, password);
		userDetailsManager.changePassword(oldPassword, newPassword);
		
		SecurityContextHolder.clearContext();
		return "redirect:/home";
	}
}
