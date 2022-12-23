package com.alura.controller;

import com.alura.dao.CategoriaDAO;
import com.alura.dao.ProdutoDAO;
import com.alura.model.Produto;
import com.alura.model.Categoria;
import com.alura.util.JPAutil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {

    public static void main(String[] args) {
        cadastrar();
        Long id = 1l;
        String nome = "iphone";
        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        Produto p = produtoDAO.buscarId(id);
        System.out.println(p.getPreco());
        List<Produto> lista = produtoDAO.listarProdutos();
        lista.forEach(produto -> {
            System.out.println(produto.getCategoria().getNome());
        });
        Produto p2 = produtoDAO.buscarNome(nome);
        System.out.println(p2.getPreco());
        BigDecimal pd = produtoDAO.buscarPreco(nome);
        System.out.println(pd);
    }

    public static void cadastrar(){
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("iphone", "telemovel", new BigDecimal("800"), celulares);

        EntityManager em = JPAutil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

}
