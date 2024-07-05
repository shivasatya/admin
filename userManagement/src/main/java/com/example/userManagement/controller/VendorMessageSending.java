package com.example.userManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userManagement.entity.VendorMessageEntity;
import com.example.userManagement.service.VendorMessage;

@RestController
@RequestMapping("/vendor")
public class VendorMessageSending {
	

	@Autowired
	VendorMessage vendorMessage;
	
	@GetMapping("/send/message/{id}")
	public ResponseEntity<String> sendingMessageToVendor(@PathVariable String id) throws Exception {
		return ResponseEntity.ok(vendorMessage.sendMessageTemplates(id));
	}
	@GetMapping("/fetching/message/value/{email}")
	public ResponseEntity<List<VendorMessageEntity>> fetchingVendorMessageByEmail(@PathVariable String email) throws Exception {
		
		return ResponseEntity.ok(vendorMessage.getListVendorMessageDetailsByEmail(email));
	}
	@GetMapping("/fetching/message/data/{id}")
	public ResponseEntity<VendorMessageEntity> getUserDetailsById(@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(vendorMessage.getVendorMessageDetailsById(id));
	}
	@GetMapping("/fetching/message")
	public ResponseEntity<List<VendorMessageEntity>> fetchingVendorMessage()  {
		
		return ResponseEntity.ok(vendorMessage.getListVendorMessageDetails());
	}

}
