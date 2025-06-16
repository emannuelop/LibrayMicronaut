package com.example.service;

import java.util.List;

import com.example.dto.EmprestimoDTO;
import com.example.model.Autor;
import com.example.model.Emprestimo;
import com.example.model.Livro;
import com.example.model.Usuario;
import com.example.repository.EmprestimoRepository;
import com.example.repository.LivroRepository;
import com.example.repository.UsuarioRepository;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    
    @Inject
    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository){
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    // Criar novo emprestimo
    public Emprestimo salvarEmprestimo(EmprestimoDTO emprestimo) {
        Usuario usuario = usuarioRepository.findById(emprestimo.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com ID: " + emprestimo.idUsuario()));

        Livro livro = livroRepository.findById(emprestimo.idLivro())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + emprestimo.idLivro()));


        Emprestimo emprestimoNovo = new Emprestimo();
        emprestimoNovo.setLivro(livro);
        emprestimoNovo.setUsuario(usuario);
        return emprestimoRepository.save(emprestimoNovo);
    }

    // Buscar todos os emprestimoes
    public List<Emprestimo> buscarTodosEmprestimos() {
        return emprestimoRepository.findAll();
    }

    // Buscar emprestimo por ID
    public Emprestimo buscarEmprestimo(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado com ID: " + id));
    }

    // Atualizar emprestimo
    public Emprestimo atualizarEmprestimo(Long id, EmprestimoDTO emprestimoAtualizado) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado com ID: " + id));

        Usuario usuario = usuarioRepository.findById(emprestimoAtualizado.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com ID: " + emprestimoAtualizado.idUsuario()));

        Livro livro = livroRepository.findById(emprestimoAtualizado.idLivro())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + emprestimoAtualizado.idLivro()));

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        return emprestimoRepository.update(emprestimo);
    }

    // Deletar emprestimo por ID
    public void deletarEmprestimo(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new RuntimeException("Emprestimo não encontrado com ID: " + id);
        }
        emprestimoRepository.deleteById(id);
    }
}
