package com.api.estacionamento.controller;

import com.api.estacionamento.dto.UserDto;
import com.api.estacionamento.model.UserModel;
import com.api.estacionamento.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping()
    public ResponseEntity<List<UserModel>> listagemDeUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> salvarUsuario(@RequestBody @Valid UserDto userDto){
        var user = new UserModel();
        BeanUtils.copyProperties(userDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(userServices.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id")UUID id){
        Optional<UserModel> userOptional = userServices.findById(id);
        if(!userOptional.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado!");
        }
        userServices.delete(userOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario deletado");
    }
}
