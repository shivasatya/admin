package com.example.userManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userManagement.entity.UsereEntity;
import com.example.userManagement.entity.VendorMessageEntity;
import com.example.userManagement.repository.UserRepository;
import com.example.userManagement.repository.VendorRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VendorMessage {

	@Autowired
	VendorRepo vendorRepo;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	public String sendMessageTemplates(String id) throws Exception {

		log.info("fetching vendor message  details");
		Optional<UsereEntity> vendorcheck = userRepository.findById(id);
		if (vendorcheck.isPresent()) {
			String message = "Sending payments to vendor " + vendorcheck.get().getUserName() + " upi "
					+ vendorcheck.get().getUpi();
			
			VendorMessageEntity  vendorMessageEntity = new VendorMessageEntity();
			vendorMessageEntity.setVendorUpi(vendorcheck.get().getUpi());
			vendorMessageEntity.setName(vendorcheck.get().getUserName());
			vendorMessageEntity.setEmail(vendorcheck.get().getEmail());
			vendorMessageEntity.setVendorTemplate(message);
			// Email Sending code here
			
			emailService.sendSimpleMessage(vendorcheck.get().getEmail(), "Vendor Sending money", message);
			log.info("message is sending in email");
			try {
				vendorRepo.save(vendorMessageEntity);
			} catch (Exception e) {

				throw new Exception("Vendor Data IS Not Saved");
			}

		} else {
			throw new Exception("Vendor Message Details By Id" + id);
		}

		return null;

	}
	
	
	public List<VendorMessageEntity> getListVendorMessageDetails()
	{
		log.info("fetching vendor message  details");
		return vendorRepo.findAll();
	}
	
	public VendorMessageEntity getVendorMessageDetailsById(Integer id) throws Exception
	{
		log.info("fetching vendor message  details");
	Optional<VendorMessageEntity> venOptional =	vendorRepo.findById(id);
	if(venOptional.isPresent()) {
		return venOptional.get();
	}
	else {
	throw new Exception("Vendor Message Details By Id" + id);
	}
		
	}
	
	public List<VendorMessageEntity> getListVendorMessageDetailsByEmail(String email) throws Exception
	{
		List<VendorMessageEntity> vendorMessageDetail = vendorRepo.findByEmail(email);
		if (!vendorMessageDetail.isEmpty()) {
			return vendorMessageDetail;
		} else {
			throw new Exception("THEY IS NO VENDOR MESSAGE DATA WITH THIS EMAIL" + email);
		}

	}
}
