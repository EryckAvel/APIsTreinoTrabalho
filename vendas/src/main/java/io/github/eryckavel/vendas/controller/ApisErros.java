package io.github.eryckavel.vendas.controller;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApisErros {
    @Getter
    private final List<String> erros;


    public ApisErros (String mensagemErro){
        this.erros = Arrays.asList((mensagemErro));
    }

}
