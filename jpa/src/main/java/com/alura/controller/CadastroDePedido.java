package com.alura.controller;

import com.alura.dao.CategoriaDAO;
import com.alura.dao.ClienteDAO;
import com.alura.dao.PedidoDAO;
import com.alura.dao.ProdutoDAO;
import com.alura.model.*;
import com.alura.util.JPAutil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {
        cadastrar();
        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        Produto produto = produtoDAO.buscarId(1l);
        Cliente cliente = clienteDAO.buscarId(1l);

        em.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adcionarItem(new ItensPedido(10, produto, pedido));

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido =  pedidoDAO.valorTotalVendido();
        System.out.println("Valor total:" + totalVendido);
    }

    public static void cadastrar(){
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("iphone", "telemovel", new BigDecimal("800"), celulares);
        Cliente cliente = new Cliente("rodrigo", "12345678912");

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDAO.cadastrar(cliente);
        em.getTransaction().commit();
        em.close();
    }

}
