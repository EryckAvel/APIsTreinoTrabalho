package br.com.emp.gerenfunci.empresa.dto;

import br.com.emp.gerenfunci.empresa.models.Funcionario;
import br.com.emp.gerenfunci.empresa.models.enums.Genero;
import br.com.emp.gerenfunci.empresa.models.enums.StatusSituacao;

import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioDto {

    private Long id;
    private String nomeCompleto;
    private Integer idade;
    private String cpf;
    private String email;
    private Genero genero;
    private String cargo;
    private Double salario;
    private StatusSituacao status;

    public FuncionarioDto(Funcionario funcionario){
        this.id = funcionario.getId();
        this.nomeCompleto = funcionario.getNomeCompleto();
        this.idade = funcionario.getIdade();
        this.cpf = funcionario.getCpf();
        this.email = funcionario.getEmail();
        this.genero = funcionario.getGenero();
        this.cargo = funcionario.getCargo().getNomeCargo();
        this.salario = funcionario.getSalario();
        this.status = funcionario.getStatus();
    }

    public static List<FuncionarioDto> converter(List<Funcionario> funcionario) {
        return funcionario.stream().map(FuncionarioDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getCargo() {
        return cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public StatusSituacao getStatus() {
        return status;
    }
}
