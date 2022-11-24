package br.com.erudio.apigateway.dto;


import java.io.Serializable;
import java.util.Objects;



public class PessoaDto implements Serializable {

    private Long id;

    private String primeiroNome;

    private String ultimoNome;

    private String endereco;

    private String genero;

    public PessoaDto(){}

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
        if (!(o instanceof PessoaDto pessoa)) return false;
        return Objects.equals(getId(), pessoa.getId()) && Objects.equals(getPrimeiroNome(), pessoa.getPrimeiroNome()) && Objects.equals(getUltimoNome(), pessoa.getUltimoNome()) && Objects.equals(getEndereco(), pessoa.getEndereco()) && Objects.equals(getGenero(), pessoa.getGenero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrimeiroNome(), getUltimoNome(), getEndereco(), getGenero());
    }
}
