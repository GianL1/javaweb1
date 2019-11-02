package br.com.dbserver.apirest.controllers;

import br.com.dbserver.apirest.models.Categoria;
import br.com.dbserver.apirest.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping(value = "/create")
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria){
        Categoria obj = categoriaService.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping(value="/listar")
    public List<Categoria> findAll(){
        return categoriaService.findAll();
    }

    @GetMapping(value="/busca/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        return categoriaService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping(value="/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Categoria categoria) {
        return categoriaService.findById(id)
                .map(record -> {
                    record.setName(categoria.getName());
                    record.setAtribuicoes(categoria.getAtribuicoes());
                    Categoria updated = categoriaService.save(record);
                    return ResponseEntity.accepted().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return categoriaService.findById(id)
                .map(record -> {
                    categoriaService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}