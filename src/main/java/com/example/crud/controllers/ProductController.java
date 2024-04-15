package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.repositories.ProductRepository;
import com.example.crud.domain.product.ProdutoDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducts = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid ProdutoDTO dados){
        Product newProduct = new Product(dados);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterProduct(@RequestBody @Valid ProdutoDTO dados){
        Optional<Product> optionalProduct = repository.findById(dados.id());
        // o tipo optional pode ou não conter algo
        if(optionalProduct.isPresent()){// por isso é necessário verificar se algo retornou
            Product p = optionalProduct.get();//passando o optional para um produto para conseguir acessar os métodos set
            p.setName(dados.name());
            p.setPrice_in_cents(dados.price_in_cents());
            return ResponseEntity.ok().build();
        }else
            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent()){
            Product p = optionalProduct.get();
            p.setActive(false);
            return ResponseEntity.noContent().build();
        }else{
            throw new EntityNotFoundException();
        }
    }

}
