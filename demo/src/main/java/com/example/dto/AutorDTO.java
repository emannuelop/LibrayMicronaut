package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record AutorDTO(

    @NotBlank(message = "O nome é obrigatório.")
    String nome,

    @NotBlank(message = "A nacionalidade é obrigatória.")
    String nacionalidade

) {}
