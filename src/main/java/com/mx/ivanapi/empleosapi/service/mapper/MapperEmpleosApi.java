package com.mx.ivanapi.empleosapi.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.mx.ivanapi.empleosapi.controller.to.CategoriaTO;
import com.mx.ivanapi.empleosapi.controller.to.SolicitudTO;
import com.mx.ivanapi.empleosapi.controller.to.UsuarioTO;
import com.mx.ivanapi.empleosapi.controller.to.VacanteTO;
import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.model.Vacante;

public class MapperEmpleosApi {

	private MapperEmpleosApi() {}
	private static final ModelMapper mapper = new ModelMapper();
	/**
	 * Method that convert of Entity to TO.
	 * @param vacante
	 * @return
	 */
	public static VacanteTO convertVacanteTOVacanteTO(final Vacante vacante) {
		return mapper.map(vacante, VacanteTO.class);
	}
	public static Vacante convertVacanteTOTOVacante(final VacanteTO convert) {
		return mapper.map(convert, Vacante.class);
	}
	/**
	 * Method that convert a list of Entity to list TO.
	 * @param <T>
	 * @param <S>
	 * @param vacante
	 * @return
	 */
	public static <S, T> List<T> mapList(final List<S> source, final Class<T> targetClass) {
		 return source
			      .stream()
			      .map(element -> mapper.map(element, targetClass))
			      .collect(Collectors.toList());
	}
	
	public static CategoriaTO convertCategoriaEntityTO(final Categoria categoria) {
		return mapper.map(categoria, CategoriaTO.class);
	}
	public static Categoria convertCategoriaToEntity(final CategoriaTO categoria) {
		return mapper.map(categoria, Categoria.class);
	}
	
	public static UsuarioTO convertUserEntityTO(final Usuario usuario) {
		return mapper.map(usuario, UsuarioTO.class);
	}
	public static Usuario convertUserToEntity(final UsuarioTO usuario) {
		return mapper.map(usuario, Usuario.class);
	}
	
	public static SolicitudTO convertSolicitudEntityTO(final Solicitud solicitud) {
		return mapper.map(solicitud, SolicitudTO.class);
	}
	public static Solicitud convertSolicitudToEntity(final SolicitudTO solicitud) {
		return mapper.map(solicitud, Solicitud.class);
	}
}
