package com.eryckavel.api.relacional.controller;

import com.eryckavel.api.relacional.model.Usuario;
import com.eryckavel.api.relacional.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    //Forma simples de fazer um findById

    /*
    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> listarUsuarios(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id).get());
    }
    **/

    //Forma melhorada de como se fazer um findById

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> listarUsuario(@PathVariable(value = "id") Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (!usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
    }

    @PostMapping
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

}
