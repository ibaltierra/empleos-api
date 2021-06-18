package com.mx.ivanapi.empleosapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="USUARIOS")
public class Usuario {
	@Id
	@Column(name="ID_USUARIO")
	@SequenceGenerator(name="SEC_ID_USUARIO", sequenceName="SEC_ID_USUARIO",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_ID_USUARIO") 
	private Integer intIdUsuario;
	@Column(name="NOMBRE")
	private String strNombre;
	@Column(name="EMAIL")
	private String strEmail;
	@Column(name="USER_NAME")
	private String strUserName;
	@Column(name="PASSWORD")
	private String strPassword;
	@Column(name="ESTATUS")
	private Integer intEstatus;
	@Column(name="FECHA_REGISTRO")
	private Date fFechaRegistro;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="USUARIO_PERFIL",
		joinColumns = @JoinColumn(name="ID_USUARIO"),
		inverseJoinColumns = @JoinColumn(name="ID_PERFIL")
			)
	private List<Perfil> perfiles;
	public void agregar(final Perfil perfil) {
		if(perfiles == null) {
			perfiles = new ArrayList<Perfil>();
		}
		perfiles.add(perfil);
	}
	public Integer getIntIdUsuario() {
		return intIdUsuario;
	}
	public void setIntIdUsuario(Integer intIdUsuario) {
		this.intIdUsuario = intIdUsuario;
	}
	public String getStrNombre() {
		return strNombre;
	}
	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}
	public String getStrEmail() {
		return strEmail;
	}
	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	public String getStrUserName() {
		return strUserName;
	}
	public void setStrUserName(String strUserName) {
		this.strUserName = strUserName;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public Integer getIntEstatus() {
		return intEstatus;
	}
	public void setIntEstatus(Integer intEstatus) {
		this.intEstatus = intEstatus;
	}
	public Date getfFechaRegistro() {
		return fFechaRegistro;
	}
	public void setfFechaRegistro(Date fFechaRegistro) {
		this.fFechaRegistro = fFechaRegistro;
	}
	public List<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	@Override
	public String toString() {
		return "Usuario [intIdUsuario=" + intIdUsuario + ", strNombre=" + strNombre + ", strEmail=" + strEmail
				+ ", strUserName=" + strUserName + ", strPassword=" + strPassword + ", intEstatus=" + intEstatus
				+ ", fFechaRegistro=" + fFechaRegistro + ", perfiles=" + perfiles + "]";
	}
	

}
