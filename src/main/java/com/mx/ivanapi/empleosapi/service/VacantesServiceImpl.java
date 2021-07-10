package com.mx.ivanapi.empleosapi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.ivanapi.empleosapi.controller.to.VacanteTO;
import com.mx.ivanapi.empleosapi.model.Vacante;
import com.mx.ivanapi.empleosapi.repository.VacanteRepository;
import com.mx.ivanapi.empleosapi.service.mapper.MapperEmpleosApi;

import java.util.Optional;

@Service
public class VacantesServiceImpl implements IVacantesService{
	@Autowired
	private VacanteRepository vacanteRepository;
	@Autowired
	protected RestTemplate restTemplate;
	
	
	/**
	 * Método que realiza la busqueda de todas las vacantes.
	 * @return
	 */
	@Override
	public List<VacanteTO> buscarTodas(){
		return MapperEmpleosApi.mapList( vacanteRepository.findAll() , VacanteTO.class);
	}
	/**
	 * Método que realiza la busqeuda de todos paginado.
	 * @param page
	 * @return
	 */
	@Override
	public Page<Vacante> buscarTodas(final Pageable page) {
		return vacanteRepository.findAll(page);
	}

	/**
	 * Mètodo que busca todas las vacantes destacadas.
	 * @return
	 */
	public List<VacanteTO>buscarDestacadas(){
		return MapperEmpleosApi.mapList( vacanteRepository.findByIntDestacadoAndStrEstatusOrderByIntIdDesc(1, "Aprobada"),
				VacanteTO.class);
	}
	
	/**
	 * Método que realiza la busqueda de vacantes por id.
	 * @param vacante
	 * @return
	 * 		//return this.lstVacantes.stream().filter(vacante -> vacante.getIntId().intValue() == intId).collect(Collectors.toList()).get(0);
	 */
	@Override
	public VacanteTO buscarPorId(final int intId) {
		final Optional<Vacante> tmp = this.vacanteRepository.findById(intId);
		if(tmp.isPresent()) {			
			return MapperEmpleosApi.convertVacanteTOVacanteTO(tmp.get());
		}
		return null;
		
	}
	/**
	 * Método que guarda una vacante.
	 * @param vacante
	 * @return
	 */
	public boolean guardar(final VacanteTO vacante) {
		
		this.vacanteRepository.save(MapperEmpleosApi.convertVacanteTOTOVacante( vacante ) );		
		
		return Boolean.TRUE;
	}
	/**
	 * Mètodo que elimina una vacante.
	 */
	public void eliminar(final Integer intIdVacante) {
		this.vacanteRepository.deleteById(intIdVacante);
	}

	public List<Vacante> buscarByExample(final Example<Vacante> example){
		return vacanteRepository.findAll(example);
	}
}
