package com.example.controller;

import java.util.List;

import com.example.dto.EmprestimoDTO;
import com.example.model.Emprestimo;
import com.example.service.EmprestimoService;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/emprestimos")
public class EmprestimoController {
    
    private final EmprestimoService emprestimoService;

    @Inject
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @Post
    public Emprestimo criarEmprestimo(@Body EmprestimoDTO emprestimo) {
        return emprestimoService.salvarEmprestimo(emprestimo);
    }

    @Get
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoService.buscarTodosEmprestimos(); 
    }

    @Get("/{id}")
    public Emprestimo buscarEmprestimo(Long id) {
        return emprestimoService.buscarEmprestimo(id); 
    }

    @Put("/{id}")
    public Emprestimo atualizarEmprestimo(Long id, @Body EmprestimoDTO emprestimoAtualizado) {
        return emprestimoService.atualizarEmprestimo(id, emprestimoAtualizado);
    }

    @Delete("/{id}")
    public void deletarEmprestimo(Long id) {
        emprestimoService.deletarEmprestimo(id);
    }

}
