package com.mx.ivanapi.empleosapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Usuario;


public interface ISolicitudesService {
	/**
	 *Método que busca todas las solicitudes. 
	 * @return
	 */
	public List<Solicitud> buscarTodas();
	/**
	 *Método que busca todas las solicitudes por paginas. 
	 * @return
	 */
	public Page<Solicitud> buscarTodas(final Pageable pageable);
	/**
	 * Método que busca una solicitud por su id.
	 * @return
	 */
	public Solicitud buscarPorId(final Integer intId);
	/**
	 * Método que guarda una solicitud.
	 * @param solicitud
	 * @return
	 */
	public boolean guardar(final Solicitud solicitud);
	/**
	 * Método que elimina una solicitud por su id.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId);
	public List<Solicitud> obtenerSolicitudPorUsuario(final Usuario usuario);
}
