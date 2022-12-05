package com.security.jwt.repository;

import com.security.jwt.dto.UsuarioDto;
import com.security.jwt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNomeOrEmail(String nome, String email);
}
