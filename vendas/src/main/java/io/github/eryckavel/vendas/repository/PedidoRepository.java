package io.github.eryckavel.vendas.repository;

import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
