package br.com.eryck.sistema.controller;

import br.com.eryck.sistema.dto.ClienteDto;
import br.com.eryck.sistema.model.Cliente;
import br.com.eryck.sistema.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listaClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarCliente(@PathVariable(value = "id") Long id){
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if (clienteOptional.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
    }


    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody @Valid ClienteDto dto){
        var cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable(value = "id")Long id, ClienteDto dto){
        Optional<Cliente> clienteOptional = clienteService.findById(id);
        if (clienteOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        var cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(cliente));
    }

}
