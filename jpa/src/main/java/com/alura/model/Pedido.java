package com.alura.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedidos")
    private Long id;
    @Column(name = "data_geracao")
    private LocalDate dataGeracao = LocalDate.now();
    @ManyToOne
    private Cliente cliente;
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @Column(name = "itens")
    private List<ItensPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente){
        this.cliente = cliente;
    }

    public Pedido(Long id, LocalDate dataGeracao, Cliente cliente, BigDecimal valorTotal) {
        this.id = id;
        this.dataGeracao = dataGeracao;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
    }

    public void adcionarItem(ItensPedido item){

        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDate dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
