package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;

@Introspected
public record LivroDTO(

    @NotBlank(message = "O titulo é obrigatório.")
    String titulo,

    Long idAutor,

    @NotBlank(message = "O Isbn é obrigatório.")
    String isbn
) {
    
}