package br.com.eryck.sistema.repository;

import br.com.eryck.sistema.model.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItensPedidoRepsitory extends JpaRepository<ItensPedido, Long> {
}
