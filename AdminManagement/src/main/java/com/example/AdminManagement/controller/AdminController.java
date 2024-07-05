package com.example.AdminManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminManagement.exception.BusinessException;
import com.example.AdminManagement.model.AdminModel;
import com.example.AdminManagement.model.UserDto;
import com.example.AdminManagement.model.VendorMessageDto;
import com.example.AdminManagement.service.AdminService;

@RestController()
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	
	@PostMapping("/create/admin")
	public ResponseEntity<Object> saveAdminDetails(@RequestBody  AdminModel adminModel)
	{
		return ResponseEntity.ok(adminService.saveAdminDetails(adminModel));
	}

	@PostMapping("/add/User")
	public ResponseEntity<String> postDataUser(@RequestBody UserDto userDto) throws BusinessException 
	{
		return ResponseEntity.ok(adminService.postDataUser(userDto));
	}
	@PostMapping("/sending/message")
	public ResponseEntity<Object> sendingMessageToVendor(@RequestBody List<VendorMessageDto> listVendorMessageDto) throws BusinessException 
	{
		return ResponseEntity.ok(adminService.sendingMessageToVendor(listVendorMessageDto));
	}
	
	
	@GetMapping("/get/UserDetails")
	public ResponseEntity<List<UserDto>> getUserDetails() 
	{
		return ResponseEntity.ok(adminService.getUserDetails());
	}
	
	
	@GetMapping("/get/UserDetails/{id}")
	public ResponseEntity<UserDto> getUserDetails(@PathVariable Integer id) throws Exception
	{
		return ResponseEntity.ok(adminService.getUserDetailsById(id));
	}
	
	
	@GetMapping("/delete/user/{id}")
	public ResponseEntity<String> deleteById(@PathVariable String id) throws Exception {
		return ResponseEntity.ok(adminService.deleteUser(id));
	}
	
	
	@GetMapping("/get/adminDetails/{email}")
	public ResponseEntity<AdminModel> getAdminDetails(@PathVariable String email)  {
		return ResponseEntity.ok(adminService.getAdminDetails(email));
	}
	
	@PutMapping("/update/User")
	public ResponseEntity<Object> updateUser(@RequestBody UserDto user) throws BusinessException  {
		return ResponseEntity.ok(adminService.updateUser(user));
	}
	
}
