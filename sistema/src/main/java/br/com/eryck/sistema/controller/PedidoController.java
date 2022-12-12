package br.com.eryck.sistema.controller;

import br.com.eryck.sistema.model.Pedido;
import br.com.eryck.sistema.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    public ResponseEntity<Object> salvarPedidos(){
        return null;
    }

}
