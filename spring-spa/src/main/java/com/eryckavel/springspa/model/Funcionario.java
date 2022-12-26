package com.eryckavel.springspa.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private BigDecimal salario;
    @Column(name = "data_contracao")
    private LocalDateTime dataContratacao;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cargo cargo;
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidade", joinColumns = {
            @JoinColumn(name = "fk_funcionario")
    }
    ,inverseJoinColumns = {
            @JoinColumn(name = "fk_unidade")
    })
    private UnidadeTrabalho unidadeTrabalho;

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                ", cargo=" + cargo +
                ", unidadeTrabalho=" + unidadeTrabalho +
                '}';
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public UnidadeTrabalho getUnidadeTrabalho() {
        return unidadeTrabalho;
    }

    public void setUnidadeTrabalho(UnidadeTrabalho unidadeTrabalho) {
        this.unidadeTrabalho = unidadeTrabalho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDateTime getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDateTime dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
}
