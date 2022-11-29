package com.api.estacionamento.services;

import com.api.estacionamento.model.Estacionamento;
import com.api.estacionamento.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstacionamentoService {

    @Autowired
    EstacionamentoRepository estacionamentoRepository;

    @Transactional
    public Estacionamento save(Estacionamento estacionamento) {

        return estacionamentoRepository.save(estacionamento);
    }


    public boolean existsByPlacaCarro(String placaCarro) {
        return estacionamentoRepository.existsByPlacaCarro(placaCarro);
    }

    public boolean existsByNumeroVaga(String numeroVaga) {
        return estacionamentoRepository.existsByNumeroVaga(numeroVaga);
    }

    public boolean existsByApartamentoBloco(String apartamento, String bloco) {
        return estacionamentoRepository.existsByApartamentoAndBloco(apartamento, bloco);
    }

    public List<Estacionamento> findAll() {
        return estacionamentoRepository.findAll();
    }

    public Optional<Estacionamento> findById(UUID id) {
        return estacionamentoRepository.findById(id);
    }

    @Transactional
    public void delete(Estacionamento estacionamento) {
        estacionamentoRepository.delete(estacionamento);
    }
}
