package com.mx.ivanapi.empleosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.controller.to.SolicitudTO;
import com.mx.ivanapi.empleosapi.controller.to.UsuarioTO;
import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.repository.SolicitudesRepository;
import com.mx.ivanapi.empleosapi.service.mapper.MapperEmpleosApi;


@Service
public class SolicitudesServiceImpl implements ISolicitudesService {

	@Autowired
	private SolicitudesRepository repository;
	/**
	 *Método que busca todas las solicitudes. 
	 * @return
	 */
	public List<SolicitudTO> buscarTodas(){
		return MapperEmpleosApi.mapList( repository.findAll(Sort.by(Constant.LABEL_MODEL_ID_SOLICITUDES)), SolicitudTO.class);
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
	public SolicitudTO buscarPorId(final Integer intId) {
		final Optional<Solicitud> tmp = this.repository.findById(intId);
		if(tmp.isPresent()) {
			return MapperEmpleosApi.convertSolicitudEntityTO(tmp.get());
		}
		return null;		
	}
	/**
	 * Método que guarda una solicitud.
	 * @param solicitud
	 * @return
	 */
	public boolean guardar(final SolicitudTO solicitud) {
		this.repository.save(MapperEmpleosApi.convertSolicitudToEntity(solicitud) );
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
	public List<SolicitudTO> obtenerSolicitudPorUsuario(final UsuarioTO usuario){
		return MapperEmpleosApi.mapList(this.repository
				.findByUsuario(MapperEmpleosApi.convertUserToEntity(usuario) )
				, SolicitudTO.class);
	}
}
