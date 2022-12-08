package br.com.eryck.sistema.repository;

import br.com.eryck.sistema.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
