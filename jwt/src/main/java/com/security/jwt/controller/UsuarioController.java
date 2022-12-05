package com.security.jwt.controller;

import com.security.jwt.dto.UsuarioDto;
import com.security.jwt.dto.UsuarioDtoInsert;
import com.security.jwt.model.Usuario;
import com.security.jwt.service.UsuarioService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listagemUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody @Valid UsuarioDto dto){
        var user = new Usuario();
        BeanUtils.copyProperties(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable("id") Integer id, @RequestBody @Valid UsuarioDto dto){
        Optional<Usuario> usuarioOptional = usuarioService.findyById(id);
        if (!usuarioOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        var user = new Usuario();
        BeanUtils.copyProperties(dto, user);
        user.setId(usuarioOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable("id") Integer id){
        Optional<Usuario> usuarioOptional = usuarioService.findyById(id);
        if (usuarioOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado!");
        }
        usuarioService.delete(usuarioOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario deletado");
    }

}
