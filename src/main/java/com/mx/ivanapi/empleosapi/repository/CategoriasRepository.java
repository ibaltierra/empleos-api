package com.mx.ivanapi.empleosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.ivanapi.empleosapi.model.Categoria;


public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
}
