package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.dto.PedidoDto;
import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.model.Pedido;
import io.github.eryckavel.vendas.repository.PedidoRepository;
import io.github.eryckavel.vendas.services.PedidosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidosServices pedidosServices;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> listar(){
        return pedidoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Integer save(@RequestBody PedidoDto dto){
        Pedido pedido = pedidosServices.salvar(dto);
        return pedido.getId();
    }



}
