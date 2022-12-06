package br.com.eryck.sistema.repository;

import br.com.eryck.sistema.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
