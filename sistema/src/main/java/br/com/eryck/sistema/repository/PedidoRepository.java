package br.com.eryck.sistema.repository;

import br.com.eryck.sistema.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
