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
    @ResponseBody
    public List<Cliente> listaCliente(){
        return clientesRepositorys.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id){
        Optional<Cliente> optionalCliente = clientesRepositorys.findById(id);
        if (optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientesRepositorys.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
        //return ResponseEntity.ok().body("Inserção realizada com sucesso!");
    }

    @DeleteMapping("{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> clienteOptional= clientesRepositorys.findById(id);
        if (clienteOptional.isPresent()){
            clientesRepositorys.delete(clienteOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
