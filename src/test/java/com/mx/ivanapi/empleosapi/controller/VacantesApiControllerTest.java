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
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.controller.to.VacanteTO;
import com.mx.ivanapi.empleosapi.service.IVacantesService;

@RunWith(SpringJUnit4ClassRunner.class)
public class VacantesApiControllerTest {

	@InjectMocks
	private VacantesApiController apiController;
	@MockBean
	private IVacantesService vacanteRepository;
	private MockMvc mockMvc; 
	private ObjectMapper mapper= new ObjectMapper();
	private final static VacanteTO VACANTE = new VacanteTO();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
		VACANTE.setIntId(1);
		VACANTE.setStrNombre("INGENIERO");
	}
	@Test
	public void buscarTodasTest()throws Exception {
		when(vacanteRepository.buscarTodas()).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get(Constant.BASE_ENDPOINT_VACANTE + "/buscarTodas")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void buscarDestacadasTest()throws Exception{
		when(vacanteRepository.buscarDestacadas()).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get(Constant.BASE_ENDPOINT_VACANTE  + "/buscarDestacadas")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void buscarPorIdTest() throws Exception{
		when(vacanteRepository.buscarPorId(Mockito.anyInt())).thenReturn(Mockito.any());
		mockMvc.perform(MockMvcRequestBuilders.get(Constant.BASE_ENDPOINT_VACANTE  + "/buscarPorId/{id}",Constant.N001)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
	}
	@Test
	public void guardarTest()throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post(Constant.BASE_ENDPOINT_VACANTE  + "/guardar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(VACANTE)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void modificarOkTest()throws Exception {
		when(this.vacanteRepository.buscarPorId(Mockito.anyInt())).thenReturn(VACANTE);
		mockMvc.perform(MockMvcRequestBuilders.put(Constant.BASE_ENDPOINT_VACANTE  + "/modificar/{id}",Constant.N001)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(VACANTE)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test(expected = NestedServletException.class)
	public void modificarBadRequesTest()throws Exception {
		when(this.vacanteRepository.buscarPorId(Mockito.anyInt())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.put(Constant.BASE_ENDPOINT_VACANTE  + "/modificar/{id}",Constant.N001)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(VACANTE)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void eliminarOkTest()throws Exception{
		when(this.vacanteRepository.buscarPorId(Mockito.anyInt())).thenReturn(VACANTE);
		mockMvc.perform(MockMvcRequestBuilders.delete(Constant.BASE_ENDPOINT_VACANTE  + "/eliminar/{id}",Constant.N001)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test(expected = NestedServletException.class)
	public void eliminarBadTest()throws Exception{
		when(this.vacanteRepository.buscarPorId(Mockito.anyInt())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.delete(Constant.BASE_ENDPOINT_VACANTE  + "/eliminar/{id}",Constant.N001)
				.contentType(MediaType.APPLICATION_JSON));
	}
}
