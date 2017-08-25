package com.shtku.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shtku.dao.ICommonDao;
import com.shtku.entity.Users;
import com.shtku.service.ICommonService;
@Service("customerJdbcDaoImpl")
public class CustomerJdbcDaoImpl  implements IChangePassword {
	@Autowired
	@Qualifier("commonDao")
	private ICommonDao commonDao;
	@Autowired
	@Qualifier("commonService")
	private ICommonService commonService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
			Users users = commonDao.findUniqueByProperty(Users.class, "username", username);
			if(users==null){
				throw new UsernameNotFoundException("用户" + username + " 不存在!!!"); 
			}
			String[] roles = users.getRoles().split(",");  
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			for(String role : roles)  
			{  
				authorities.add(new SimpleGrantedAuthority(role));  
			}  
			return new User(users.getUsername(), users.getPassword(), true,true,true,true,authorities); 
		
		
	}


	@Override
	@Transactional
	public void changePassword(String username, String password) {
		Users users = commonDao.findUniqueByProperty(Users.class, "username", username);
		if(users==null){
			throw new UsernameNotFoundException("用户" + username + " 不存在!!!"); 
		}
		users.setPassword(password);
		commonService.saveOrUpdate(users);
	}
	
	

}
