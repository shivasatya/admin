package com.example.apigateway.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


public class ControllerAdvicer extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ExceptionHandlerCustomer.class)
	public ResponseEntity<ErrorResponse> customerExceptionHandeler(ExceptionHandlerCustomer exceptionHandlerCustomer){
		ErrorResponse errorResponse = new ErrorResponse(exceptionHandlerCustomer.getMessage(), exceptionHandlerCustomer.getErrorCode());
		return ResponseEntity.ok(errorResponse);

	}

}

