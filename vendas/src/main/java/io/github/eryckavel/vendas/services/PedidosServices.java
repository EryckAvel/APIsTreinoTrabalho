package io.github.eryckavel.vendas.services;

import io.github.eryckavel.vendas.dto.PedidoDto;
import io.github.eryckavel.vendas.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface PedidosServices {

    Pedido salvar(PedidoDto dto);

}
