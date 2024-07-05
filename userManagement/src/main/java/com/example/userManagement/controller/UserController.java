package com.example.userManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userManagement.model.OrderDto;
import com.example.userManagement.model.UserDto;
import com.example.userManagement.service.RefreshTokenService;
import com.example.userManagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	HttpServletRequest httpServletRequest;

	@PostMapping("/save/employees")
	public ResponseEntity<String> createUsers(@RequestBody UserDto userDetails) {
		return ResponseEntity.ok(userService.createCompanyRoleUsers(userDetails));

	}
	
	
	
	
	
	@PostMapping("/sigup")
	public ResponseEntity<String> createUserSigup(@RequestBody UserDto userDetails) {
		return ResponseEntity.ok(userService.createUserSigup(userDetails));
		
	}
	@GetMapping("/get/UserDetails")
	public ResponseEntity<List<UserDto>> getUserDetails() {
		return ResponseEntity.ok(userService.getUserDetails());

	}
	@GetMapping("/get/UserDetails/{id}")
	public ResponseEntity<UserDto> getUserDetailsById(@PathVariable String id) throws Exception {
		return ResponseEntity.ok(userService.getUserDetailsById(id));
	}
	@GetMapping("/get/UserDetails/email/{email}")
	public ResponseEntity<UserDto> getUserDetailsByName(@PathVariable String email) throws Exception {
		return ResponseEntity.ok(userService.getUserDetailsByEmail(email));
	}

	@PostMapping("/order/product")
	public ResponseEntity<String> userToOrderProduct(OrderDto order){
		return ResponseEntity.ok(userService.orderProduct(order));

	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<String> userToOrderProduct(@PathVariable String id){
		return ResponseEntity.ok(userService.deleteUser(id));

	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> userUpdate(@RequestBody UserDto userDto) throws Exception{
		return ResponseEntity.ok(userService.userUpdate(userDto));

	}

}
