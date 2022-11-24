package br.com.erudio.apigateway.controller;

import br.com.erudio.apigateway.dto.PessoaDto;
import br.com.erudio.apigateway.model.Pessoa;
import br.com.erudio.apigateway.services.PessoaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaServices pessoaServices;


    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable(value = "id") Long id) throws Exception {
        return pessoaServices.findById(id);
    }


    @GetMapping
    public List<PessoaDto> findAll(){
        return pessoaServices.findAll();
    }

    @PostMapping
    public Pessoa create(@RequestBody Pessoa pessoa){
        return pessoaServices.create(pessoa);
    }

    @PutMapping
    public Pessoa update(@RequestBody Pessoa pessoa){
        return pessoaServices.update(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        pessoaServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
