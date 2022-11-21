package io.github.eryckavel.vendas.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDto> itens;

}
