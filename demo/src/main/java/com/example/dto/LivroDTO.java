package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record LivroDTO(

    @NotBlank(message = "O titulo é obrigatório.")
    String titulo,

    Long idAutor,

    @NotBlank(message = "O Isbn é obrigatório.")
    String isbn
) {
    
}