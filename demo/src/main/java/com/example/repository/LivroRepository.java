package com.example.repository;

import java.util.Optional;

import com.example.model.Livro;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    Optional<Livro> findByIsbn(String isbn);
}