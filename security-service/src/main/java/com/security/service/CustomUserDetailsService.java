package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.config.UserInfo;
import com.security.dto.UserDto;

public class CustomUserDetailsService  implements UserDetailsService {

	@Autowired
	UserDetailsData userDetails;
	@Override
	public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			UserDto user =	userDetails.getUserDetailsByEmail(email);
			return new UserInfo(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
