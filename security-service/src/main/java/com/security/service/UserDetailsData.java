package com.security.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.security.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class UserDetailsData {
	public static final String urlGet = "http://localhost:8080/user/get/UserDetails/";
	public static final String urlGetByEmail = "http://localhost:8080/user/get/UserDetails/email";
	public static final String urlAdminGetByEmail = "http://localhost:8085/admin/get/adminDetails";
	
	public List<UserDto> getUserDetails() {
		List<UserDto> getUserDetails = null;
		try {
			WebClient webClient = WebClient.create();
			getUserDetails = webClient.get().uri(urlGet).retrieve().bodyToMono(List.class).block();

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return getUserDetails;
	}
	
	public UserDto getUserDetailsByEmail(String email) throws Exception {
		UserDto getUserDetails = null;
		try {
			WebClient webClient = WebClient.create();
			getUserDetails = webClient.get().uri(urlGetByEmail + "/{email}", email).retrieve().bodyToMono(UserDto.class).block();
			if(getUserDetails==null) {
				getUserDetails = 	getAdminUserDetailsByEmail(email);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return getUserDetails;
	}
	public UserDto getAdminUserDetailsByEmail(String email) throws Exception {
		UserDto getUserDetails = null;
		try {
			WebClient webClient = WebClient.create();
			getUserDetails = webClient.get().uri(urlAdminGetByEmail + "/{email}", email).retrieve().bodyToMono(UserDto.class).block();
			log.info(getUserDetails.getEmail());
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return getUserDetails;
	}
	
	
}
