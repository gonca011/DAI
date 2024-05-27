package com.dai2324.prototipodai.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Dados Recompensas
@Entity
@Table(name = "premio")
public class Premio {

    @Id
    @Column(name = "id", nullable = false, length = 40)
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "criteriodeatribuicao")
    private String criteriodeAtribuicao;

    public Premio(String nome, String descricao, String criteriodeAtribuicao, String id) {
        this.nome = nome;
        this.descricao = descricao;
        this.criteriodeAtribuicao = criteriodeAtribuicao;
        this.id = id;
    }

    public Premio() {
    }

    public int getPointsRequired() {
        return Integer.parseInt(this.criteriodeAtribuicao);
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

    public String getCriteriodeAtribuicao() {
        return criteriodeAtribuicao;
    }

    public void setCriteriodeAtribuicao(String criteriodeAtribuicao) {
        this.criteriodeAtribuicao = criteriodeAtribuicao;
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
        return "Premio{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", criteriodeAtribuicao='" + criteriodeAtribuicao + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}
