package br.com.dbserver.apirest.controllers;


import br.com.dbserver.apirest.models.Categoria;
import br.com.dbserver.apirest.models.Product;
import br.com.dbserver.apirest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/create")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product){
        Product obj = productService.save(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping(value = "/listar")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping(value="/busca/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return productService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping(value="/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Product product) {
        return productService.findById(id)
                .map(record -> {
                    record.setName(product.getName());
                    record.setMarca(product.getMarca());
                    Product updated = productService.save(record);
                    return ResponseEntity.accepted().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return productService.findById(id)
                .map(record -> {
                    productService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
