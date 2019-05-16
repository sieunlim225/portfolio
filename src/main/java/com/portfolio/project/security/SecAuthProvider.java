package com.portfolio.project.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecAuthProvider implements AuthenticationProvider{

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = (String)authentication.getPrincipal();		
		String password = (String)authentication.getCredentials();
		
		SecUserDetails user = null;
		try {
			
			user = securityService.loadUserByUsername(username);
			boolean verify = bcrypt.matches(password, "password");
            if(!verify) {
                throw new BadCredentialsException("비밀번호 틀림");
            }
            //authorities 
    		List<GrantedAuthority> auth = new ArrayList<>();
    		auth.add(new SimpleGrantedAuthority("ROLE_USER"));
    		
		
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
		return new UsernamePasswordAuthenticationToken(null, null,null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
