package com.api.estacionamento.services;

import com.api.estacionamento.model.Estacionamento;
import com.api.estacionamento.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstacionamentoService {

    @Autowired
    EstacionamentoRepository estacionamentoRepository;

    @Transactional
    public Estacionamento save(Estacionamento estacionamento) {
        return estacionamentoRepository.save(estacionamento);
    }


    public boolean existsByPlacaCarro(String placaCarro) {
    }

    public boolean existsByNumeroVaga(String numeroVaga) {
    }

    public boolean existsByApartamentoBloco(String apartamento, String bloco) {
    }
}
