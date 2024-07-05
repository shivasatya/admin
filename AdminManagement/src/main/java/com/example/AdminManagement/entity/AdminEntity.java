package com.example.AdminManagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AdminEntity {

	@Id
	private String adminId;

	private String userName;

	private String email;

	private String password;

	private String phoneNumber;

	private String roles;

}
