package com.example.userManagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UsereEntity {

	@Id
	private String id;

	private String userName;

	private String email;

	private String password;
	private String ctc;
	private String designation;
	private String upi;
	private String roles;

}
