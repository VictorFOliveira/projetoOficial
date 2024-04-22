package com.GeladaoEstacio.Geladao.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result) {


        log.error("Api Error", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON).body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)", result));
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioException(UsuarioException ex, HttpServletRequest request) {
        log.error("Api Error", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Defina o status de acordo com a exceção específica, se necessário

        if (ex.getMessage().equals(UsuarioException.USUARIO_EXISTENTE)) {
            status = HttpStatus.CONFLICT; // Usuário já existente
        } else if (ex.getMessage().equals(UsuarioException.USUARIO_NAO_ENCONTRADO)) {
            status = HttpStatus.NOT_FOUND; // Usuário não encontrado
        } else if (ex.getMessage().equals(UsuarioException.USUARIOS_NAO_ENCONTRADOS)) {
            status = HttpStatus.NOT_FOUND; // Usuários não encontrados
        } else if (ex.getMessage().equals(UsuarioException.USUARIO_INATIVO)) {
            status = HttpStatus.BAD_REQUEST; // Usuário desativado
        } else if (ex.getMessage().equals(UsuarioException.USUARIO_SEM_NOME)) {
            status = HttpStatus.BAD_REQUEST; // usuário não informou o nome
        } else if (ex.getMessage().equals(UsuarioException.USUARIO_SEM_EMAIL)) {
            status = HttpStatus.BAD_REQUEST; // usuário não informou o e-mail
        } else if (ex.getMessage().equals(UsuarioException.USUARIO_ADM)) {
            status = HttpStatus.BAD_REQUEST; // usuário já é ADM
        } else if (ex.getMessage().equals(UsuarioException.VENDEDOR_NAO_LOCALIZADO)) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex.getMessage().equals(UsuarioException.USUARIO_VENDEDOR)) {
            status = HttpStatus.BAD_REQUEST;
        }


        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, ex.getMessage()));
    }

    @ExceptionHandler(ProdutoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioException(ProdutoException ex, HttpServletRequest request) {
        log.error("Api Error", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Defina o status de acordo com a exceção específica, se necessário

        if (ex.getMessage().equals(ProdutoException.PRODUTO_EXISTENTE)) {
            status = HttpStatus.CONFLICT; // Produto já existente
        }
        if (ex.getMessage().equals(ProdutoException.PRODUTO_NAO_ENCONTRADO)) {
            status = HttpStatus.NOT_FOUND; // Produto não encontrado
        }
        if (ex.getMessage().equals(ProdutoException.PRODUTO_SEM_PRECO)) {
            status = HttpStatus.BAD_REQUEST; //
        }
        if (ex.getMessage().equals(ProdutoException.PRODUTO_SEM_NOME)) {
            status = HttpStatus.BAD_REQUEST;
        }
        if (ex.getMessage().equals(ProdutoException.PRODUTO_COM_DATA_MENOR_QUE_DATA_ATUAL)) {
            status = HttpStatus.BAD_REQUEST;
        }
        if (ex.getMessage().equals(ProdutoException.PRODUTO_SEM_QUANTIDADE_OU_ZERADA)) {
            status = HttpStatus.BAD_REQUEST;
        }


        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, ex.getMessage()));
    }

    @ExceptionHandler(VendaException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioException(VendaException ex, HttpServletRequest request) {
        log.error("Api Error", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Defina o status de acordo com a exceção específica, se necessário

        if (ex.getMessage().equals(VendaException.VENDA_NAO_LOCALIZADA)) {
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, ex.getMessage()));
    }

}

