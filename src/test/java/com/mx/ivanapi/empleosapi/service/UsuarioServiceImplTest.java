package com.mx.ivanapi.empleosapi.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.controller.to.SolicitudTO;
import com.mx.ivanapi.empleosapi.controller.to.UsuarioTO;
import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.repository.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioServiceImplTest {

	@InjectMocks
	private UsuarioServiceImpl service;
	@MockBean
	private UsuarioRepository usuarioRepository;
	@MockBean
	private ISolicitudesService solicitudesService;
	private static final Usuario USUARIO_TEST = new Usuario();
	private static final Optional<Usuario> USUARIO_OPTIONAL = Optional.of(USUARIO_TEST);
	private static final int ID=1;
	private static final String NAME_COMPARE ="Ivan";
	private static final List<SolicitudTO> LST_SOLICITUDES = new ArrayList<>();
	private static final SolicitudTO SOLICITUD_TEST = new SolicitudTO();
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void guardarTest() {
		when(this.usuarioRepository.save(Mockito.any(Usuario.class)) ).thenReturn(new Usuario());
		final boolean blnResultado = this.service.guardar(new UsuarioTO());
		assertTrue(blnResultado);
	}
	@Test
	public void buscarTodoTest() {
		when(usuarioRepository.findAll()).thenReturn(new ArrayList<>());
		final List<UsuarioTO> lstUsuarios = this.service.buscarTodo();
		assertNotNull(lstUsuarios);
		assertEquals(Constant.N000, lstUsuarios.size());
		assertTrue(lstUsuarios.isEmpty());
	}
	@Test
	public void buscarPorIdTest() {
		this.USUARIO_TEST.setIntIdUsuario(ID);
		when(usuarioRepository.findById(ID)).thenReturn(USUARIO_OPTIONAL);
		final UsuarioTO resultado = this.service.buscarPorId(ID);
		assertNotNull(resultado);
		assertEquals(ID, resultado.getIntIdUsuario());
	}
	@Test
	public void buscarPorIdNullTest() {
		when(usuarioRepository.findById(ID)).thenReturn(Optional.empty());
		final UsuarioTO resultado = this.service.buscarPorId(ID);
		assertNull(resultado);
	}
	@Test
	public void eliminarTest() {
		when(this.solicitudesService.obtenerSolicitudPorUsuario(Mockito.any(UsuarioTO.class) )).thenReturn(new ArrayList<SolicitudTO>());
		final boolean resultado = this.service.eliminar(ID);
		assertTrue(resultado);
	}
	
	@Test
	public void eliminarFalseTest() {
		LST_SOLICITUDES.add(SOLICITUD_TEST);
		Mockito.when(this.solicitudesService.obtenerSolicitudPorUsuario(Mockito.any(UsuarioTO.class)))
			.thenReturn(LST_SOLICITUDES);
		final boolean resultado = this.service.eliminar(ID);
		assertFalse(resultado);
	}
	@Test
	public void buscarPorUserNameTest() {
		USUARIO_TEST.setStrNombre("Ivan");
		when( this.usuarioRepository.findByStrUserName(Mockito.anyString())).thenReturn(USUARIO_TEST);
		final UsuarioTO resultado = this.service.buscarPorUserName(Mockito.anyString());
		assertNotNull(resultado);
		assertEquals(NAME_COMPARE, resultado.getStrNombre() );
	}
}
