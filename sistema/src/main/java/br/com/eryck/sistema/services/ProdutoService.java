package br.com.eryck.sistema.services;

import br.com.eryck.sistema.model.Produto;
import br.com.eryck.sistema.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }
}
