package io.github.eryckavel.vendas.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getId().equals(cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    public Cliente(Integer id, String nome) {
        this.nome = nome;
    }
    public Cliente(){}
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return "Cliente {" + "id=" + id + ", nome="  + nome + '\'' + '}';
    }
}
