package br.com.eryck.sistema.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_itens_pedido")
public class ItensPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Produto produto;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "valor_total", nullable = false, precision = 9, scale = 2)
    private BigDecimal totalItem;



}
