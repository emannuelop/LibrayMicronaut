package com.example.repository;

import com.example.model.Autor;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {
}
