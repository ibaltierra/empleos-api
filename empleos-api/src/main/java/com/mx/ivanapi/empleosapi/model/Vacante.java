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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Clase que mapea la tabla de vacante
 * @author Ivan Manuel Baltierra Muñoz.
 * 12/11/2020
 *
 */
@Entity
@Table(name="VACANTES")
public class Vacante implements Serializable{
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
    @JoinColumns({
        @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID")})
	@JsonProperty("categoria")
	private Categoria 	categoria; 
	/**
	 * 
	 * @return
	 */
	public Integer getIntId() {
		return intId;
	}
	/**
	 * 
	 * @param intId
	 */
	public void setIntId(Integer intId) {
		this.intId = intId;
	}
	/**
	 * 
	 * @return
	 */
	public String getStrNombre() {
		return strNombre;
	}
	/**
	 * 
	 * @param strNombre
	 */
	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}
	/**
	 * 
	 * @return
	 */
	public String getStrDescripcion() {
		return strDescripcion;
	}
	/**
	 * 
	 * @param strDescripcion
	 */
	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}
	/**
	 * 
	 * @return
	 */
	public Date getfFecha() {
		return fFecha;
	}
	/**
	 * 
	 * @param fFecha
	 */
	public void setfFecha(Date fFecha) {
		this.fFecha = fFecha;
	}
	/**
	 * 
	 * @return
	 */
	public Double getdSalario() {
		return dSalario;
	}
	/**
	 * 
	 * @param dSalario
	 */
	public void setdSalario(Double dSalario) {
		this.dSalario = dSalario;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getIntDestacado() {
		return intDestacado;
	}
	/**
	 * 
	 * @param intDestacado
	 */
	public void setIntDestacado(Integer intDestacado) {
		this.intDestacado = intDestacado;
	}
	/**
	 * 
	 * @return
	 */
	public String getStrImagen() {
		return strImagen;
	}
	/**
	 * 
	 * @param strImagen
	 */
	public void setStrImagen(String strImagen) {
		this.strImagen = strImagen;
	}
	/**
	 * 
	 * @return
	 */
	public String getStrEstatus() {
		return strEstatus;
	}
	/**
	 * 
	 * @param strEstatus
	 */
	public void setStrEstatus(String strEstatus) {
		this.strEstatus = strEstatus;
	}
	/**
	 * 
	 * @return
	 */
	public String getStrDetalle() {
		return strDetalle;
	}
	/**
	 * 
	 * @param strDetalle
	 */
	public void setStrDetalle(String strDetalle) {
		this.strDetalle = strDetalle;
	}
	/**
	 * 
	 * @return
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	/**
	 * 
	 * @param categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void resetImage() {
		this.strImagen = null;
	}
	@Override
	public String toString() {
		return "Vacante [intId=" + intId + ", strNombre=" + strNombre + ", strDescripcion=" + strDescripcion
				+ ", fFecha=" + fFecha + ", dSalario=" + dSalario + ", intDestacado=" + intDestacado + ", strImagen="
				+ strImagen + ", strEstatus=" + strEstatus + ", strDetalle=" + strDetalle + ", categoria=" + categoria
				+ "]";
	}
	
	
}
