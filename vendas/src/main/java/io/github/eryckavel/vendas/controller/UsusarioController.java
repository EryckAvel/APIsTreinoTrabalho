package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.config.jwt.JwtService;
import io.github.eryckavel.vendas.dto.CredenciaisDto;
import io.github.eryckavel.vendas.dto.TokenDto;
import io.github.eryckavel.vendas.exception.RegraNegocioException;
import io.github.eryckavel.vendas.exception.SenhaInvalidaException;
import io.github.eryckavel.vendas.model.Usuario;
import io.github.eryckavel.vendas.services.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsusarioController {

    @Autowired
    JwtService jwtService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        String senhaCritpografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCritpografada);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PostMapping("/auth")
    public TokenDto usuarioAutenticar(@RequestBody CredenciaisDto credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDto(usuario.getLogin(), token);
        }catch (UsernameNotFoundException|SenhaInvalidaException e){
            throw new ResponseStatusException( HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
