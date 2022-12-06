package br.com.eryck.sistema.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "nome_completo", nullable = false, length = 255)
    private String NomeCompleto;
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    public Cliente() {
    }

    public Cliente(UUID id, String nomeCompleto, String email, String cpf) {
        this.id = id;
        NomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return NomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        NomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
