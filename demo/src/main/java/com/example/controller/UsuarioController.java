package com.example.controller;

import com.example.service.UsuarioService;
import com.example.dto.UsuarioDTO;
import com.example.model.Usuario;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Inject
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Post
    public Usuario criarUsuario(@Body UsuarioDTO usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    @Get
    public List<Usuario> listarUsuarios() {
        return usuarioService.buscarTodosUsuarios(); 
    }

    @Get("/{id}")
    public Usuario buscarUsuario(Long id) {
        return usuarioService.buscarUsuario(id); 
    }

    @Put("/{id}")
    public Usuario atualizarUsuario(Long id, @Body UsuarioDTO usuarioAtualizado) {
        return usuarioService.atualizarUsuario(id, usuarioAtualizado);
    }

    @Delete("/{id}")
    public void deletarUsuario(Long id) {
        usuarioService.deletarUsuario(id);
    }
}
