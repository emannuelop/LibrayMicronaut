package com.example.repository;

import com.example.model.Emprestimo;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    
}
