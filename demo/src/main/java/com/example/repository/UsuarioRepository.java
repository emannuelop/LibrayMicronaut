package com.example.repository;

import com.example.model.Usuario;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLoginAndSenha(String login, String senha);

}
