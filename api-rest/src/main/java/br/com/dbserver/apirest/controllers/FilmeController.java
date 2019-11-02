package br.com.dbserver.apirest.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


import br.com.dbserver.apirest.models.Filme;
import br.com.dbserver.apirest.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;


    @GetMapping(value = "/listar")
    public List<Filme> findAll(){
        return filmeService.findAll();
    }

    @GetMapping(value="/busca/{id}")
    public ResponseEntity<Filme> findById(@PathVariable Long id){
        return filmeService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value="/create")
    public ResponseEntity<Filme> create(@Valid @RequestBody Filme filme){
        Filme obj = filmeService.save(filme);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Filme filme) {
        return filmeService.findById(id)
                .map(record -> {
                    record.setTitulo(filme.getTitulo());
                    record.setDiretor(filme.getDiretor());
                    record.setProtagonista(filme.getProtagonista());
                    Filme updated = filmeService.save(record);
                    return ResponseEntity.accepted().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return filmeService.findById(id)
                .map(record -> {
                    filmeService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

