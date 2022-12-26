package com.eryckavel.springspa.repository;

import com.eryckavel.springspa.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
}
