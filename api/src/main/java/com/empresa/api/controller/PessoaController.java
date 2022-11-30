package com.empresa.api.controller;

import com.empresa.api.dto.PessoaDto;
import com.empresa.api.model.Pessoa;
import com.empresa.api.services.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {


    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> salvarPessoa(@RequestBody @Valid PessoaDto pessoaDto){
        if (pessoaService.existsByCpf(pessoaDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: placa de carro ja existente no sistema!");
        }
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> pesquisarPessoa(@PathVariable("id") Long id){

        Optional<Pessoa> pessoaOptional = pessoaService.fidById(id);
        if (!pessoaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrado!");
        }
            return ResponseEntity.status(HttpStatus.OK).body(pessoaOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizandoPessoa(@PathVariable("id") Long id, @RequestBody  @Valid PessoaDto pessoaDto){
        if (pessoaService.existsByCpf(pessoaDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: placa de carro ja existente no sistema!");
        }
        Optional<Pessoa> pessoaOptional = pessoaService.fidById(id);
        if(!pessoaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada!");
        }
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletandoPessoa(@PathVariable("id") Long id){
        Optional<Pessoa> pessoaOptional = pessoaService.fidById(id);
        if (!pessoaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("Pessoa não encontrada!");
        }
        pessoaService.delete(pessoaOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pessoa deletada");

    }

}
