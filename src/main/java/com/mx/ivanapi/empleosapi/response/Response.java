package com.mx.ivanapi.empleosapi.response;

public class Response {

	private Integer intId;
	private String strTitulo;
	
	public Response(Integer intId, String strTitulo) {
		super();
		this.intId = intId;
		this.strTitulo = strTitulo;
	}
	public Integer getIntId() {
		return intId;
	}
	public void setIntId(Integer intId) {
		this.intId = intId;
	}
	public String getStrTitulo() {
		return strTitulo;
	}
	public void setStrTitulo(String strTitulo) {
		this.strTitulo = strTitulo;
	}
	
}
