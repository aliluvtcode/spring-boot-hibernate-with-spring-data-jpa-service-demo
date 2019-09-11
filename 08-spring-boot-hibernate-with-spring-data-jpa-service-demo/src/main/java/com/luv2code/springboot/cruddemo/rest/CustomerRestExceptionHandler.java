package com.luv2code.springboot.cruddemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	//add the exception handling code here 
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse>handleException(CustomerNotFoundException exc){
	
		//create a studentErrorResponse
		CustomerErrorResponse error =new CustomerErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		//return ResponseEntity
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse>handleException(Exception exc){// we used here only Exception exc to cover all the errors(not only the errors cuz the error number like up
		//create a studentErrorResponse
				CustomerErrorResponse error =new CustomerErrorResponse();
				error.setStatus(HttpStatus.BAD_REQUEST.value());
				error.setMessage("Worng input number");
				error.setTimeStamp(System.currentTimeMillis());
				
				//return ResponseEntity
				
				return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
		
	}
	
}
