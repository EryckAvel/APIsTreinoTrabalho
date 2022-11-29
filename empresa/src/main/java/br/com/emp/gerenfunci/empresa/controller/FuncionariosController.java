package br.com.emp.gerenfunci.empresa.controller;

import br.com.emp.gerenfunci.empresa.dto.FuncionarioDto;
import br.com.emp.gerenfunci.empresa.models.Funcionario;
import br.com.emp.gerenfunci.empresa.repository.CargoRepository;
import br.com.emp.gerenfunci.empresa.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosController {

    @Autowired
    FuncionariosRepository funcionariosRepository;

    @Autowired
    CargoRepository cargoRepository;

    @GetMapping
    public List<FuncionarioDto> listaAll(){
        List<Funcionario> funcionario = funcionariosRepository.findAll();
        return FuncionarioDto.converter(funcionario);
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscaFiltrada(@PathVariable Long id){
        return funcionariosRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario save(@RequestBody @Valid Funcionario funcionario){
        return funcionariosRepository.save(funcionario);
    }

}
