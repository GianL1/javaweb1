package br.com.dbserver.apirest.models;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Filme {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo titulo é obrigatório")
    private String titulo;

    @NotBlank(message = "Campo diretor é obrigatório")
    private String diretor;

    private String protagonista;

    public Filme() {
    }

    public Filme(Long id, String titulo, String diretor, String protagonista) {
        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.protagonista = protagonista;
    }

    public Long getId() {
        return id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }
}
