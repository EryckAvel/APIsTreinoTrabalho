package br.com.eryck.sistema.model;

import br.com.eryck.sistema.model.enums.StatusVenda;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_vendas")
public class Vendas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToMany
    private Cliente cliente;
    @ManyToOne
    private Endereco endereco;
    @ManyToOne
    @JoinColumn(name = "clinte_id")
    private List<Pedido> pedido;
    @Enumerated(value = EnumType.STRING)
    private StatusVenda status;
    @Column(name = "data_venda")
    private Date dataVenda;
    @Column(name = "valor_total", nullable = false, precision = 9, scale = 2)
    private BigDecimal total;



    public Vendas() {
    }

    public Vendas(UUID id, Cliente cliente, Endereco endereco, List<Pedido> pedido, StatusVenda status, Date dataVenda, BigDecimal total) {
        this.id = id;
        this.cliente = cliente;
        this.endereco = endereco;
        this.pedido = pedido;
        this.status = status;
        this.dataVenda = dataVenda;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    public StatusVenda getStatus() {
        return status;
    }

    public void setStatus(StatusVenda status) {
        this.status = status;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
