package com.example.service;

import com.example.dto.UsuarioDTO;
import com.example.model.Cargo;
import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton // Equivalente ao @Service do Spring
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Inject
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Criar novo usuário
    public Usuario salvarUsuario(UsuarioDTO usuario) {
        Usuario usuarioNovo = new Usuario();
        usuarioNovo.setLogin(usuario.login());
        usuarioNovo.setSenha(usuario.senha());

        try {
            usuarioNovo.setCargo(Cargo.valueOf(usuario.idCargo()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Cargo inválido: " + usuario.idCargo());
        }

        return usuarioRepository.save(usuarioNovo);
    }

    // Buscar todos os usuários
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    // Atualizar usuário
    public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        usuario.setLogin(usuarioAtualizado.login());
        usuario.setSenha(usuarioAtualizado.senha());

        try {
            usuario.setCargo(Cargo.valueOf(usuarioAtualizado.idCargo()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Cargo inválido: " + usuarioAtualizado.idCargo());
        }

        return usuarioRepository.save(usuario);
    }

    // Deletar usuário
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Login
    public boolean login(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha).isPresent();
    }
}
