package com.example.service;

import com.example.dto.AutorDTO;
import com.example.model.Autor;
import com.example.repository.AutorRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton // Equivalente ao @Service do Spring
public class AutorService {

    private final AutorRepository autorRepository;

    @Inject
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Criar novo autor
    public Autor salvarAutor(AutorDTO autor) {
        Autor autorNovo = new Autor();
        autorNovo.setNome(autor.nome());
        autorNovo.setNacionalidade(autor.nacionalidade());
        return autorRepository.save(autorNovo);
    }

    // Buscar todos os autores
    public List<Autor> buscarTodosAutores() {
        return autorRepository.findAll();
    }

    // Buscar autor por ID
    public Autor buscarAutor(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));
    }

    // Atualizar autor
    public Autor atualizarAutor(Long id, AutorDTO autorAtualizado) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));

        autor.setNome(autorAtualizado.nome());
        autor.setNacionalidade(autorAtualizado.nacionalidade());
        return autorRepository.save(autor);
    }

    // Deletar autor por ID
    public void deletarAutor(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado com ID: " + id);
        }
        autorRepository.deleteById(id);
    }
}
