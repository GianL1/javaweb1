package br.com.dbserver.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dbserver.apirest.models.Product;

@Repository

public interface ProductRepository extends JpaRepository <Product, Long> {
}
