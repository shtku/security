package com.shtku.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class MyJdbcUserDetailManager extends JdbcUserDetailsManager{

	@Autowired 
	private PasswordEncoder passwordEncoder;
	@Autowired 
	private SaltSource saltSource;
	@Override
	public void changePassword(String oldPassword, String newPassword)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException("Can't change password as no Authentication object found in context " +
                    "for current user.");
        }

        String username = currentUser.getName();
        UserDetails user = loadUserByUsername(username);
        String encodedPassword = passwordEncoder.encodePassword (newPassword, saltSource.getSalt(user));
		super.changePassword(oldPassword, encodedPassword);
	}
	
}
