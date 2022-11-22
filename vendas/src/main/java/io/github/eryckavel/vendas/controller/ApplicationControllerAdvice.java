package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.exception.PedidoNaoEncontradoException;
import io.github.eryckavel.vendas.exception.RegraNegocioException;
import org.jetbrains.annotations.Async;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApisErros handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApisErros(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApisErros handlerPedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApisErros(ex.getMessage());
    }

}
