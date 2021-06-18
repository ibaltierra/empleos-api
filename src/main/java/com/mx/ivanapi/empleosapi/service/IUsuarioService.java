package com.mx.ivanapi.empleosapi.service;

import java.util.List;

import com.mx.ivanapi.empleosapi.model.Usuario;



public interface IUsuarioService {

	/**
	 * Mètodo que guarda un usuario.
	 * @param usuario
	 * @return
	 */
	public boolean guardar(final Usuario usuario);
	/**
	 * Mètodo que realiza la busqueda de todos los usuarios.
	 * @return
	 */
	public List<Usuario> buscarTodo();
	/**
	 * Mètodo que realiza la busqueda de un usuario por su id.
	 * @param intId
	 * @return
	 */
	public Usuario buscarPorId(final Integer intId);
	/**
	 * Mètodo que elimina un usuario.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId);
	/**
	 * Método que busca un usuario por su user name.
	 * @param strUserName
	 * @return
	 */
	public Usuario buscarPorUserName(final String strUserName);
	
}
