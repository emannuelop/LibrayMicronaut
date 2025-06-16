package com.example.controller;

import com.example.dto.AutorDTO;
import com.example.model.Autor;
import com.example.service.AutorService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/autores")
public class AutorController {

    private final AutorService autorService;

    @Inject
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @Post
    public HttpResponse<?> criarAutor(@Body AutorDTO autor) {
        try{
            Autor autorNovo = autorService.salvarAutor(autor);
            return HttpResponse.created(autorNovo);
        } catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao criar Autor: " + e.getMessage());
        }
    }

    @Get
    public List<Autor> listarAutores() {
        return autorService.buscarTodosAutores();
    }

    @Get("/{id}")
    public HttpResponse<?> buscarAutor(@PathVariable Long id) {
        try{
        Autor autor = autorService.buscarAutor(id);
        return HttpResponse.ok(autor);
        } catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao buscar usuario: " + e.getMessage());
        }
    }

    @Put("/{id}")
    public HttpResponse<?> atualizarAutor(@PathVariable Long id, @Body AutorDTO autorAtualizado) {
        try{
        Autor autorAtualizadoCorretamente = autorService.atualizarAutor(id, autorAtualizado);
        return HttpResponse.ok(autorAtualizadoCorretamente);
        } catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao atualizar autor: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deletarAutor(@PathVariable Long id) {
        try{
        autorService.deletarAutor(id);
        return HttpResponse.noContent();
        } catch (PersistenceException e) {
            if (e.getCause() != null && e.getCause().getMessage().contains("foreign key constraint")) {
                return HttpResponse.badRequest("Não é possível excluir o usuário: existem outros registros relacionados.");
            }else{
                return HttpResponse.badRequest("Erro no banco de dados");
            }
        } catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao deletar autor: " + e.getMessage());
        }
    }
}
