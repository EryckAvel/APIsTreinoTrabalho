package com.empresa.api.controller;

import com.empresa.api.dto.RoleDto;
import com.empresa.api.model.Role;
import com.empresa.api.services.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> listasRoles(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> salvarRole(@RequestBody @Valid RoleDto roleDto){
        var role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
    }

}
