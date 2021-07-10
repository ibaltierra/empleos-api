package com.mx.ivanapi.empleosapi.controller.to;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VacanteTO {

	@JsonProperty("intId")
	private Integer 	intId;
	@JsonProperty("strNombre")
	private String 		strNombre;
	@JsonProperty("strDescripcion")
	private String 		strDescripcion;
	@JsonProperty("fFecha")
	private Date 		fFecha;
	@JsonProperty("dSalario")
	private Double 		dSalario;
	@JsonProperty("intDestacado")
	private Integer 	intDestacado;
	@JsonProperty("strImagen")
	private String 		strImagen ="no-image.png";
	@JsonProperty("strEstatus")
	private String 		strEstatus;
	@JsonProperty("strDetalle")
	private String 		strDetalle;	
	@JsonProperty("categoria")
	private CategoriaTO 	categoria;

	
}
