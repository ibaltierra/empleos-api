package com.mx.ivanapi.empleosapi.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.ivanapi.empleosapi.model.Vacante;


public interface IVacantesService {
	/**
	 * Método que realiza la busqueda de todas las vacantes.
	 * @return
	 */
	public List<Vacante> buscarTodas();
	/**
	 * Método que realiza la busqeuda de todos paginado.
	 * @param page
	 * @return
	 */
	public Page<Vacante> buscarTodas(final Pageable page);
	/**
	 * Método que realiza la busqueda de vacantes por id.
	 * @param vacante
	 * @return
	 */
	Vacante buscarPorId(int intId);
	/**
	 * Método que guarda una vacante.
	 * @param vacante
	 * @return
	 */
	public boolean guardar(final Vacante vacante);
	/**
	 * Mètodo que busca todas las vacantes destacadas.
	 * @return
	 */
	public List<Vacante>buscarDestacadas();
	
	public void eliminar(final Integer intIdVacante);
	
	public List<Vacante> buscarByExample(final Example<Vacante> example);
	
}
