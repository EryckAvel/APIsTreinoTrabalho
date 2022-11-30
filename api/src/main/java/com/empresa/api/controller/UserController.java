package com.empresa.api.controller;

import com.empresa.api.dto.UserDto;
import com.empresa.api.model.User;
import com.empresa.api.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> salvandoUsuario(@RequestBody @Valid UserDto userDto){
        var user = new User();
        BeanUtils.copyProperties(userDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

}
