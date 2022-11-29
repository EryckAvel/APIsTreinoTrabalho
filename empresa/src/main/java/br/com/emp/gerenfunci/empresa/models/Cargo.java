package br.com.emp.gerenfunci.empresa.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_cargo", nullable = false, length = 80)
    private String nomeCargo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cargo cargo)) return false;
        return Objects.equals(
                getId(),
                cargo.getId()) && Objects.equals(getNomeCargo(),
                cargo.getNomeCargo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getNomeCargo());
    }

    public Cargo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
