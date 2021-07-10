package com.mx.ivanapi.empleosapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ivanapi.empleosapi.response.ExceptionGeneria;
import com.mx.ivanapi.empleosapi.response.Response;
import com.mx.ivanapi.empleosapi.controller.to.VacanteTO;
import com.mx.ivanapi.empleosapi.model.Vacante;
import com.mx.ivanapi.empleosapi.service.IVacantesService;

@RestController
@RequestMapping("/apiVacantes")
public class VacantesApiController {
	
	@Autowired
	private IVacantesService vacanteRepository;
	private List<VacanteTO> lstVacantes;
	@GetMapping("/buscarTodas")
	public List<VacanteTO> buscarTodas(){
		lstVacantes = vacanteRepository.buscarTodas();
		return lstVacantes;
	}
	/**
	 * Método que realiza la busqeuda de todos paginado.
	 * @param page
	 * @return
	 */
	@GetMapping("/buscarTodasPagina")
	public Page<Vacante> buscarTodasPaginado(final Pageable page) {
		return vacanteRepository.buscarTodas(page);
	}

	/**
	 * Mètodo que busca todas las vacantes destacadas.
	 * @return
	 */
	@GetMapping("/buscarDestacadas")
	public List<VacanteTO>buscarDestacadas(){
		lstVacantes = vacanteRepository.buscarDestacadas();
		return lstVacantes;
	}
	
	/**
	 * Método que realiza la busqueda de vacantes por id.
	 * @param vacante
	 * @return
	 */
	@GetMapping("/buscarPorId/{id}")
	public VacanteTO buscarPorId(@PathVariable("id") final int intId) {
		return this.vacanteRepository.buscarPorId(intId);			
	}
	/**
	 * Método que guarda una vacante.
	 * @param vacante
	 * @return
	 */
	@PostMapping(value = "/guardar", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> guardar(@RequestBody final VacanteTO vacante) {	
		this.vacanteRepository.guardar(vacante);	
		Response result = new Response(vacante.getIntId(), vacante.getStrNombre());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@PutMapping(value = "/modificar/{id}")
	public ResponseEntity<Response> modificar(@PathVariable("id") final Integer intId, @RequestBody final VacanteTO vacante) throws ExceptionGeneria {		
		if(this.vacanteRepository.buscarPorId(intId) == null) {
			throw new ExceptionGeneria("No se encuentra el id!!");
		}
		vacante.setIntId(intId);
		this.vacanteRepository.guardar(vacante);	
		final Response result = new Response(vacante.getIntId(), vacante.getStrNombre());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	/**
	 * Mètodo que elimina una vacante.
	 * @throws ExceptionGeneria 
	 */
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") final Integer intIdVacante) throws ExceptionGeneria {
		if(this.vacanteRepository.buscarPorId(intIdVacante) == null) {			
			throw new ExceptionGeneria("No se encuentra el id!!");
		}
		this.vacanteRepository.eliminar(intIdVacante);
	}
	@PostMapping(value="/buscarByExample")
	public List<Vacante> buscarByExample(@RequestBody final Vacante example){
		/**
		 *Con esto se cambia el igual a contains(like) en la consulta que se genera al realizar la busqueda. 
		 */
		final ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("strDescripcion"
				, ExampleMatcher.GenericPropertyMatchers.contains());
		return vacanteRepository.buscarByExample(Example.of(example, matcher));
	}

}
