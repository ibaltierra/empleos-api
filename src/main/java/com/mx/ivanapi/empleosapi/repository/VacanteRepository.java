package com.mx.ivanapi.empleosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.model.Vacante;


/**
 * Repositorio para la clase vacante.
 * @author Ivan Manuel Baltierra Muñoz.
 *
 */
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {
	
	/**
	 * Método que realiza la busqueda de vacantes por estatus.
	 * @param strEstatus
	 * @return
	 */
	List<Vacante> findByStrEstatus(final String strEstatus);
	/**
	 * Método que realizar la busqueda por categoria.
	 * @param categoria
	 * @return
	 */
	List<Vacante> findByCategoria(final Categoria categoria);	
	
	List<Vacante> findByIntDestacadoAndStrEstatusOrderByIntIdDesc(final Integer descatado, final String estatus);
	
	List<Vacante> findBydSalarioBetween(final Double s1, final Double s2);
	
	List<Vacante> findByStrEstatusIn(String... estatus);	

}
