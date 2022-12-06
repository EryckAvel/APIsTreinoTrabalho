package br.com.eryck.sistema.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_itens_pedido")
public class ItensPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "valor_total", nullable = false, precision = 9, scale = 2)
    private BigDecimal totalItem;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItensPedido that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getProduto(), that.getProduto()) && Objects.equals(getQuantidade(), that.getQuantidade()) && Objects.equals(getTotalItem(), that.getTotalItem()) && Objects.equals(getPedido(), that.getPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduto(), getQuantidade(), getTotalItem(), getPedido());
    }

    public ItensPedido() {
    }

    public ItensPedido(Long id, Produto produto, Integer quantidade, BigDecimal totalItem, Pedido pedido) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.totalItem = totalItem;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(BigDecimal totalItem) {
        this.totalItem = totalItem;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
