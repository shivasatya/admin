package com.security.dto;


import lombok.Data;

@Data
public class UserDto {

	private String id;

	private String userName;

	private String email;

	private String password;

	private String phoneNumber;

	private String roles;

}
