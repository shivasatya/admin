package com.example.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class VendorMessageEntity {

	@Id
	@GeneratedValue
	private Integer id;

	private String email;

	private String name;

	private String vendorTemplate;

	private String vendorUpi;

}
