package com.portfolio.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService{
	
	@Autowired
	private SecurityDao securityDao;
	
	@Override
	public SecUserDetails loadUserByUsername(String username) {

			SecUserDetails user = null;
			try {
				user = securityDao.getUserById(username);
				if(user==null)throw new UsernameNotFoundException("");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user;
			
			
		
	}


}
