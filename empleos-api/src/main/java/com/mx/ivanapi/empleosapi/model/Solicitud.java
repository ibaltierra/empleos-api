package com.mx.ivanapi.empleosapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SOLICITUDES")
public class Solicitud {
	
	@Id
	@Column(name = "ID_SOLICITUD")
	@SequenceGenerator(name="SEC_ID_SOLICITUD", sequenceName="SEC_ID_SOLICITUD",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEC_ID_SOLICITUD") 
	private Integer intIdSolicitudes;
	@Column(name="FECHA")
	private Date fFecha;
	@Column(name ="ARCHIVO")
	private String strArchivo;
	@Column(name="COMENTARIOS")
	private String strComentarios;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "ID_VACANTE", referencedColumnName = "ID_VACANTE")
	})
	private Vacante vacante;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	})
	private Usuario usuario;
	public Integer getIntIdSolicitudes() {
		return intIdSolicitudes;
	}
	public void setIntIdSolicitudes(Integer intIdSolicitudes) {
		this.intIdSolicitudes = intIdSolicitudes;
	}
	public Date getfFecha() {
		return fFecha;
	}
	public void setfFecha(Date fFecha) {
		this.fFecha = fFecha;
	}
	public String getStrArchivo() {
		return strArchivo;
	}
	public void setStrArchivo(String strArchivo) {
		this.strArchivo = strArchivo;
	}
	public String getStrComentarios() {
		return strComentarios;
	}
	public void setStrComentarios(String strComentarios) {
		this.strComentarios = strComentarios;
	}
	public Vacante getVacante() {
		return vacante;
	}
	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Solicitud [intIdSolicitudes=" + intIdSolicitudes + ", fFecha=" + fFecha + ", strArchivo=" + strArchivo
				+ ", strComentarios=" + strComentarios + ", vacante=" + vacante + ", usuario=" + usuario + "]";
	}
	
}
