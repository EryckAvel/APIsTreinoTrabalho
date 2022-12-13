package br.com.eryck.sistema.dto;

import br.com.eryck.sistema.model.Cliente;
import br.com.eryck.sistema.model.ItensPedido;
import br.com.eryck.sistema.model.enums.StatusPedido;

import java.util.List;

public class PedidoDto {

    private Cliente cliente;
    private StatusPedido status;
    private List<ItensPedido> itensPedidos;

    public PedidoDto() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
}
