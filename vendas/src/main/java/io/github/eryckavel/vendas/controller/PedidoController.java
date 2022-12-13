package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.dto.AtualizacaoStatusPedidoDto;
import io.github.eryckavel.vendas.dto.InformacaoItemPedidoDto;
import io.github.eryckavel.vendas.dto.InformacaoPedidoDto;
import io.github.eryckavel.vendas.dto.PedidoDto;
import io.github.eryckavel.vendas.model.ItemPedido;
import io.github.eryckavel.vendas.model.Pedido;
import io.github.eryckavel.vendas.model.enums.StatusPedido;
import io.github.eryckavel.vendas.services.PedidosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
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
    public  Integer save(@RequestBody @Valid PedidoDto dto){
        Pedido pedido = pedidosServices.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacaoPedidoDto getByid(@PathVariable("id") Integer id){
        return pedidosServices
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  updateStatus(@PathVariable("id") Integer id, @RequestBody AtualizacaoStatusPedidoDto dto){
        String novoStatus = dto.getNovoStatus();
        pedidosServices.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacaoPedidoDto converter(Pedido pedido){
        return InformacaoPedidoDto
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status((pedido.getStatus().name()))
                .itens(converterItens(pedido.getItens()))
                .build();

    }

    private List<InformacaoItemPedidoDto> converterItens(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(item -> InformacaoItemPedidoDto
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPrecoUnitario())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }

}
