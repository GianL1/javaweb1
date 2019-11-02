package br.com.dbserver.apirest.services;

import br.com.dbserver.apirest.models.Filme;
import br.com.dbserver.apirest.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public Optional<Filme> findById(Long id) {
        return filmeRepository.findById(id);
    }

    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }

    public Filme save(Filme filme) {
        return filmeRepository.save(filme);
    }

    public void deleteById(long id) {
        filmeRepository.deleteById(id);
    }

    public void deleteAll() {
        filmeRepository.deleteAll();
    }
}
