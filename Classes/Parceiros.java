package com.dai2324.prototipodai.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Dados Parceiros
@Entity
@Table(name = "parceiros")
public class Parceiros implements java.io.Serializable {

    // Atributos
    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Id
    @Column(name = "id")
    private String id;

    // Construtor
    public Parceiros(String nome, String descricao, String id) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
    }

    public Parceiros() {
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(String id) {
        this.id = id;
    }
}