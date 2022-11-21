package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.dto.InformacaoItemPedidoDto;
import io.github.eryckavel.vendas.dto.InformacaoPedidoDto;
import io.github.eryckavel.vendas.dto.PedidoDto;
import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.model.ItemPedido;
import io.github.eryckavel.vendas.model.Pedido;
import io.github.eryckavel.vendas.repository.PedidoRepository;
import io.github.eryckavel.vendas.services.PedidosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidosServices pedidosServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Integer save(@RequestBody PedidoDto dto){
        Pedido pedido = pedidosServices.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacaoPedidoDto getByid(@PathVariable("id") Integer id){
        return pedidosServices
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

    private InformacaoPedidoDto converter(Pedido pedido){
        return InformacaoPedidoDto
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .itens(converter(pedido.getItens()))
                .build();

    }

    private List<InformacaoItemPedidoDto> converter(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(item -> InformacaoItemPedidoDto
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

}
