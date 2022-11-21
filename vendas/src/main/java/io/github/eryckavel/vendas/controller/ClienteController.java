package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.repository.ClientesRepositorys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepositorys clientesRepositorys;

    @GetMapping
    public List<Cliente> listaCliente(){
        return clientesRepositorys.findAll();
    }

    @GetMapping("/busca")
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro, matcher);
        return clientesRepositorys.findAll(example);
    }

    @GetMapping("/{id}")
    public Cliente consultaCliente(@PathVariable("id") Integer id){
        return clientesRepositorys
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encotrado"));
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clientesRepositorys.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable Integer id){
        clientesRepositorys
                .findById(id)
                .map(cliente -> {
                    clientesRepositorys.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void atualizar(@RequestBody Cliente cliente,
                                       @PathVariable("id") Integer id){
           clientesRepositorys
                   .findById(id)
                   .map(clienteExistente ->{
                      cliente.setId(clienteExistente.getId());
                      clientesRepositorys.save(cliente);
                      return clienteExistente;
                   })
                   .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

}
