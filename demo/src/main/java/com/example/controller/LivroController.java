package com.example.controller;

import com.example.service.LivroService;
import com.example.dto.LivroDTO;
import com.example.model.Livro;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/livros")
public class LivroController {

    private final LivroService livroService;

    @Inject
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @Post
    public Livro criarLivro(@Body LivroDTO livro) {
        return livroService.salvarLivro(livro);
    }

    @Get
    public List<Livro> listarLivros() {
        return livroService.buscarTodosLivros(); 
    }

    @Get("/{id}")
    public Livro buscarLivro(Long id) {
        return livroService.buscarLivro(id); 
    }

    @Put("/{id}")
    public Livro atualizarLivro(Long id, @Body LivroDTO livroAtualizado) {
        return livroService.atualizarLivro(id, livroAtualizado);
    }

    @Delete("/{id}")
    public void deletarLivro(Long id) {
        livroService.deletarLivro(id);
    }
}
