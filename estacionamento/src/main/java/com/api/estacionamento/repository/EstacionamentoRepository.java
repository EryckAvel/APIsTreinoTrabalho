package com.api.estacionamento.repository;

import com.api.estacionamento.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, UUID> {
    boolean existsByPlacaCarro(String plcaCarro);
    boolean existsByNumeroVaga(String numeroVagas);
    boolean existsByApartamentoAndBloco(String apartamento, String bloco);
}
