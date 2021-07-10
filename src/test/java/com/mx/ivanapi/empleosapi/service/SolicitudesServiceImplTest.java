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

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.controller.to.SolicitudTO;
import com.mx.ivanapi.empleosapi.controller.to.UsuarioTO;
import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.repository.SolicitudesRepository;
import com.mx.ivanapi.empleosapi.service.mapper.MapperEmpleosApi;

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
		when(repository.findAll(Sort.by(Constant.LABEL_MODEL_ID_SOLICITUDES))).thenReturn(new ArrayList<>());
		final List<SolicitudTO> lstSolicitudes = this.solicitud.buscarTodas();
		assertEquals(Constant.N000, lstSolicitudes.size());
		assertNotNull(lstSolicitudes);
		assertTrue(lstSolicitudes.isEmpty());
	}
	@Test
	public void buscarPorIdTest() {
		SOLICITUD_TEST.setIntIdSolicitudes(ID);
		when(this.repository.findById(Mockito.anyInt())).thenReturn(SOLICITUD_OPTIONAL);
		final SolicitudTO resultado = this.solicitud.buscarPorId(ID);
		assertEquals(ID, resultado.getIntIdSolicitudes());
		assertNotNull(resultado);
	}
	@Test
	public void buscarPorIdNullTest() {
		when(this.repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		final SolicitudTO resultado = this.solicitud.buscarPorId(ID);
		assertNull(resultado);	
	}
	@Test
	public void guardarTest() {
		final boolean blnResultado = this.solicitud.guardar(MapperEmpleosApi.convertSolicitudEntityTO(SOLICITUD_TEST) );
		assertEquals(Boolean.TRUE, blnResultado);
	}
	@Test
	public void eliminarTest(){
		final boolean blnResultado = this.solicitud.eliminar(Mockito.anyInt());
		assertEquals(Boolean.TRUE, blnResultado);
	}
	@Test
	public void obtenerSolicitudPorUsuarioTest() {
		when(this.repository.findByUsuario(Mockito.any())).thenReturn(LST_SOLICITUDES_TEST);
		final List<SolicitudTO> lstResultado = this.solicitud.obtenerSolicitudPorUsuario(new UsuarioTO());
		assertEquals(Constant.N001, lstResultado.size());
		assertNotNull(lstResultado);
		assertFalse(lstResultado.isEmpty());
	}
	@Test
	public void buscarTodasPaginadoTest() {
		Pageable pageable = PageRequest.of(Constant.N001, Constant.N008);
		when(repository.findAll( pageableMock)).thenReturn(solicitudPage);
		final Page<Solicitud> page= this.solicitud.buscarTodas(pageable);
		assertNull(page);
	}
}
