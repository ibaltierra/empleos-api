package com.mx.ivanapi.empleosapi.service;

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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.mx.ivanapi.empleosapi.controller.constants.Constant;
import com.mx.ivanapi.empleosapi.controller.to.VacanteTO;
import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Vacante;
import com.mx.ivanapi.empleosapi.repository.VacanteRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class VacantesServiceImplTest {
	@MockBean
	private VacanteRepository vacanteRepository;
	@MockBean
	protected RestTemplate restTemplate;
	@InjectMocks
	private VacantesServiceImpl serviceImpl;
	@MockBean
    private Pageable pageableMock;
	@MockBean
    private Page<Vacante> solicitudPage;
	private final static Vacante VACANTE_TEST = new Vacante();
	private final static VacanteTO VACANTE_TEST_2 = new VacanteTO();
	private final static Optional<Vacante> VACANTE_OPTIONAL = Optional.of(VACANTE_TEST);
	private static final String NAME_COMPARE = "Ivan";
	private final List<Vacante> lstVacantes = new ArrayList<>();
	private final static int ID = 1;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		lstVacantes.add(new Vacante());
	}
	@Test
	public void buscarTodasTest() {
		when(vacanteRepository.findAll()).thenReturn(lstVacantes);
		final List<VacanteTO> lstResultado = this.serviceImpl.buscarTodas();
		assertNotNull(lstResultado);
		assertEquals(lstResultado.size(), 1);
		assertFalse(lstResultado.isEmpty());
	}
	@Test
	public void buscarTodasPageTest() {
		final Pageable pageable = PageRequest.of(Constant.N001, Constant.N008);
		when(this.vacanteRepository.findAll( pageableMock)).thenReturn(solicitudPage);
		final Page<Vacante> page= this.serviceImpl.buscarTodas(pageable);
		assertNull(page);
	}
	@Test
	public void buscarDestacadasTest() {
		this.lstVacantes.add(new Vacante());
		when(vacanteRepository.findByIntDestacadoAndStrEstatusOrderByIntIdDesc(Constant.N001, "Aprobada"))
		.thenReturn(lstVacantes);
		final List<VacanteTO>lstResultado = this.serviceImpl.buscarDestacadas(); 
		assertNotNull(lstResultado);
		assertEquals(lstResultado.size(), Constant.N002);
		assertFalse(lstResultado.isEmpty());
	}
	@Test
	public void buscarPorIdTest() {
		this.VACANTE_TEST.setStrNombre("Manuel");
		when(this.vacanteRepository.findById(Mockito.anyInt())).thenReturn(VACANTE_OPTIONAL);
		final VacanteTO resultado = this.serviceImpl.buscarPorId(ID);
		assertNotEquals(resultado.getStrNombre(), NAME_COMPARE);
		assertNotNull(resultado);
		assertFalse(resultado.getStrNombre().isEmpty());
	}
	@Test
	public void buscarPorIdNullTest() {
		when(this.vacanteRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		final VacanteTO resultado = this.serviceImpl.buscarPorId(ID);
		assertNull(resultado);
	}
	@Test
	public void guardarTest() {
		final boolean resultado = this.serviceImpl.guardar(VACANTE_TEST_2);
		assertTrue(resultado);
	}
	@Test
	public void eliminarTest() {
		this.serviceImpl.eliminar(Mockito.anyInt());
		
	}
	@Test
	public void buscarByExampleTest() {
		final Vacante vacante = new Vacante();
		vacante.setIntId(Constant.N001);
		final List<Vacante> lstVacantes = new ArrayList<>();
		lstVacantes.add(vacante);
		final ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("strDescripcion"
				, ExampleMatcher.GenericPropertyMatchers.contains());
		when(vacanteRepository.findAll( Example.of(vacante, matcher)  ))
		.thenReturn(lstVacantes);
		final List<Vacante> lstVacantesRes = this.serviceImpl.buscarByExample(Example.of(vacante, matcher) );
		assertNotNull(lstVacantesRes);
		
	}
}
