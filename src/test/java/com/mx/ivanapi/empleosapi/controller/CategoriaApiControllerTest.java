package com.mx.ivanapi.empleosapi.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.service.ICategoriasService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoriaApiControllerTest {

	@MockBean
	private ICategoriasService categoriasService;
	@InjectMocks
	private CategoriaApiController apiController;
	private MockMvc mockMvc; 
	private ObjectMapper mapper = new ObjectMapper();
	private final static Categoria CATEGORIA = new Categoria();
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
		CATEGORIA.setIntId(1);
		CATEGORIA.setStrNombre("Terror.");
	}
	@Test
	public void buscarTodasTest() throws Exception{
		when(categoriasService.buscarTodas()).thenReturn(new ArrayList<Categoria>());
		this.mockMvc.perform(MockMvcRequestBuilders.get(Constant.BASE_PACKAGE_CATEGORIA + "/buscarTodas")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void buscarPorIdTest() throws Exception{
		when(categoriasService.buscarPorId(Mockito.anyInt())).thenReturn(Mockito.any());
		this.mockMvc.perform(MockMvcRequestBuilders.get(Constant.BASE_PACKAGE_CATEGORIA+ "/buscarPorId/{id}",1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void guardarTest()throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post(Constant.BASE_PACKAGE_CATEGORIA + "/guardar")
				.content(this.mapper.writeValueAsString(CATEGORIA))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void eliminarTest() throws Exception{
		when(categoriasService.buscarPorId(Mockito.anyInt())).thenReturn(CATEGORIA);
		this.mockMvc.perform(MockMvcRequestBuilders.delete(Constant.BASE_PACKAGE_CATEGORIA + "/eliminar/{id}",1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
}
