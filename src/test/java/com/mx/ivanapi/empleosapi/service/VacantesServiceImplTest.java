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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

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
	private final static Optional<Vacante> VACANTE_OPTIONAL = Optional.of(VACANTE_TEST);
	private final List<Vacante> lstVacantes = new ArrayList<Vacante>();
	private final static int ID = 1;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		lstVacantes.add(VACANTE_TEST);
	}
	@Test
	public void buscarTodasTest() {
		when(vacanteRepository.findAll()).thenReturn(lstVacantes);
		final List<Vacante> lstResultado = this.serviceImpl.buscarTodas();
		assertNotNull(lstResultado);
		assertEquals(lstResultado.size(), 1);
		assertFalse(lstResultado.isEmpty());
	}
	@Test
	public void buscarTodasPageTest() {
		final Pageable pageable = PageRequest.of(1, 8);
		when(this.vacanteRepository.findAll( pageableMock)).thenReturn(solicitudPage);
		final Page<Vacante> page= this.serviceImpl.buscarTodas(pageable);
		assertNull(page);
	}
	@Test
	public void buscarDestacadasTest() {
		this.lstVacantes.add(new Vacante());
		when(vacanteRepository.findByIntDestacadoAndStrEstatusOrderByIntIdDesc(1, "Aprobada"))
		.thenReturn(lstVacantes);
		final List<Vacante>lstResultado = this.serviceImpl.buscarDestacadas(); 
		assertNotNull(lstResultado);
		assertEquals(lstResultado.size(), 2);
		assertFalse(lstResultado.isEmpty());
	}
	@Test
	public void buscarPorIdTest() {
		this.VACANTE_TEST.setStrNombre("Manuel");
		when(this.vacanteRepository.findById(Mockito.anyInt())).thenReturn(VACANTE_OPTIONAL);
		final Vacante resultado = this.serviceImpl.buscarPorId(ID);
		assertNotEquals(resultado.getStrNombre(),"Ivan");
		assertNotNull(resultado);
		assertFalse(resultado.getStrNombre().isEmpty());
	}
	@Test
	public void buscarPorIdNullTest() {
		when(this.vacanteRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		final Vacante resultado = this.serviceImpl.buscarPorId(ID);
		assertNull(resultado);
	}
	@Test
	public void guardarTest() {
		final boolean resultado = this.serviceImpl.guardar(VACANTE_TEST);
		assertTrue(resultado);
	}
}
