package br.com.dbserver.apirest.repositories;

import br.com.dbserver.apirest.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
}
