package br.com.eryck.sistema.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome_produto", nullable = false, length = 255)
    private String nome;
    @Column(name = "categoria", nullable = false, length = 255)
    private String categoria;
    @Column(name = "qtd_estoque", nullable = false)
    private Integer estoque;
    @Column(name = "valor_unitario", nullable = false, precision = 9, scale = 2)
    private BigDecimal valorUnitario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(getId(), produto.getId()) && Objects.equals(getNome(), produto.getNome()) && Objects.equals(getCategoria(), produto.getCategoria()) && Objects.equals(getEstoque(), produto.getEstoque()) && Objects.equals(getValorUnitario(), produto.getValorUnitario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCategoria(), getEstoque(), getValorUnitario());
    }

    public Produto() {
    }

    public Produto(Long id, String nome, String categoria, Integer estoque, BigDecimal valorUnitario) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.estoque = estoque;
        this.valorUnitario = valorUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
