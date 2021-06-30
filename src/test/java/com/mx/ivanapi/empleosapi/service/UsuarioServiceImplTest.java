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

import com.mx.ivanapi.empleosapi.model.Solicitud;
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
	private final static Usuario USUARIO_TEST = new Usuario();
	private final static Solicitud SOLICITUD_TEST = new Solicitud();
	private final static Optional<Usuario> USUARIO_OPTIONAL = Optional.of(USUARIO_TEST);
	private final static int ID=1;
	private final static List<Solicitud> LST_SOLICITUDES = new ArrayList<Solicitud>();
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void guardarTest() {
		final boolean blnResultado = this.service.guardar(new Usuario());
		assertTrue(blnResultado);
	}
	@Test
	public void buscarTodoTest() {
		when(usuarioRepository.findAll()).thenReturn(new ArrayList<>());
		final List<Usuario> lstUsuarios = this.service.buscarTodo();
		assertNotNull(lstUsuarios);
		assertEquals(lstUsuarios.size(), 0);
		assertTrue(lstUsuarios.isEmpty());
	}
	@Test
	public void buscarPorIdTest() {
		this.USUARIO_TEST.setIntIdUsuario(ID);
		when(usuarioRepository.findById(ID)).thenReturn(USUARIO_OPTIONAL);
		final Usuario resultado = this.service.buscarPorId(ID);
		assertNotNull(resultado);
		assertEquals(resultado.getIntIdUsuario(), ID);
	}
	@Test
	public void buscarPorIdNullTest() {
		when(usuarioRepository.findById(ID)).thenReturn(Optional.empty());
		final Usuario resultado = this.service.buscarPorId(ID);
		assertNull(resultado);
	}
	@Test
	public void eliminarTest() {
		when(this.solicitudesService.obtenerSolicitudPorUsuario(this.USUARIO_TEST)).thenReturn(new ArrayList<Solicitud>());
		final boolean resultado = this.service.eliminar(ID);
		assertTrue(resultado);
	}
	/*@Test
	public void eliminarFalseTest() {
		LST_SOLICITUDES.add(SOLICITUD_TEST);
		USUARIO_TEST.setIntIdUsuario(ID);
		when(this.solicitudesService.obtenerSolicitudPorUsuario(this.USUARIO_TEST)).thenReturn(LST_SOLICITUDES);
		final boolean resultado = this.service.eliminar(ID);
		assertFalse(resultado);
	}*/
	@Test
	public void buscarPorUserNameTest() {
		USUARIO_TEST.setStrNombre("Ivan");
		when( this.usuarioRepository.findByStrUserName(Mockito.anyString())).thenReturn(USUARIO_TEST);
		final Usuario resultado = this.service.buscarPorUserName(Mockito.anyString());
		assertNotNull(resultado);
		assertEquals(resultado.getStrNombre(), "Ivan");
	}
}
