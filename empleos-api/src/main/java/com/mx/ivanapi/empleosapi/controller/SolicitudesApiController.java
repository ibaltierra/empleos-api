package com.mx.ivanapi.empleosapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.response.Response;
import com.mx.ivanapi.empleosapi.service.ISolicitudesService;

@RestControllerAdvice
@RequestMapping("/apiSolicitudes")
public class SolicitudesApiController {

	@Autowired
	private ISolicitudesService service;
	/**
	 *Método que busca todas las solicitudes. 
	 * @return
	 */
	@GetMapping(value="/buscarTodas")
	public List<Solicitud> buscarTodas(){
		return service.buscarTodas();				
	}
	/**
	 *Método que busca todas las solicitudes por paginas. 
	 * @return
	 */
	@GetMapping(value="/buscarTodasPaginado")
	public Page<Solicitud> buscarTodasPaginado(final Pageable pageable){
		return service.buscarTodas(pageable);
	}
	/**
	 * Método que busca una solicitud por su id.
	 * @return
	 */
	@GetMapping(value="/buscarPorId/{id}")
	public Solicitud buscarPorId(@PathVariable("id") final Integer intId) {
		return this.service.buscarPorId(intId);
	}
	/**
	 * Método que guarda una solicitud.
	 * @param solicitud
	 * @return
	 */
	@PostMapping(value="/guardar")
	public ResponseEntity<Response> guardar(@RequestBody final Solicitud solicitud) {
		this.service.guardar(solicitud);
		Response resultado = new Response(solicitud.getVacante().getIntId(), solicitud.getStrComentarios());
		return new ResponseEntity<Response>(resultado, HttpStatus.OK);
	}
	/**
	 * Método que elimina una solicitud por su id.
	 * @param intId
	 * @return
	 */
	@DeleteMapping(value="/eliminar/{id}")
	public ResponseEntity<Response> eliminar(@PathVariable("id") final Integer intId) {
		this.service.eliminar(intId);
		final Response resultado = new Response(intId, "¡Registro eliminado!");
		return new ResponseEntity<Response>(resultado, HttpStatus.OK);
	}
}
