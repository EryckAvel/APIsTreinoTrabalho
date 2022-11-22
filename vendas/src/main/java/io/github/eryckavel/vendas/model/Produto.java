package io.github.eryckavel.vendas.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;
    @Column(name = "{campo.preco.obrigatorio}")
    @NotNull(message = "Campo preço e obrigatorio")
    private BigDecimal preco;
}
