package com.example.userManagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userManagement.entity.UsereEntity;
import com.example.userManagement.model.OrderDto;
import com.example.userManagement.model.UserDto;
import com.example.userManagement.orderTemplate.OrderTemplate;
import com.example.userManagement.repository.UserRepository;
import com.example.userManagement.util.CommonUtil;
import com.example.userManagement.util.SecurityCheck;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityCheck securityCheck;

	@Autowired
	private OrderTemplate orderTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	
	public String createCompanyRoleUsers(UserDto userDto) {
		if (securityCheck.validToken().stream()
				.anyMatch(token -> token.equals("ROLE_ADMIN") || token.equals("ROLES_SUPERADMIN"))) {
			
			Optional<UsereEntity> userDuplicate = userRepository.findByEmailAndRoles(userDto.getEmail(),"ROLE_"+userDto.getRoles());
			if (!userDuplicate.isPresent()) {
				try {
					ModelMapper mapper = new ModelMapper();
					userDto.setId(UUID.randomUUID().toString());
					userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
					String addRole = "ROLE_" + userDto.getRoles().toUpperCase();
					userDto.setRoles(addRole);
					userDto.setUserName(userDto.getUserName());
					userDto.setCtc(userDto.getCtc());
					userDto.setUpi(userDto.getUpi());
					UsereEntity userDetails = mapper.map(userDto, UsereEntity.class);
					userDetails = userRepository.save(userDetails);
					return "CREATED USER WITH THIS ID " + userDetails.getId();
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			} else {
				return "DUPLICATE EMAIL";
			}
		}
		return "AUTHORIZATION";
	}


	public String createUserSigup(UserDto userDto) {
		if (securityCheck.validToken().stream()
				.anyMatch(token -> token.equals("ROLE_ADMIN") || token.equals("ROLES_SUPERADMIN"))) {
			Optional<UsereEntity> userDuplicate = userRepository.findByEmailAndRoles(userDto.getEmail(),
					userDto.getRoles());
			if (!userDuplicate.isPresent()) {
				try {
					ModelMapper mapper = new ModelMapper();
					userDto.setId(UUID.randomUUID().toString());
					userDto.setRoles("ROLE_" + userDto.getRoles().toUpperCase());
					userDto.setUserName(userDto.getUserName());
					userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
					UsereEntity userDetails = mapper.map(userDto, UsereEntity.class);
					userDetails = userRepository.save(userDetails);
					return "CREATED USER WITH THIS ID " + userDetails.getId();
				} catch (Exception e) {
					log.info(e.getMessage());
				}
			}

			return "DUPLICATE EMAIL ...ALREADY THIS EMAIL REGISTRY";
		}
		return null;
	}


	public List<UserDto> getUserDetails() {
		if (securityCheck.validToken().stream()
				.anyMatch(token -> token.equals("ROLE_ADMIN"))) {
		return CommonUtil.convertDtoToEntity(userRepository.findAll());
		}
		
		 throw new RuntimeException("Invalid 403");
	}

	public UserDto getUserDetailsById(String id) throws Exception {
		Optional<UsereEntity> userDetails = userRepository.findById(id);
		if (userDetails.isPresent()) {
			return CommonUtil.convertEntityToDto(userDetails.get());
		}
		throw new Exception("THEY IS NO DATA WITH ID " + id);
	}

	public String orderProduct(OrderDto order) {
		if(order!=null) {
			return orderTemplate.postUserOrderDetails(order);
		}
		else
			return "order Details is null";
	}



	public UserDto getUserDetailsByEmail(String email) {
		Optional<UsereEntity> userDetails = userRepository.findByEmail(email);
		if (userDetails.isPresent()) {
			return new ModelMapper().map(userDetails.get(), UserDto.class);
		}
		return null;
	}


	public String deleteUser(String id) {
		if (securityCheck.validToken().stream()
				.anyMatch(token -> token.equals("ROLE_ADMIN"))) {
		userRepository.deleteById(id);
		}
		else {
			return "INVALID ADMIN";
		}
		return "Deleted user Id with " +id;
	}


	public Object userUpdate(UserDto userDto) throws Exception {
		if (securityCheck.validToken().stream()
				.anyMatch(token -> token.equals("ROLE_ADMIN") || token.equals("ROLES_SUPERADMIN"))) {
			Optional<UsereEntity> userData = userRepository.findById(userDto.getId());
			if (userData.isPresent()) {
				try {
					ModelMapper mapper = new ModelMapper();
					userDto.setUserName(userData.get().getUserName());
					userDto.setPassword(passwordEncoder.encode(
							userDto.getPassword() != null ? userDto.getPassword() : userData.get().getPassword()));
					userDto.setRoles(userDto.getRoles() != null ? userDto.getRoles() : userData.get().getRoles());
					userDto.setEmail(userData.get().getEmail());
					userDto.setCtc(userDto.getCtc());
					userDto.setDesignation(userDto.getDesignation() != null ? userDto.getDesignation()
							: userData.get().getDesignation());
					UsereEntity userDetails = mapper.map(userDto, UsereEntity.class);
					
					userDetails = userRepository.save(userDetails);
					return "UPDATED USER WITH THIS ID " + userDetails.getId();
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
			} else {
				return "User IS NOT PRESENT WITH THIS ID " + userDto.getId();
			}
		}
		return "AUTHORIZATION";
	}


	


}
