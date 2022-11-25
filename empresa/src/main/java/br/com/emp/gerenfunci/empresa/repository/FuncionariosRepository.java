package br.com.emp.gerenfunci.empresa.repository;

import br.com.emp.gerenfunci.empresa.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionariosRepository extends JpaRepository<Funcionario, Long> {



}
