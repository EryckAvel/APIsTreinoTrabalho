package br.com.eryck.sistema.model;


import br.com.eryck.sistema.model.enums.StatusPedido;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "data_pedido")
    private Date dataPedido;
    @OneToOne
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
    @OneToMany
    private List<ItensPedido> itensProdutos;
    @Column(name = "total", nullable = false)
    private BigDecimal Total;

    public Pedido() {
    }

    public Pedido(UUID id, Date dataPedido, Cliente cliente, StatusPedido status, List<ItensPedido> itensProdutos, BigDecimal total) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.status = status;
        this.itensProdutos = itensProdutos;
        Total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
