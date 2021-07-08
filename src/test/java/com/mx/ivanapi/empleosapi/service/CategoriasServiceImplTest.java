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
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.model.Categoria;
import com.mx.ivanapi.empleosapi.repository.CategoriasRepository;

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
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		lstCategorias.add(new Categoria());
	}
	@Test
	public void buscarTodasTest() {
		when(categoriasRepository.findAll(Sort.by(Constant.LABEL_MODEL_INT_ID))).thenReturn(lstCategorias);
		final List lst = categoriasServiceImpl.buscarTodas();
		assertEquals(lst.size(), 1);
		assertNotNull(lst);
	}
	@Test
	public void buscarPorIdTest() {
		CATEGORIA_TEST.setStrNombre(Constant.LABEL_NOMBRE_COMPARAR);
		when(this.categoriasRepository.findById(Mockito.anyInt())).thenReturn(CATEGORIA_OPTIONAL);
		final Categoria catTemp = categoriasServiceImpl.buscarPorId(Mockito.anyInt());
		assertNotNull(catTemp);
		assertEquals(catTemp.getStrNombre(), LABEL_COMPARE);
		assertFalse(catTemp.getStrNombre().isEmpty());
	}
	@Test
	public void buscarPorIdNullTest() {
		when(this.categoriasRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		final Categoria catTemp = categoriasServiceImpl.buscarPorId(Mockito.anyInt());
		assertNull(catTemp);
	}
	@Test
	public void guardarTest() {
		final boolean blnResultado = this.categoriasServiceImpl.guardar(CATEGORIA_TEST);
		assertEquals(Boolean.TRUE, blnResultado);
		assertTrue(blnResultado);
	}
}
