package com.example.AdminManagement.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.AdminManagement.entity.AdminEntity;
import com.example.AdminManagement.exception.BusinessException;
import com.example.AdminManagement.model.AdminModel;
import com.example.AdminManagement.model.UserDto;
import com.example.AdminManagement.model.VendorMessageDto;
import com.example.AdminManagement.repository.AdminRepo;
import com.example.AdminManagement.util.SecurityCheck;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {

	public static final String urlPost = "http://localhost:8080/user/save/employees"; 
	public static final String updateurlPost = "http://localhost:8080/user/update";
	public static final String urlGet = "http://localhost:8080/user/get/UserDetails/";
	public static final String urlGetDelete = "http://localhost:8080/user/delete";
	public static final String sendingMessageUrl = "http://localhost:8080/vendor/send/message";
 
	@Autowired
	AdminRepo adminRepo;

	@Autowired
	SecurityCheck securityCheck;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public String postDataUser(UserDto userDto) throws BusinessException {
		String data = null;
		try {
			WebClient client = WebClient.create();
			data = client.post().uri(urlPost).contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", securityCheck.getToken()).body(BodyInserters.fromValue(userDto)).retrieve()
					.bodyToMono(String.class).block();
			
           return data;
		} catch (Exception e) {
			throw new BusinessException("Authorization User Issues", "500");
		}

	}

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

	public UserDto getUserDetailsById(Integer id) throws Exception {
		UserDto getUserDetails = null;
		try {
			WebClient webClient = WebClient.create();
			getUserDetails = webClient.get().uri(urlGet + "/{id}", id).retrieve().bodyToMono(UserDto.class).block();

		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return getUserDetails;
	}

	public AdminModel getAdminDetails(String email)  {
		AdminEntity adminEntity = adminRepo.findByEmail(email);
		AdminModel adminModel = new AdminModel();
		adminModel.setEmail(adminEntity.getEmail());
		adminModel.setRoles(adminEntity.getRoles());
		adminModel.setUserName(adminEntity.getUserName());
		adminModel.setPhoneNumber(adminEntity.getPhoneNumber());
		adminModel.setPassword(adminEntity.getPassword());
		adminModel.setAdminId(adminEntity.getAdminId());
		return adminModel;
	}

	public Object saveAdminDetails(AdminModel adminModel) {
		adminModel.setAdminId(UUID.randomUUID().toString());
		adminModel.setPassword(passwordEncoder.encode(adminModel.getPassword()));
		AdminEntity adminEntity = new ModelMapper().map(adminModel, AdminEntity.class);
		
		adminRepo.save(adminEntity);
		return "Saved";
	}

	public String deleteUser(String id) {
		String getUserDetails = null;
		try {
			WebClient webClient = WebClient.create();
			
			getUserDetails = webClient.get()
                    .uri(urlGetDelete + "/{id}", id)
                    .header("Authorization", securityCheck.getToken()) // Assuming Bearer token
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return getUserDetails;
	}

	public Object updateUser(UserDto user) throws BusinessException {
		String data = null;
		try {
			WebClient client = WebClient.create();
			data = client.put().uri(updateurlPost).contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", securityCheck.getToken()).body(BodyInserters.fromValue(user)).retrieve()
					.bodyToMono(String.class).block();
			
           return data;
		} catch (Exception e) {
			throw new BusinessException("Authorization User Issues", "500");
		}
	}

	public Object sendingMessageToVendor(List<VendorMessageDto> listVendorMessageDto)
			throws BusinessException {
		String data = null;
		try {
			WebClient client = WebClient.create();
			data = client.post().uri(sendingMessageUrl).contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", securityCheck.getToken())
					.body(BodyInserters.fromValue(listVendorMessageDto)).retrieve().bodyToMono(String.class).block();

			return data;
		} catch (Exception e) {
			throw new BusinessException("Authorization User Issues", "500");
		}
	}

}
