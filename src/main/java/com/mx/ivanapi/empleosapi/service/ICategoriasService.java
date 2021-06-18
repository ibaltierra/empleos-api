package com.mx.ivanapi.empleosapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.ivanapi.empleosapi.model.Categoria;


public interface ICategoriasService {

	public List<Categoria> buscarTodas();
	
	public Categoria buscarPorId(final Integer intId);
	
	public boolean guardar(final Categoria categoria);
	/**
	 * Mètodo que elimina una categoria.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId);
	/**
	 * Método que busca las categorias paginadas.
	 * @param pageable
	 * @return
	 */
	public Page<Categoria> buscarTodasPage(final Pageable pageable);
}
