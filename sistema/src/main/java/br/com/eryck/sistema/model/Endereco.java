package br.com.eryck.sistema.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "CEP", nullable = false, length = 8)
    private String cep;
    @Column(name = "rua", nullable = false, length = 100)
    private String rua;
    @Column(name = "numero", nullable = false, length = 20)
    private String numero;
    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;
    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;
    @Column(name = "Complemento", nullable = true, length = 100)
    private String complemento;

    public Endereco() {
    }

    public Endereco(UUID id, String cep, String rua, String numero, String bairro, String cidade, String complemento) {
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
