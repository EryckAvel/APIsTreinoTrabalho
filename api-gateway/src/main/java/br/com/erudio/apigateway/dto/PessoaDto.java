package br.com.erudio.apigateway.model;

import jakarta.persistence.*;

import javax.naming.Name;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "Pessoa")
public class Pessoa implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "primeiro_nome", nullable = false, length = 80)
    private String primeiroNome;
    @Column(name = "ultimo_nome", nullable = false, length = 80)
    private String ultimoNome;
    @Column(nullable = false, length = 100)
    private String endereco;
    @Column(nullable = false, length = 10)
    private String genero;

    public Pessoa(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(getId(), pessoa.getId()) && Objects.equals(getPrimeiroNome(), pessoa.getPrimeiroNome()) && Objects.equals(getUltimoNome(), pessoa.getUltimoNome()) && Objects.equals(getEndereco(), pessoa.getEndereco()) && Objects.equals(getGenero(), pessoa.getGenero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrimeiroNome(), getUltimoNome(), getEndereco(), getGenero());
    }
}
