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

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
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
			perfiles = new ArrayList<>();
		}
		perfiles.add(perfil);
	}
	@Override
	public String toString() {
		return "Usuario [intIdUsuario=" + intIdUsuario + ", strNombre=" + strNombre + ", strEmail=" + strEmail
				+ ", strUserName=" + strUserName + ", strPassword=" + strPassword + ", intEstatus=" + intEstatus
				+ ", fFechaRegistro=" + fFechaRegistro + ", perfiles=" + perfiles + "]";
	}
	

}
