package com.pyae.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pyae.exception.entity.ErrorDetail;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetail ed = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(ed, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetail> UserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetail ed = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(ed, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationUserException.class)
	public final ResponseEntity<ErrorDetail> CreatedUserWrongException(Exception ex, WebRequest request) throws Exception {
		ErrorDetail ed = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetail>(ed, HttpStatus.BAD_REQUEST);
	}
	
	
	

}
