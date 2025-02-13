package com.dai2324.prototipodai.Classes;

import java.util.ArrayList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;

// Dados Viagem
@Entity
public class Percurso implements java.io.Serializable {

    // Atributos
    @Id
    @Column(name = "idpercurso")
    private String id;
    private String origem;
    private String destino;

    public Percurso() {
    }

    // Construtor
    public Percurso(String id, String origem, String destino) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
    }

    @ManyToMany(mappedBy = "percursos")
    private List<Paragem> paragens = new ArrayList<>();

    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    // toString
    @Override
    public String toString() {
        return "Percursos{id=" + id + ", origem=" + origem + ", destino=" + destino + "}";
    }

}
