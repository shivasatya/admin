package com.example.userManagement.model;


import lombok.Data;

@Data
public class UserDto {

	private String id;

	private String userName;

	private String email;

	private String password;

	private String ctc;
	
	private String designation;
	private String upi;
	private String roles;

}
