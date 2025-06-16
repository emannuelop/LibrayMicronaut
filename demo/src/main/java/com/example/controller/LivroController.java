package com.example.controller;

import com.example.service.LivroService;
import com.example.dto.LivroDTO;
import com.example.model.Livro;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/livros")
public class LivroController {

    private final LivroService livroService;

    @Inject
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @Post
    public HttpResponse<?> criarLivro(@Body LivroDTO livro) {
        try{
        Livro livroCriado = livroService.salvarLivro(livro);
        return HttpResponse.created(livroCriado);
        }catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao criar Livro: " + e.getMessage());
        }
    }

    @Get
    public List<Livro> listarLivros() {
        return livroService.buscarTodosLivros(); 
    }

    @Get("/{id}")
    public HttpResponse<?> buscarLivro(Long id) {
        try{
        Livro livro = livroService.buscarLivro(id); 
        return HttpResponse.ok(livro);
        } catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao buscar usuario: " + e.getMessage());
        } 
    }

    @Put("/{id}")
    public HttpResponse<?> atualizarLivro(Long id, @Body LivroDTO livroAtualizado) {
        try{
            Livro livroAtualizadoCorretamente = livroService.atualizarLivro(id, livroAtualizado);
            return HttpResponse.ok(livroAtualizadoCorretamente);
        }catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deletarLivro(Long id) {
        try{
        livroService.deletarLivro(id);
        return HttpResponse.noContent();
        }catch (PersistenceException e) {
            if (e.getCause() != null && e.getCause().getMessage().contains("foreign key constraint")) {
                return HttpResponse.badRequest("Não é possível excluir o Livro: existem empréstimos ou outros registros relacionados.");
            }else{
                return HttpResponse.badRequest("Erro no banco de dados");
            }
        } catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao deletar usuario: " + e.getMessage());
        }
    }
}
