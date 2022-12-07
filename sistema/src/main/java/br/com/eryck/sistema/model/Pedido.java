package br.com.eryck.sistema.model;


import br.com.eryck.sistema.model.enums.StatusPedido;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "data_pedido")
    private Date dataPedido;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
    @OneToMany(mappedBy = "pedido")
    private List<ItensPedido> itensProdutos;
    @Column(name = "total", nullable = false)
    private BigDecimal Total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(getId(), pedido.getId()) && Objects.equals(getDataPedido(), pedido.getDataPedido()) && Objects.equals(getCliente(), pedido.getCliente()) && Objects.equals(getEndereco(), pedido.getEndereco()) && getStatus() == pedido.getStatus() && Objects.equals(getItensProdutos(), pedido.getItensProdutos()) && Objects.equals(getTotal(), pedido.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataPedido(), getCliente(), getEndereco(), getStatus(), getItensProdutos(), getTotal());
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pedido() {
    }

    public Pedido(Long id, Date dataPedido, Cliente cliente, StatusPedido status, List<ItensPedido> itensProdutos, BigDecimal total) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.status = status;
        this.itensProdutos = itensProdutos;
        Total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItensPedido> getItensProdutos() {
        return itensProdutos;
    }

    public void setItensProdutos(List<ItensPedido> itensProdutos) {
        this.itensProdutos = itensProdutos;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }
}
