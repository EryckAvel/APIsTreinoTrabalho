package br.com.eryck.sistema.controller;

import br.com.eryck.sistema.dto.PedidoDto;
import br.com.eryck.sistema.model.Pedido;
import br.com.eryck.sistema.services.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Pedido> salvarPedidos(PedidoDto dto){
        var pedido = new Pedido();
        BeanUtils.copyProperties(dto, pedido);
        pedido.setDataPedido(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));

    }

}
