package com.alura.dao;

import com.alura.model.Pedido;
import com.alura.model.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO() {
    }

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido){
        this.em.persist(pedido);
    }

    public Pedido buscarId(Long id){
        return em.find(Pedido.class, id);
    }

    public List<Pedido> listarProdutos(){
        String jpql = "SELECT p FROM Pedido p";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }

    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    /*
    public Pedido buscarNome(String nome){
        String jpql = "SELECT p FROM Pedido p WHERE p.nome = :nome";
        return em.createQuery(jpql, Pedido.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

     */

    /*
    public BigDecimal buscarPreco(String nome){
        String jpql = "SELECT p.preco FROM Pedido p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

     */
}
