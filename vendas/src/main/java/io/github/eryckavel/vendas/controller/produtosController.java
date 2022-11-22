package io.github.eryckavel.vendas.controller;

import io.github.eryckavel.vendas.model.Produto;
import io.github.eryckavel.vendas.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class produtosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping
    public List<Produto> listar(){
        return produtosRepository.findAll();
    }

    @GetMapping("/busca")
    public List<Produto> findProduto(Produto pfiltro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(pfiltro, matcher);
        return produtosRepository.findAll(example);
    }

    @GetMapping("/{id}")
    public Produto consultaProduto(@PathVariable("id") Integer id){
        return produtosRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return produtosRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void delete(@PathVariable("/id") Integer id){
        produtosRepository
                .findById(id)
                .map(produto -> {
                    produtosRepository.delete(produto);
                    return null;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void atualizar(@RequestBody Produto produto, @PathVariable("id") Integer id){
        produtosRepository
                .findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtosRepository.save(produto);
                    return produtoExistente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

}
