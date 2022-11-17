package io.github.eryckavel.vendas.repository;

import io.github.eryckavel.vendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
