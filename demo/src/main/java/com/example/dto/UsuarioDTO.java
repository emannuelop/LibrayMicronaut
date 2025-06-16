package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UsuarioDTO(

    String login,

    String senha,

    int idCargo

) {
    
}