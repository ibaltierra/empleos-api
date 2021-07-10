package com.mx.ivanapi.empleosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.controller.to.CategoriaTO;
import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.repository.CategoriasRepository;
import com.mx.ivanapi.empleosapi.response.ExceptionGeneria;
import com.mx.ivanapi.empleosapi.service.mapper.MapperEmpleosApi;

import lombok.extern.slf4j.Slf4j;


//@Primary indica cual es el bean principal de las implementaciones
@Slf4j
@Service
public class CategoriasServiceImpl implements ICategoriasService{
	
	@Autowired
	private CategoriasRepository categoriasRepository;

	/**
	 * Método que regresa todas las categorias.
	 */
	public List<CategoriaTO> buscarTodas(){
		return MapperEmpleosApi.mapList(categoriasRepository.findAll(Sort.by(Constant.LABEL_MODEL_INT_ID)), CategoriaTO.class);
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
	public CategoriaTO buscarPorId(final Integer intId){
		final Optional<Categoria> catPorId=this.categoriasRepository.findById(intId);
		if(catPorId.isPresent()) {
			return MapperEmpleosApi.convertCategoriaEntityTO( catPorId.get() );
		}
		return null;
	}
	/**
	 * Método que guarda una categoria.
	 */
	public boolean guardar(final CategoriaTO categoria) {
		try {
			this.categoriasRepository.save(MapperEmpleosApi.convertCategoriaToEntity( categoria) );
			return Boolean.TRUE;
		}catch(final Exception e) {
			log.error(e.getMessage());
			return Boolean.FALSE;
		}
	}
	/**
	 * Mètodo que elimina una categoria.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId) throws ExceptionGeneria{
		try {
			this.categoriasRepository.deleteById(intId);
			return Boolean.TRUE;
		} catch (final Exception e) {
			log.error(e.getMessage());
			throw new ExceptionGeneria(e.getMessage());
		}
	}
	
}
