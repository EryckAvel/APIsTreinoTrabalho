package com.api.estacionamento.controller;

import com.api.estacionamento.dto.EstacionamentoDto;
import com.api.estacionamento.model.Estacionamento;
import com.api.estacionamento.services.EstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    EstacionamentoService estacionamentoService;

    @PostMapping
    public ResponseEntity<Object> salvarEstacionamento(@RequestBody @Valid EstacionamentoDto estacionamentoDto){
        if (estacionamentoService.existsByPlacaCarro(estacionamentoDto.getPlacaCarro())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: placa de carro ja existente no sistema!");
        }
        if (estacionamentoService.existsByNumeroVaga(estacionamentoDto.getNumeroVaga())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de carro ja ocupada no sistema!");
        }
        if (estacionamentoService.existsByApartamentoBloco(estacionamentoDto.getApartamento(), estacionamentoDto.getBloco())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Apartamento e Bloco ja cadastrado com vaga no sistema!");
        }
        var estacionamento = new Estacionamento();
        BeanUtils.copyProperties(estacionamentoDto, estacionamento);
        estacionamento.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoService.save(estacionamento));
    }

    @GetMapping
    public ResponseEntity<List<Estacionamento>> getAllEstacionamento(){
        return ResponseEntity.status(HttpStatus.OK).body(estacionamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEstacionamento(@PathVariable(value = "id")UUID id){
        Optional<Estacionamento> estacionamentoOptional = estacionamentoService.findById(id);
        if (!estacionamentoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(estacionamentoOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstacionamento(@PathVariable(value = "id") UUID id){
        Optional<Estacionamento> estacionamentoOptional = estacionamentoService.findById(id);
        if (!estacionamentoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estacionamento não encontrado!");
        }
        estacionamentoService.delete(estacionamentoOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Estacionamento Liberado!");

    }

}
