package io.github.eryckavel.vendas.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    private Integer quantidade;
}
