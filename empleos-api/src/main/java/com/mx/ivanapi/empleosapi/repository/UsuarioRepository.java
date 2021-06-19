package com.mx.ivanapi.empleosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ivanapi.empleosapi.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	/**
	 * Método que busca por user name.
	 * @param strUserName
	 * @return
	 */
	public Usuario findByStrUserName(final String strUserName);
}
