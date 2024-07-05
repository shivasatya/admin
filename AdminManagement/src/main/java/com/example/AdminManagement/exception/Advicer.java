package com.example.AdminManagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.AdminManagement.model.ErrorResponse;


@ControllerAdvice
public class Advicer extends    ResponseEntityExceptionHandler  {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> errorMessage(BusinessException businessException) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.errorCode(businessException.getErrorCode())
				.errorMessage(businessException.getMessage()).build();
		return ResponseEntity.ok(errorResponse);

	}

}
