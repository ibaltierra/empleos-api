package com.mx.ivanapi.empleosapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	public Integer getIntIdPerfil() {
		return intIdPerfil;
	}
	public void setIntIdPerfil(Integer intIdPerfil) {
		this.intIdPerfil = intIdPerfil;
	}
	public String getStrPerfil() {
		return strPerfil;
	}
	public void setStrPerfil(String strPerfil) {
		this.strPerfil = strPerfil;
	}
	@Override
	public String toString() {
		return "Perfil [intIdPerfil=" + intIdPerfil + ", strPerfil=" + strPerfil + "]";
	}
	
}
