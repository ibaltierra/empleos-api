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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.ivanapi.empleosapi.model.Usuario;
import com.mx.ivanapi.empleosapi.service.IUsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioApiControllerTest {

	@InjectMocks
	private UsuarioApiController controller;
	@MockBean
	private IUsuarioService service; 
	private MockMvc mockMvc; 
	private final static Usuario USUARIO_BUSQUEDA = new Usuario();
	private final static Usuario USUARIO_GUARDAR = new Usuario();
	private final static String NAME = "Ivan";
	private ObjectMapper mapper = new ObjectMapper();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	@Test
	public void buscarTodoTest()throws Exception {
		when(this.service.buscarTodo()).thenReturn(new ArrayList<Usuario>());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/apiUsuario/buscarTodo")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void buscarPorIdTest() throws Exception{
		when(this.service.buscarPorId(Mockito.anyInt())).thenReturn(USUARIO_BUSQUEDA);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/apiUsuario/buscarPorId/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void eliminarTest()throws Exception {
		when(this.service.eliminar(Mockito.anyInt())).thenReturn(Boolean.TRUE);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/apiUsuario/eliminar/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test(expected = NestedServletException.class)
	public void eliminarBadRequestTest()throws Exception {
		when(this.service.eliminar(Mockito.anyInt())).thenReturn(Boolean.FALSE);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/apiUsuario/eliminar/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void buscarPorUserNameTest()throws Exception {
		when(this.service.buscarPorUserName(Mockito.anyString())).thenReturn(USUARIO_BUSQUEDA);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/apiUsuario/buscarPorUserName/{username}", NAME)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void guardar()throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/apiUsuario/guardar")
				.content(mapper.writeValueAsString(USUARIO_GUARDAR))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
}
