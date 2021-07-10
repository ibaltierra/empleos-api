package com.mx.ivanapi.empleosapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.ivanapi.empleosapi.controller.to.CategoriaTO;
import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.response.ExceptionGeneria;


public interface ICategoriasService {

	public List<CategoriaTO> buscarTodas();
	
	public CategoriaTO buscarPorId(final Integer intId);
	
	public boolean guardar(final CategoriaTO categoria);
	/**
	 * Mètodo que elimina una categoria.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId)throws ExceptionGeneria;
	/**
	 * Método que busca las categorias paginadas.
	 * @param pageable
	 * @return
	 */
	public Page<Categoria> buscarTodasPage(final Pageable pageable);
}
