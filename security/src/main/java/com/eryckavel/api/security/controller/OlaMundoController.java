package com.eryckavel.api.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OlaMundoController {

    @GetMapping("/hello")
    public String hello() {
        return "Ola, essa e uma String retornada via API!";
    }

    @GetMapping("/user")
    public String user(){
        return "Usuario autorizado!";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Administrador autorizado!";
    }

}
