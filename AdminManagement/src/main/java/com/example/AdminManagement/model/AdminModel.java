package com.example.AdminManagement.model;



import lombok.Data;

@Data
public class AdminModel {

	private String adminId;

	private String userName;

	private String email;

	private String password;

	private String phoneNumber;

	private String roles;
}
