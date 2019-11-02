package br.com.dbserver.apirest.controllers;

import br.com.dbserver.apirest.models.Contact;
import br.com.dbserver.apirest.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/listar")
    public List<Contact> findAll(){
        return contactService.findAll();
    }

    @GetMapping(value="/busca/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        return contactService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Contact> create(@Valid @RequestBody Contact contact){
        Contact obj = contactService.save(contact);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Contact contact) {
        return contactService.findById(id)
                .map(record -> {
                    record.setName(contact.getName());
                    record.setEmail(contact.getEmail());
                    record.setPhone(contact.getPhone());
                    Contact updated = contactService.save(record);
                    return ResponseEntity.accepted().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return contactService.findById(id)
                .map(record -> {
                    contactService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
