package br.com.eryck.sistema.services;

import br.com.eryck.sistema.model.Pedido;
import br.com.eryck.sistema.repository.ItensPedidoRepsitory;
import br.com.eryck.sistema.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ItensPedidoRepsitory itensPedidoRepsitory;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
