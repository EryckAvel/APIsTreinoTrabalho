package io.github.eryckavel.vendas.services.impl;

import io.github.eryckavel.vendas.dto.ItemPedidoDto;
import io.github.eryckavel.vendas.dto.PedidoDto;
import io.github.eryckavel.vendas.exception.PedidoNaoEncontradoException;
import io.github.eryckavel.vendas.exception.RegraNegocioException;
import io.github.eryckavel.vendas.model.Cliente;
import io.github.eryckavel.vendas.model.ItemPedido;
import io.github.eryckavel.vendas.model.Pedido;
import io.github.eryckavel.vendas.model.Produto;
import io.github.eryckavel.vendas.model.enums.StatusPedido;
import io.github.eryckavel.vendas.repository.ClientesRepositorys;
import io.github.eryckavel.vendas.repository.ItemPedidoRepository;
import io.github.eryckavel.vendas.repository.PedidoRepository;
import io.github.eryckavel.vendas.repository.ProdutosRepository;
import io.github.eryckavel.vendas.services.PedidosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidosServices {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClientesRepositorys clientesRepositorys;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDto dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepositorys
                .findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException("Codigo de cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItens(pedido, dto.getItens());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {

        return pedidoRepository.findByIdFatchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    @Transactional
    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDto> itens){
        if (itens.isEmpty()){
            throw new RegraNegocioException("Não e possivel realizar um pedido sem itens!");
        }

        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(()-> new RegraNegocioException("Codigo de cliente invalido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
