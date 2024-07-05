package com.example.apigateway.util;


public class ExceptionHandlerCustomer  extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ExceptionHandlerCustomer(String message ,String errorCode) {
		super(message);
		this.errorCode = errorCode;

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
