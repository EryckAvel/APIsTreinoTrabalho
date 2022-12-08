package br.com.eryck.sistema.controller;

import br.com.eryck.sistema.dto.ProdutoDto;
import br.com.eryck.sistema.model.Produto;
import br.com.eryck.sistema.services.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProduto(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid ProdutoDto dto){
        var produto = new Produto();
        BeanUtils.copyProperties(dto, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> bucarProduto(@PathVariable(value = "id")Long id){
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if(!produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterarProduto(@PathVariable(value = "id") Long id, @RequestBody @Valid ProdutoDto dto){
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if (!produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        var produto = new Produto();
        BeanUtils.copyProperties(dto, produto);
        produto.setId(produtoOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable(value = "id") Long id){
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if (!produtoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produtoService.delete(produtoOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Produto deletado!");
    }
}
