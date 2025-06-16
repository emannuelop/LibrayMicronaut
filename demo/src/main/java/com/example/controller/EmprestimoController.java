package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.dto.EmprestimoDTO;
import com.example.model.Emprestimo;
import com.example.service.EmprestimoService;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

@Controller("/emprestimos")
public class EmprestimoController {
    
    private final EmprestimoService emprestimoService;

    @Inject
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @Post
    public HttpResponse<?> criarEmprestimo(@Body EmprestimoDTO emprestimo) {
        try{
            Emprestimo emprestimoNovo = emprestimoService.salvarEmprestimo(emprestimo);
            return HttpResponse.created(emprestimoNovo);
        }catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao criar emprestimo: " + e.getMessage());
        }
    }

    @Get
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoService.buscarTodosEmprestimos(); 
    }

    @Get("/{id}")
    public HttpResponse<?> buscarEmprestimo(Long id) {
        try{
            Emprestimo emprestimo = emprestimoService.buscarEmprestimo(id);
            return HttpResponse.ok(emprestimo);
        }catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao buscar emprestimo: " + e.getMessage());
        }
    }

    @Put("/{id}")
    public HttpResponse<?> atualizarEmprestimo(Long id, @Body EmprestimoDTO emprestimoAtualizado) {
        try{
        Emprestimo emprestimoAtualizadoCorretamente = emprestimoService.atualizarEmprestimo(id, emprestimoAtualizado);
        return HttpResponse.ok(emprestimoAtualizadoCorretamente);
        } catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao atualizar emprestimo: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deletarEmprestimo(Long id) {
        try{
        emprestimoService.deletarEmprestimo(id);
        return HttpResponse.noContent();
        }catch (PersistenceException e) {
            if (e.getCause() != null && e.getCause().getMessage().contains("foreign key constraint")) {
                return HttpResponse.badRequest("Não é possível excluir o emprestimo: existem outros registros relacionados.");
            }else{
                return HttpResponse.badRequest("Erro no banco de dados");
            }
        } catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao deletar usuario: " + e.getMessage());
        }
    }

}
