package br.com.dbserver.apirest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Product {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @NotBlank(message = "Campo email é obrigatório")
    private String marca;

    private String preco;

    public Product() {
    }

    public Product(Long id, String name, String marca, String preco) {
        this.id = id;
        this.name = name;
        this.marca = marca;
        this.preco = preco;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPreco() {
        return preco;
    }

    public Long getId() {
        return id;
    }
}
