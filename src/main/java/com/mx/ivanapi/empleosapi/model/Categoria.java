package com.mx.ivanapi.empleosapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
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
	@Override
	public String toString() {
		return "Categoria [intId=" + intId + ", strNombre=" + strNombre + ", strDescripcion=" + strDescripcion + "]";
	}
	
}
