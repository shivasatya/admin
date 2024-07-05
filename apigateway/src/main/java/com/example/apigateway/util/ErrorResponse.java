package com.example.apigateway.util;



public class ErrorResponse {

	public ErrorResponse(String message, String errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	private String message;
	private String errorCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
