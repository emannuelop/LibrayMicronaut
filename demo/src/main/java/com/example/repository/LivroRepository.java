package com.example.repository;

import com.example.model.Livro;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}