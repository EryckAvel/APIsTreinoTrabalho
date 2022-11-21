package io.github.eryckavel.vendas.repository;

import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
    Optional<Pedido> findByIdFatchItens(Integer id);
}
