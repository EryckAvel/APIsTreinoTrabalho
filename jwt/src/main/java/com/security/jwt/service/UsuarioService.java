package com.security.jwt.service;


import com.security.jwt.dto.UsuarioDto;
import com.security.jwt.model.Usuario;
import com.security.jwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Optional<Usuario> findyById(Long id) {
        return usuarioRepository.findById(id);
    }
}
