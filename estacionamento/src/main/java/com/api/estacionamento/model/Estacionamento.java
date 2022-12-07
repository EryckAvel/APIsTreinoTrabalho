package com.api.estacionamento.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_estacionamento")
public class Estacionamento{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "numero_vaga", unique = true, length = 10, nullable = false)
    private String numeroVaga;
    @Column(name = "placa_carro", unique = true, length = 7, nullable = false)
    private String placaCarro;
    @Column(name = "marca_carro", length = 70, nullable = false)
    private String marcaCarro;
    @Column(name = "modelo_carro", length = 70, nullable = false)
    private String modeloCarro;
    @Column(name = "cor_carro", length = 70, nullable = false)
    private String corCarro;
    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;
    @Column(name = "nome_responsavel", length = 130, nullable = false)
    private String nomeResponsavel;
    @Column(name = "apartamento", length =30, nullable = false )
    private String apartamento;
    @Column(name = "bloco", length = 30, nullable = false)
    private String bloco;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(String numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getCorCarro() {
        return corCarro;
    }

    public void setCorCarro(String corCarro) {
        this.corCarro = corCarro;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }
}
