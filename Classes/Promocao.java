package com.dai2324.prototipodai.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

// Dados Recompensas
@Entity
@Table(name = "promocao")
public class Promocao {

    // Atributos
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "criteriodeatribuicao")
    private String criterioDeAtribuicao;

    public Promocao(){
        
    }

    // Construtor
    public Promocao(String nome, String descricao, String criterioDeAtribuicao, String id) {
        this.nome = nome;
        this.descricao = descricao;
        this.criterioDeAtribuicao = criterioDeAtribuicao;
        this.id = id;
    }

    public int getPointsRequired() {
        return Integer.parseInt(this.criterioDeAtribuicao);
    }


    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriterioDeAtribuicao() {
        return criterioDeAtribuicao;
    }

    public void setCriterioDeAtribuicao(String criterioDeAtribuicao) {
        this.criterioDeAtribuicao = criterioDeAtribuicao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // toString
    @Override
    public String toString() {
        return "{" + nome + ", " + descricao + ", " + criterioDeAtribuicao + ", " + id + "}";
    }

}
