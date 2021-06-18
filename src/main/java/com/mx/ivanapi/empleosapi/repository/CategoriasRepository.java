package com.mx.ivanapi.empleosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.mx.ivanapi.empleosapi.model.Categoria;



//CrudRepository es de spring data.
//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
}
