package br.com.eryck.sistema.model;


import br.com.eryck.sistema.model.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_pedidos")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
    @OneToMany(mappedBy = "pedido")
    private List<ItensPedido> itensPedidos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(getId(), pedido.getId()) && Objects.equals(getDataPedido(), pedido.getDataPedido()) && Objects.equals(getCliente(), pedido.getCliente()) && getStatus() == pedido.getStatus() && Objects.equals(getItensPedidos(), pedido.getItensPedidos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataPedido(), getCliente(), getStatus(), getItensPedidos());
    }

    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime dataPedido, Cliente cliente, StatusPedido status, List<ItensPedido> itensPedidos, BigDecimal total) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.status = status;
        this.itensPedidos = itensPedidos;
        //Total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
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

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    /*
    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }
    */

}
