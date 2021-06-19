package com.mx.ivanapi.empleosapi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.ivanapi.empleosapi.model.Vacante;
import com.mx.ivanapi.empleosapi.repository.VacanteRepository;

import java.util.Optional;

@Service
public class VacantesServiceImpl implements IVacantesService{
	private List<Vacante> lstVacantes = null;
	@Autowired
	private VacanteRepository vacanteRepository;
	@Autowired
	protected RestTemplate restTemplate;
	/**
	 * Constructor por default.
	 */
	public VacantesServiceImpl() {		
	}
	
	/**
	 * Método que realiza la busqueda de todas las vacantes.
	 * @return
	 */
	@Override
	public List<Vacante> buscarTodas(){
		lstVacantes = vacanteRepository.findAll();
		return lstVacantes;
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
	public List<Vacante>buscarDestacadas(){
		lstVacantes = vacanteRepository.findByIntDestacadoAndStrEstatusOrderByIntIdDesc(1, "Aprobada");
		return lstVacantes;
	}
	
	/**
	 * Método que realiza la busqueda de vacantes por id.
	 * @param vacante
	 * @return
	 */
	@Override
	public Vacante buscarPorId(final int intId) {
		Optional< Vacante> tmp = this.vacanteRepository.findById(intId);
		if(tmp.isPresent()) {
			return tmp.get();
		}
		return null;
		
		//return this.lstVacantes.stream().filter(vacante -> vacante.getIntId().intValue() == intId).collect(Collectors.toList()).get(0);
	}
	/**
	 * Método que guarda una vacante.
	 * @param vacante
	 * @return
	 */
	public boolean guardar(final Vacante vacante) {
		try {
			this.vacanteRepository.save(vacante);		
		}catch(final NullPointerException ex) {
			ex.printStackTrace();
			return Boolean.FALSE;
		}catch(final Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
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
