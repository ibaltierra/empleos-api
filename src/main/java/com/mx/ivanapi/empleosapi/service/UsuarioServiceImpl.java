package com.mx.ivanapi.empleosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ivanapi.empleosapi.controller.to.UsuarioTO;
import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.repository.UsuarioRepository;
import com.mx.ivanapi.empleosapi.service.mapper.MapperEmpleosApi;



/**
 * 
 * @author Ivan Manuel Baltierra Muñoz
 * Clase que realiza el negocio del registro, modificaciòn y eliminaciòn de los usuarios.
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ISolicitudesService solicitudesService;
	/**
	 * Mètodo que guarda un usuario.
	 * @param usuario
	 * @return
	 */
	public boolean guardar(final UsuarioTO usuario) {		
			usuarioRepository.save(MapperEmpleosApi.convertUserToEntity(usuario) );
			return Boolean.TRUE;
			
	}
	/**
	 * Mètodo que realiza la busqueda de todos los usuarios.
	 * @return
	 */
	public List<UsuarioTO> buscarTodo() {
		return MapperEmpleosApi.mapList(usuarioRepository.findAll(), UsuarioTO.class);
	}
	/**
	 * Mètodo que realiza la busqueda de un usuario por su id.
	 * @param intId
	 * @return
	 */
	public UsuarioTO buscarPorId(final Integer intId) {
		final Optional< Usuario> tmp = usuarioRepository.findById(intId);
		if(tmp.isPresent()) {
			return MapperEmpleosApi.convertUserEntityTO(tmp.get() );
		}
		return null;		
	}
	/**
	 * Mètodo que elimina un usuario.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId) {		
			final UsuarioTO tmp = new UsuarioTO();
			tmp.setIntIdUsuario(intId);
			if(this.solicitudesService.obtenerSolicitudPorUsuario(tmp).isEmpty()) {				
				usuarioRepository.deleteById(intId);
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		
	}
	/**
	 * Método que busca un usuario por su user name.
	 * @param strUserName
	 * @return
	 */
	public UsuarioTO buscarPorUserName(final String strUserName) {
		return MapperEmpleosApi.convertUserEntityTO(this.usuarioRepository.findByStrUserName(strUserName) );
	}
}
