package br.com.eryck.sistema.model;


import br.com.eryck.sistema.model.enuns.StatusPedido;
import enuns.StatusPedido;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "data_pedido")
    private Date dataPedido;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<ItensPedido> itensProdutos;
    @Column(name = "total", nullable = false)
    private BigDecimal Total;

}
