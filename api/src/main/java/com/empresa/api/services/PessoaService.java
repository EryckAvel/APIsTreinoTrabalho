package com.empresa.api.services;

import com.empresa.api.model.Pessoa;
import com.empresa.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }
}
