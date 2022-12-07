package br.com.projeto.apitreino.controller;

import br.com.projeto.apitreino.model.Pessoa;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping()
public class Controller {

    @GetMapping()
    public String mensagem(){
        return "Hello Word!";
    }

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Seja bem vindo(a)!";
    }

    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome){
        return "Seja bem vindo(a) " + nome;
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa pessoa){
        return pessoa;
    }
}
