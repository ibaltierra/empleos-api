package com.mx.ivanapi.empleosapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="CATEGORIAS")
@Entity
public class Categoria implements Serializable{
	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEC_ID_CATEGORIA", sequenceName="SEC_ID_CATEGORIA",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_ID_CATEGORIA") 
	private Integer intId;
	@Column(name="NOMBRE")
	private String strNombre;
	@Column(name="DESCRIPCION")
	private String strDescripcion;/*
	@OneToMany(targetEntity = Vacante.class)
	private List<Vacante> lstVacantes;*/
	public Integer getIntId() {
		return intId;
	}
	public void setIntId(Integer intId) {
		this.intId = intId;
	}
	public String getStrNombre() {
		return strNombre;
	}
	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}
	public String getStrDescripcion() {
		return strDescripcion;
	}
	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}
	@Override
	public String toString() {
		return "Categoria [intId=" + intId + ", strNombre=" + strNombre + ", strDescripcion=" + strDescripcion + "]";
	}
	
}
