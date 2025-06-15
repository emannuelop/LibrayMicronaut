package com.example.repository;

import com.example.model.Livro;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long>{

}