package br.com.eryck.sistema.services;

import br.com.eryck.sistema.model.Produto;
import br.com.eryck.sistema.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> findById(UUID id) {
        return produtoRepository.findById(id);
    }
}
