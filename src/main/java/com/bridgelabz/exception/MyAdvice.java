package com.bridgelabz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyAdvice {
	
	@ExceptionHandler(UserException.class)
	private ResponseEntity<Object> handleException(UserException userException){
		String message =userException.getMessage();
		return new ResponseEntity<Object>(message,HttpStatus.NOT_FOUND);
		
	}
	
}
