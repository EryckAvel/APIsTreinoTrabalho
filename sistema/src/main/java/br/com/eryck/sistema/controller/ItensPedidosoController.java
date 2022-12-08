package br.com.eryck.sistema.controller;

import br.com.eryck.sistema.services.ItensPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itensPedidos")
public class ItensPedidosoController {

    @Autowired
    ItensPedidoService itensPedidoService;



}
