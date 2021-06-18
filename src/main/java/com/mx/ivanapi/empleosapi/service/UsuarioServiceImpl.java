package com.mx.ivanapi.empleosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.repository.UsuarioRepository;


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
	public boolean guardar(final Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
			return Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	/**
	 * Mètodo que realiza la busqueda de todos los usuarios.
	 * @return
	 */
	public List<Usuario> buscarTodo() {
		return usuarioRepository.findAll();
	}
	/**
	 * Mètodo que realiza la busqueda de un usuario por su id.
	 * @param intId
	 * @return
	 */
	public Usuario buscarPorId(final Integer intId) {
		final Optional< Usuario> tmp = usuarioRepository.findById(intId);
		if(tmp.isPresent()) {
			return tmp.get();
		}
		return null;		
	}
	/**
	 * Mètodo que elimina un usuario.
	 * @param intId
	 * @return
	 */
	public boolean eliminar(final Integer intId) {
		try {
			final Usuario tmp = new Usuario();
			tmp.setIntIdUsuario(intId);
			if(!this.solicitudesService.obtenerSolicitudPorUsuario(tmp).isEmpty()) {
				return Boolean.FALSE;
			}
			usuarioRepository.deleteById(intId);
			return Boolean.TRUE;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Boolean.FALSE;
		}
	}
	/**
	 * Método que busca un usuario por su user name.
	 * @param strUserName
	 * @return
	 */
	public Usuario buscarPorUserName(final String strUserName) {
		return this.usuarioRepository.findByStrUserName(strUserName);
	}
	
}
