package com.mx.ivanapi.empleosapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ivanapi.empleosapi.controller.to.UsuarioTO;
import com.mx.ivanapi.empleosapi.response.ExceptionGeneria;
import com.mx.ivanapi.empleosapi.response.Response;
import com.mx.ivanapi.empleosapi.service.IUsuarioService;

@RestController
@RequestMapping("/apiUsuario")
public class UsuarioApiController {

	@Autowired
	private IUsuarioService service;	
	/**
	 * Mètodo que guarda un usuario.
	 * @param usuario
	 * @return
	 */
	@PostMapping(value="/guardar")
	public ResponseEntity<Response> guardar(@RequestBody final UsuarioTO usuario) {
		service.guardar(usuario);
		final Response resultado = new Response(usuario.getIntIdUsuario(), usuario.getStrUserName());
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	/**
	 * Mètodo que realiza la busqueda de todos los usuarios.
	 * @return
	 */
	@GetMapping("/buscarTodo")
	public List<UsuarioTO> buscarTodo(){
		return this.service.buscarTodo();
	}
	/**
	 * Mètodo que realiza la busqueda de un usuario por su id.
	 * @param intId
	 * @return
	 */
	@GetMapping("/buscarPorId/{id}")
	public UsuarioTO buscarPorId(@PathVariable("id")final Integer intId) {
		return this.service.buscarPorId(intId);
	}
	/**
	 * Mètodo que elimina un usuario.
	 * @param intId
	 * @return
	 * @throws ExceptionGeneria 
	 */
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Response> eliminar(@PathVariable("id")final Integer intId) throws ExceptionGeneria {
		Response resultado = null;
		if(!this.service.eliminar(intId)) {
			throw new ExceptionGeneria("No se puede eliminar el registro!!");
		}
		resultado = new Response(intId, "Registro eliminado");
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	/**
	 * Método que busca un usuario por su user name.
	 * @param strUserName
	 * @return
	 */
	@GetMapping("/buscarPorUserName/{username}")
	public UsuarioTO buscarPorUserName(@PathVariable("username")final String strUserName) {
		return this.service.buscarPorUserName(strUserName);
	}
}
