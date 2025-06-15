package com.example.controller;

import com.example.dto.AutorDTO;
import com.example.model.Autor;
import com.example.service.AutorService;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/autores")
public class AutorController {

    private final AutorService autorService;

    @Inject
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @Post
    public Autor criarAutor(@Body AutorDTO autor) {
        return autorService.salvarAutor(autor);
    }

    @Get
    public List<Autor> listarAutores() {
        return autorService.buscarTodosAutores();
    }

    @Get("/{id}")
    public Autor buscarAutor(@PathVariable Long id) {
        return autorService.buscarAutor(id);
    }

    @Put("/{id}")
    public Autor atualizarAutor(@PathVariable Long id, @Body AutorDTO autorAtualizado) {
        return autorService.atualizarAutor(id, autorAtualizado);
    }

    @Delete("/{id}")
    public void deletarAutor(@PathVariable Long id) {
        autorService.deletarAutor(id);
    }
}
