package io.github.eryckavel.vendas.repository;

import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
    @Query("Select p from Pedido p left join fetch p.itens where p.id =:id")
    Optional<Pedido> findByIdFatchItens(@PathVariable("id") Integer id);
}
