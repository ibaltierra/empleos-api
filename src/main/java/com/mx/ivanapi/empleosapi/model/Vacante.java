package com.mx.ivanapi.empleosapi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
/**
 * Clase que mapea la tabla de vacante
 * @author Ivan Manuel Baltierra Muñoz.
 * 12/11/2020
 *
 */
@Setter
@Getter
@Entity
@Table(name="VACANTES")
public class Vacante implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID_VACANTE")
	@SequenceGenerator(name="SEC_ID_VACANTE", sequenceName="SEC_ID_VACANTE",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_ID_VACANTE") 
	@JsonProperty("intId")
	private Integer 	intId;
	@Column(name="NOMBRE")
	@JsonProperty("strNombre")
	private String 		strNombre;
	@Column(name="DESCRIPCION")
	@JsonProperty("strDescripcion")
	private String 		strDescripcion;
	@Column(name="FECHA")
	@JsonProperty("fFecha")
	//@JsonFormat(pattern="yyyy/MM/dd")
	private Date 		fFecha;
	@Column(name="SALARIO")
	@JsonProperty("dSalario")
	private Double 		dSalario;
	@Column(name="DESTACADO")
	@JsonProperty("intDestacado")
	private Integer 	intDestacado;
	@Column(name="IMAGEN")
	@JsonProperty("strImagen")
	private String 		strImagen ="no-image.png";
	@Column(name="ESTATUS", columnDefinition = "varchar2(20) CHECK( ESTATUS IN ('Creada','Aprobada','Eliminada'))")
	@JsonProperty("strEstatus")
	private String 		strEstatus;
	@Column(name="DETALLES")
	@JsonProperty("strDetalle")
	private String 		strDetalle;
	//ignora la columna @Transient
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID")
	@JsonProperty("categoria")
	private Categoria 	categoria; 
	
	@Override
	public String toString() {
		return "Vacante [intId=" + intId + ", strNombre=" + strNombre + ", strDescripcion=" + strDescripcion
				+ ", fFecha=" + fFecha + ", dSalario=" + dSalario + ", intDestacado=" + intDestacado + ", strImagen="
				+ strImagen + ", strEstatus=" + strEstatus + ", strDetalle=" + strDetalle + ", categoria=" + categoria
				+ "]";
	}
	
	
}
