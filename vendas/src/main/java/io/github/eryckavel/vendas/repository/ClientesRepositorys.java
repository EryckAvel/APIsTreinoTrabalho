package io.github.eryckavel.vendas.repository;

import io.github.eryckavel.vendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientesRepositorys extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(Integer id);
}