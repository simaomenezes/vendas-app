package com.simaoneto.res.controller;

import com.simaoneto.domain.entity.Produto;
import com.simaoneto.domain.repository.Produtos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos/")
public class ProdutoController {

    private Produtos produtos;

    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto create(@RequestBody Produto produto){
        return produtos.save(produto);
    }

    @GetMapping
    public List<Produto> geAll(){
        return produtos.findAll();
    }
}
