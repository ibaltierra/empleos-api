package com.mx.ivanapi.empleosapi.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class Error {

	@ExceptionHandler(ExceptionGeneria.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorResponse resourceNotFoundException(ExceptionGeneria ex, WebRequest request) {
		ErrorResponse message = new ErrorResponse();
		message.setMessage(ex.getMessage());
	    message.setStatus(HttpStatus.BAD_REQUEST.toString());
	    return message;
	}
}
