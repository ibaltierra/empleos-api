package com.mx.ivanapi.empleosapi.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.repository.CategoriasRepository;


//@Primary indica cual es el bean principal de las implementaciones
@Service
public class CategoriasServiceImpl implements Serializable, ICategoriasService{

	private List<Categoria> lstCategorias;
	@Autowired
	private CategoriasRepository categoriasRepository;
	/**
	 * Constructor por default.
	 */
	public CategoriasServiceImpl() {		
	}
	/**
	 * Método que regresa todas las categorias.
	 */
	public List<Categoria> buscarTodas(){
		lstCategorias = categoriasRepository.findAll(Sort.by("intId"));
		return lstCategorias;
	}
	/**
	 * Método que busca las categorias paginadas.
	 * @param pageable
	 * @return
	 */
	public Page<Categoria> buscarTodasPage(final Pageable pageable){
		return categoriasRepository.findAll(pageable);
	}
	/**
	 * Método que realiza la busqueda de una categoria por su id.
	 * 		//return lstCategorias.stream().filter(cat-> cat.getIntId().intValue() == intId.intValue()).collect(Collectors.toList()).get(0);
	 */
	public Categoria buscarPorId(final Integer intId){
		final Optional<Categoria> catPorId=this.categoriasRepository.findById(intId);
		if(catPorId.isPresent()) {
			return catPorId.get();
		}
		return null;
	}
	/**
	 * Método que guarda una categoria.
	 */
	public boolean guardar(final Categoria categoria) {
		try {
			this.categoriasRepository.save(categoria);
			return Boolean.TRUE;
		}catch(final Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	/**
	 * Mètodo que elimina una categoria.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId) {
		try {
			this.categoriasRepository.deleteById(intId);
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	
}
