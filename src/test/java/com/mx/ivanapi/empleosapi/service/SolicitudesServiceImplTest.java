package com.mx.ivanapi.empleosapi.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.repository.SolicitudesRepository;

import antlr.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
public class SolicitudesServiceImplTest {

	@MockBean
	private SolicitudesRepository repository;
	@InjectMocks
	private SolicitudesServiceImpl solicitud;
	private final static Solicitud SOLICITUD_TEST = new Solicitud();
	private final static Optional<Solicitud> SOLICITUD_OPTIONAL = Optional.of(SOLICITUD_TEST);
	private final static int ID = 1;
	private final static List<Solicitud> LST_SOLICITUDES_TEST = new ArrayList<>();
	@Mock
    private Pageable pageableMock;
	@Mock
    private Page<Solicitud> solicitudPage;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		LST_SOLICITUDES_TEST.add(this.SOLICITUD_TEST);
	}
	@Test
	public void buscarTodasTest() {
		when(repository.findAll(Sort.by("intIdSolicitudes"))).thenReturn(new ArrayList<>());
		final List<Solicitud> lstSolicitudes = this.solicitud.buscarTodas();
		assertEquals(lstSolicitudes.size(), 0);
		assertNotNull(lstSolicitudes);
		assertTrue(lstSolicitudes.isEmpty());
	}
	@Test
	public void buscarPorIdTest() {
		SOLICITUD_TEST.setIntIdSolicitudes(ID);
		when(this.repository.findById(Mockito.anyInt())).thenReturn(SOLICITUD_OPTIONAL);
		final Solicitud resultado = this.solicitud.buscarPorId(ID);
		assertEquals(resultado.getIntIdSolicitudes(), ID);
		assertNotNull(resultado);
	}
	@Test
	public void buscarPorIdNullTest() {
		when(this.repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		final Solicitud resultado = this.solicitud.buscarPorId(ID);
		assertNull(resultado);	
	}
	@Test
	public void guardarTest() {
		final boolean blnResultado = this.solicitud.guardar(SOLICITUD_TEST);
		assertEquals(blnResultado, Boolean.TRUE);
	}
	@Test
	public void eliminarTest(){
		final boolean blnResultado = this.solicitud.eliminar(Mockito.anyInt());
		assertEquals(blnResultado, Boolean.TRUE);
	}
	@Test
	public void obtenerSolicitudPorUsuarioTest() {
		when(this.repository.findByUsuario(Mockito.any())).thenReturn(LST_SOLICITUDES_TEST);
		final List<Solicitud> lstResultado = this.solicitud.obtenerSolicitudPorUsuario(new Usuario());
		assertEquals(lstResultado.size(), 1);
		assertNotNull(lstResultado);
		assertFalse(lstResultado.isEmpty());
	}
	@Test
	public void buscarTodasPaginadoTest() {
		Pageable pageable = PageRequest.of(1, 8);
		when(repository.findAll( pageableMock)).thenReturn(solicitudPage);
		final Page<Solicitud> page= this.solicitud.buscarTodas(pageable);
		System.out.println(page == null? "es null" : "no es null");
		assertNull(page);
	}
}
