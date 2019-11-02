package br.com.dbserver.apirest.services;

import br.com.dbserver.apirest.models.Categoria;
import br.com.dbserver.apirest.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria save(Categoria contact) {
        return categoriaRepository.save(contact);
    }

    public void deleteById(long id) {
        categoriaRepository.deleteById(id);
    }

    public void deleteAll() {
        categoriaRepository.deleteAll();
    }

}
