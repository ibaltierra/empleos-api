package com.mx.ivanapi.empleosapi.controller.to;


import java.util.Date;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioTO {

	private Integer intIdUsuario;
	private String strNombre;
	private String strEmail;
	private String strUserName;
	private String strPassword;
	private Integer intEstatus;
	private Date fFechaRegistro;
}
