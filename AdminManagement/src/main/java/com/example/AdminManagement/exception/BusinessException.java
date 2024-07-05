package com.example.AdminManagement.exception;

import lombok.Data;

@Data
public class BusinessException  extends Exception{

	private String errorCode;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String message,String errorCode) {
		super(message);
		this.errorCode=errorCode;
	}
}
