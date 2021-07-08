package com.mx.ivanapi.empleosapi.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.repository.SolicitudesRepository;


@Service
public class SolicitudesServiceImpl implements ISolicitudesService, Serializable {

	@Autowired
	private SolicitudesRepository repository;
	/**
	 *Método que busca todas las solicitudes. 
	 * @return
	 */
	public List<Solicitud> buscarTodas(){
		return repository.findAll(Sort.by(Constant.LABEL_MODEL_ID_SOLICITUDES));
	}
	/**
	 *Método que busca todas las solicitudes por paginas. 
	 * @return
	 */
	public Page<Solicitud> buscarTodas(final Pageable pageable){
		return repository.findAll(pageable);
	}
	/**
	 * Método que busca una solicitud por su id.
	 * @return
	 */
	public Solicitud buscarPorId(final Integer intId) {
		Optional<Solicitud> tmp = this.repository.findById(intId);
		if(tmp.isPresent()) {
			return tmp.get();
		}
		return null;		
	}
	/**
	 * Método que guarda una solicitud.
	 * @param solicitud
	 * @return
	 */
	public boolean guardar(final Solicitud solicitud) {
		this.repository.save(solicitud);
		return Boolean.TRUE;
	}
	/**
	 * Método que elimina una solicitud por su id.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId) {
		this.repository.deleteById(intId);
		return Boolean.TRUE;
	}
	public List<Solicitud> obtenerSolicitudPorUsuario(final Usuario usuario){
		return this.repository.findByUsuario(usuario);
	}
}
