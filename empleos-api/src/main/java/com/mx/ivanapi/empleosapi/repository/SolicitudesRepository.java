package com.mx.ivanapi.empleosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ivanapi.empleosapi.model.Solicitud;
import com.mx.ivanapi.empleosapi.model.Usuario;



public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer>{

	List<Solicitud> findByUsuario(final Usuario usuario);
}
