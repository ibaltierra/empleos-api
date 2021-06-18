package com.mx.ivanapi.empleosapi.response;

import java.util.Date;

public class ErrorResponse{

	private String message;		
	private String status;
	public ErrorResponse() {
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
