package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.repository.ClientesRepositorys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepositorys clientesRepositorys;

    @GetMapping
    public List<Cliente> getClienteById(){
        return clientesRepositorys.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id){
        Optional<Cliente> optionalCliente = clientesRepositorys.findById(id);
        if (optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientesRepositorys.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }



}
