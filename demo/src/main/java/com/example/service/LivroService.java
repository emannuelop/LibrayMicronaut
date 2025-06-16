package com.example.service;

import com.example.dto.LivroDTO;
import com.example.model.Autor;
import com.example.model.Livro;
import com.example.repository.AutorRepository;
import com.example.repository.LivroRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton // Equivalente ao @Service no Micronaut
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Inject
    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    // Criar novo livro
    public Livro salvarLivro(LivroDTO livro) {
        Autor autor = autorRepository.findById(livro.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + livro.idAutor()));

        Livro livroNovo = new Livro();
        livroNovo.setAutor(autor);
        livroNovo.setDisponivel(true);
        livroNovo.setIsbn(livro.isbn());
        livroNovo.setTitulo(livro.titulo());

        return livroRepository.save(livroNovo);
    }

    // Buscar todos os livros
    public List<Livro> buscarTodosLivros() {
        return livroRepository.findAll();
    }

    // Buscar livro por ID
    public Livro buscarLivro(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));
    }

    // Atualizar livro
    public Livro atualizarLivro(Long id, LivroDTO livroAtualizado) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com ID: " + id));

        Autor autor = autorRepository.findById(livroAtualizado.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + livroAtualizado.idAutor()));

        livro.setAutor(autor);
        livro.setIsbn(livroAtualizado.isbn());
        livro.setTitulo(livroAtualizado.titulo());

        return livroRepository.update(livro);
    }

    // Deletar livro
    public void deletarLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com ID: " + id);
        }
        livroRepository.deleteById(id);
    }
}
