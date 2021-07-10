package com.mx.ivanapi.empleosapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="PERFILES")
public class Perfil {

	@Id
	@Column(name="ID_PERFIL")
	@SequenceGenerator(name="SEC_ID_PERFIL", sequenceName="SEC_ID_PERFIL",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_ID_PERFIL") 
	private Integer intIdPerfil;
	@Column(name="PERFIL")
	private String strPerfil;
	
	@Override
	public String toString() {
		return "Perfil [intIdPerfil=" + intIdPerfil + ", strPerfil=" + strPerfil + "]";
	}
	
}
