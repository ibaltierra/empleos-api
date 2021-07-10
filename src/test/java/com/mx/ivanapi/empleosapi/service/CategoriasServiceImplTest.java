package com.mx.ivanapi.empleosapi.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.NestedServletException;

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.controller.to.CategoriaTO;
import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.repository.CategoriasRepository;
import com.mx.ivanapi.empleosapi.response.ExceptionGeneria;
import com.mx.ivanapi.empleosapi.service.mapper.MapperEmpleosApi;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoriasServiceImplTest {

	@MockBean
	private CategoriasRepository categoriasRepository;
	@InjectMocks
	private CategoriasServiceImpl categoriasServiceImpl;
	private List<Categoria> lstCategorias = new ArrayList<Categoria>();
	private final static Categoria CATEGORIA_TEST = new Categoria();
	private final static Optional<Categoria> CATEGORIA_OPTIONAL = Optional.of(CATEGORIA_TEST);
	private final static String LABEL_COMPARE = "Terror.";
	@Mock
    private Pageable pageableMock;
	@Mock
    private Page<Categoria> categoriaPage;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		lstCategorias.add(new Categoria());
	}
	@Test
	public void buscarTodasTest() {
		when(categoriasRepository.findAll(Sort.by(Constant.LABEL_MODEL_INT_ID))).thenReturn(lstCategorias);
		final List<CategoriaTO> lst = categoriasServiceImpl.buscarTodas();
		assertEquals(Constant.N001, lst.size());
		assertNotNull(lst);
	}
	@Test
	public void buscarPorIdTest() {
		CATEGORIA_TEST.setStrNombre(Constant.LABEL_NOMBRE_COMPARAR);
		when(this.categoriasRepository.findById(Mockito.anyInt())).thenReturn(CATEGORIA_OPTIONAL);
		final CategoriaTO catTemp = categoriasServiceImpl.buscarPorId(Mockito.anyInt());
		assertNotNull(catTemp);
		assertEquals(LABEL_COMPARE, catTemp.getStrNombre());
		assertFalse(catTemp.getStrNombre().isEmpty());
	}
	@Test
	public void buscarPorIdNullTest() {
		when(this.categoriasRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		final CategoriaTO catTemp = categoriasServiceImpl.buscarPorId(Mockito.anyInt());
		assertNull(catTemp);
	}
	@Test
	public void guardarTest() {
		final boolean blnResultado = this.categoriasServiceImpl.guardar(MapperEmpleosApi.convertCategoriaEntityTO(CATEGORIA_TEST) );
		assertEquals(Boolean.TRUE, blnResultado);
		assertTrue(blnResultado);
	}	
	@Test
	public void eliminarTest() throws ExceptionGeneria{
		final boolean blnResultado = this.categoriasServiceImpl.eliminar(Mockito.anyInt());
		assertTrue(blnResultado);
	}
	
	@Test
	public void buscarTodasPageTest() {
		final Pageable pageable = PageRequest.of(Constant.N001, Constant.N008);
		when(categoriasRepository.findAll( pageableMock)).thenReturn(categoriaPage);
		final Page<Categoria> page= this.categoriasServiceImpl.buscarTodasPage(pageable);
		assertNull(page);
	}
}
