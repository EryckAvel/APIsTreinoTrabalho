package models;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import models.enums.Cargo;
import models.enums.Genero;
import models.enums.StatusSituação;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_completo",nullable = false, length = 100)
    private String nomeCompleto;
    @Column(nullable = false, length = 100)
    private Integer idade;
    @CPF
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(nullable = false, length = 80)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cargo cargo;
    @Column(length = 9, precision = 2, nullable = false)
    private Double salario;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSituação status;

    public Funcionario() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario that)) return false;
        return Objects.equals(
                getId(),
                that.getId()) && Objects.equals(getNomeCompleto(),
                that.getNomeCompleto()) && Objects.equals(getIdade(),
                that.getIdade()) && Objects.equals(getCpf(),
                that.getCpf()) && Objects.equals(getEmail(),
                that.getEmail()) && getGenero() == that.getGenero() && getCargo() == that.getCargo() && Objects.equals(getSalario(),
                that.getSalario()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getNomeCompleto(),
                getIdade(),
                getCpf(),
                getEmail(),
                getGenero(),
                getCargo(),
                getSalario(),
                getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public StatusSituação getStatus() {
        return status;
    }

    public void setStatus(StatusSituação status) {
        this.status = status;
    }
}
