package com.mx.ivanapi.empleosapi.controller;

import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Vacante;
import com.mx.ivanapi.empleosapi.service.ISolicitudesService;

@RunWith(SpringJUnit4ClassRunner.class)
public class SolicitudesApiControllerTest {

	@MockBean
	private ISolicitudesService service;
	@InjectMocks
	private SolicitudesApiController controller;
	@MockBean
    private Pageable pageableMock;
	@MockBean
    private Page<Solicitud> solicitudPage;
	private MockMvc mockMvc; 
	
	private final static String BASE_PACKAGE ="/apiSolicitudes";
	private final static Solicitud SOLICITUD = new Solicitud();
	private final static Vacante VACANTE = new Vacante();
	private ObjectMapper mapper = new ObjectMapper();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	@Test
	public void buscarTodasTest() throws Exception{
		when(service.buscarTodas()).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get(BASE_PACKAGE + "/buscarTodas")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		SOLICITUD.setIntIdSolicitudes(1);
		VACANTE.setIntId(1);
		SOLICITUD.setVacante(VACANTE);
		SOLICITUD.setStrComentarios("Test.");
	}
	@Test
	public void buscarPorIdTest()throws Exception {
		when(this.service.buscarPorId(Mockito.anyInt())).thenReturn(SOLICITUD);
		this.mockMvc.perform(MockMvcRequestBuilders.get(this.BASE_PACKAGE + "/buscarPorId/{id}",1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void guardarTest() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PACKAGE + "/guardar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(SOLICITUD)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void eliminarTest()throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete(BASE_PACKAGE + "/eliminar/{id}",1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	/*@Test
	public void buscarTodasPaginadoTest() throws Exception{
		final Pageable pageable = PageRequest.of(1, 8);
		when(service.buscarTodas(pageable)).thenReturn(solicitudPage);
		this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_PACKAGE+"/buscarTodasPaginado?page=1&size=10")
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}*/
}
