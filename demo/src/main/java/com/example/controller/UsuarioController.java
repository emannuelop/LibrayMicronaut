package com.example.controller;

import com.example.service.UsuarioService;
import com.example.dto.UsuarioDTO;
import com.example.model.Usuario;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Inject
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Post
    public HttpResponse<?> criarUsuario(@Body UsuarioDTO usuario) {

        try{
        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return HttpResponse.created(novoUsuario);
        }catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao criar usuario: " + e.getMessage());
        }
    }

    @Get
    public List<Usuario> listarUsuarios() {
        return usuarioService.buscarTodosUsuarios(); 
    }

    @Get("/{id}")
    public HttpResponse<?> buscarUsuario(Long id) {
        try{
            Usuario usuarioBuscado = usuarioService.buscarUsuario(id); 
        return HttpResponse.ok(usuarioBuscado);
        } catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao buscar usuario: " + e.getMessage());
        }
    }

    @Put("/{id}")
    public HttpResponse<?> atualizarUsuario(Long id, @Body UsuarioDTO usuarioAtualizado) {

        try{
            Usuario usuarioAtualizadoCorretamente = usuarioService.atualizarUsuario(id, usuarioAtualizado);
        return HttpResponse.ok(usuarioAtualizadoCorretamente);
        }catch(ConstraintViolationException e){
            String mensagemErro = e.getConstraintViolations().stream()
                    .map(cv -> String.format("Campo '%s': %s", cv.getPropertyPath(), cv.getMessage()))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest("Erro de validação: " + mensagemErro);
        }catch(RuntimeException e){
            return HttpResponse.badRequest("Erro ao atualizar usuario: " + e.getMessage());
        }
    }

    @Delete("/{id}")
    public HttpResponse<?> deletarUsuario(Long id) {

        try{
            usuarioService.deletarUsuario(id);
            return HttpResponse.noContent();
        } catch (PersistenceException e) {
            if (e.getCause() != null && e.getCause().getMessage().contains("foreign key constraint")) {
                return HttpResponse.badRequest("Não é possível excluir o usuário: existem empréstimos ou outros registros relacionados.");
            }else{
                return HttpResponse.badRequest("Erro no banco de dados");
            }
        } catch(RuntimeException e){
            return HttpResponse.notFound("Erro ao deletar usuario: " + e.getMessage());
        }
    }
}
