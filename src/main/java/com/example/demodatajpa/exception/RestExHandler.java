package com.example.demodatajpa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.demodatajpa.controller.EmployeeController;
import com.example.demodatajpa.model.ErrorResp;

@RestControllerAdvice
public class RestExHandler {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@ExceptionHandler(AuthenticationException.class)
	public ErrorResp handleAuthEx(AuthenticationException ex) {
		logger.error("Error: {}", ex.getLocalizedMessage());
		return new ErrorResp("auth123", ex.getLocalizedMessage());
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ErrorResp handleBadCredEx(BadCredentialsException ex) {
		logger.error("Error: {}", ex.getLocalizedMessage());
		return new ErrorResp("auth122", ex.getLocalizedMessage());
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResp handleNoHandlerFoundException(NoHandlerFoundException ex) {
		logger.error("Error: {}", ex.getLocalizedMessage());
		return new ErrorResp("auth121", ex.getLocalizedMessage());
	}
}
