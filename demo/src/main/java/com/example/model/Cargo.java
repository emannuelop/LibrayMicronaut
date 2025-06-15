package com.example.model;

import io.micronaut.core.annotation.Introspected;

@Introspected // serialização/deserialização no Micronaut
public enum Cargo {
    ADMIN(1, "Administrador"),
    FUNCIONARIO(2, "Funcionário"),
    CLIENTE(3, "Cliente");

    private final int id;
    private final String label;

    Cargo(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Cargo valueOf(Integer id) {
        if (id == null) return null;

        for (Cargo cargo : Cargo.values()) {
            if (id == cargo.id) return cargo;
        }

        throw new IllegalArgumentException("Número fora das opções disponíveis");
    }
}
