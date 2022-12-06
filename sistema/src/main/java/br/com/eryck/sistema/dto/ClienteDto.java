package br.com.eryck.sistema.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteDto {

    @NotBlank
    private String nomeCompleto;
    @NotBlank
    @Size(max = 11)
    private String cpf;

    public ClienteDto(String nomeCompleto, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
