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
import org.springframework.web.bind.annotation.RestController;

import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.response.Response;
import com.mx.ivanapi.empleosapi.service.ICategoriasService;
@RequestMapping("/apiCategoria")
@RestController
public class CategoriaApiController {

	@Autowired
	private ICategoriasService categoriasService;
	@GetMapping(value="/buscarTodas")
	public List<Categoria> buscarTodas(){
		return categoriasService.buscarTodas();
	}
	@GetMapping(value="/buscarPorId/{id}")
	public Categoria buscarPorId(@PathVariable("id")final Integer intId) {
		return categoriasService.buscarPorId(intId);
	}
	@PostMapping("/guardar")
	public ResponseEntity<Response> guardar(@RequestBody final Categoria categoria) {
		categoriasService.guardar(categoria);
		Response result = new Response(categoria.getIntId(), categoria.getStrNombre());		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	/**
	 * Mètodo que elimina una categoria.
	 * @param intId
	 * @return
	 */
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Response> eliminar(@PathVariable("id") final Integer intId) {
		final Categoria cat = this.buscarPorId(intId);
		this.categoriasService.eliminar(intId);
		final Response result = new Response(cat.getIntId(), cat.getStrNombre());		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	/**
	 * Método que busca las categorias paginadas.
	 * @param pageable
	 * @return
	 */
	@GetMapping("/buscarTodasPage")
	public Page<Categoria> buscarTodasPage(final Pageable pageable){
		return this.categoriasService.buscarTodasPage(pageable);
	}
	
	
}
