package com.example.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record UsuarioDTO(

    String login,

    String senha,

    int idCargo

) {
    
}