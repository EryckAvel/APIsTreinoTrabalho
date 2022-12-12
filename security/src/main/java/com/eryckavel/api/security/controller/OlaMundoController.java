package com.eryckavel.api.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class OlaMundoController {

    @GetMapping
    public String hello() {
        return "Ola, essa e uma String retornada via API!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String user(){
        return "Usuario autorizado!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String admin(){
        return "Administrador autorizado!";
    }

}
