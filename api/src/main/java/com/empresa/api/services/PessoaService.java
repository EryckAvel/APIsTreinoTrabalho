package com.empresa.api.services;

import com.empresa.api.model.Pessoa;
import com.empresa.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> fidById(Long id) {
        return pessoaRepository.findById(id);
    }

    public boolean existsByCpf(String cpf) {
        return pessoaRepository.existsByCpf(cpf);
    }

    public void delete(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }
}
