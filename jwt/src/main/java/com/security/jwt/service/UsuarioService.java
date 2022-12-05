package com.security.jwt.service;


import com.security.jwt.config.jwt.TokenUtil;
import com.security.jwt.dto.UsuarioDto;
import com.security.jwt.model.Token;
import com.security.jwt.model.Usuario;
import com.security.jwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario user) {
        String encoder = passwordEncoder.encode(user.getSenha());
        user.setSenha(encoder);
        return usuarioRepository.save(user);
    }

    public Optional<Usuario> findyById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Boolean validarSenha(Usuario usuario) {
        String senha = usuarioRepository.getById(usuario.getId()).getSenha();
        return passwordEncoder.matches(usuario.getSenha(), senha);
    }

    public Token gerarToken(UsuarioDto dto) {
        Usuario user = usuarioRepository.findByNomeOrEmail(dto.getNome(), dto.getEmail());
        if(user != null){
            Boolean valid = passwordEncoder.matches(dto.getSenha(), user.getSenha());
            if(valid){
                return new Token(TokenUtil.criarToken(user));
            }
        }
        return null;
    }
}
