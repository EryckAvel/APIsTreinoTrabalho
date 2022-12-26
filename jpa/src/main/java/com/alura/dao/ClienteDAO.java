package com.alura.dao;

import com.alura.model.Categoria;
import com.alura.model.Cliente;
import com.alura.model.Produto;

import javax.persistence.EntityManager;

public class ClienteDAO {

    private EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente){
        this.em.persist(cliente);
    }

    public Cliente buscarId(Long id){
        return em.find(Cliente.class, id);
    }
}
