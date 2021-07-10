package com.mx.ivanapi.empleosapi.controller.to;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitudTO {

	private Integer intIdSolicitudes;
	private Date fFecha;
	private String strArchivo;
	private String strComentarios;
	private VacanteTO vacante;	
	private UsuarioTO usuario;
}
