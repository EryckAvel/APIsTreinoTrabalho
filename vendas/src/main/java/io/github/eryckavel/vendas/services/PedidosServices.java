package io.github.eryckavel.vendas.services;

import io.github.eryckavel.vendas.dto.PedidoDto;
import io.github.eryckavel.vendas.model.Pedido;

import java.util.Optional;


public interface PedidosServices {

    Pedido salvar(PedidoDto dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
