package br.com.eryck.sistema.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return Objects.equals(getId(), endereco.getId()) && Objects.equals(getCep(), endereco.getCep()) && Objects.equals(getRua(), endereco.getRua()) && Objects.equals(getNumero(), endereco.getNumero()) && Objects.equals(getBairro(), endereco.getBairro()) && Objects.equals(getCidade(), endereco.getCidade()) && Objects.equals(getComplemento(), endereco.getComplemento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCep(), getRua(), getNumero(), getBairro(), getCidade(), getComplemento());
    }

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
