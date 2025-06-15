package com.example.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.NotBlank;
import io.micronaut.data.annotation.TypeDef;
import io.micronaut.data.model.DataType;

@MappedEntity
@Introspected
public class Usuario {

    @Id
    @GeneratedValue
    private Long idUsuario;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @TypeDef(type = DataType.STRING) // Persistir o enum como String no banco
    private Cargo cargo;

    // Getters e Setters

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
