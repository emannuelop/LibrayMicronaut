package com.example.controller;

import com.example.service.UsuarioService;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;

@Controller("/login")
public class LoginController {

    private final UsuarioService usuarioService;

    @Inject
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Post
    public HttpResponse<String> login(@QueryValue String login, @QueryValue String senha) {
        boolean sucesso = usuarioService.login(login, senha);
        if (sucesso) {
            return HttpResponse.ok("Login bem-sucedido!");
        } else {
            return HttpResponse.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos!");
        }
    }
}
