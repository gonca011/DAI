package com.dai2324.prototipodai.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Dados de Pontos
@Entity
@Table(name = "codigodesconto")
public class CodigoDesconto {

    @Id
    @Column(name = "id", nullable = false, length = 40)
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "criteriodeatribuicao")
    private String criterioDeAtribuicao;

    // construtores

    public CodigoDesconto() {
    }

    public CodigoDesconto(String nome, String descricao, String criterioDeAtribuicao, String id) {
        this.nome = nome;
        this.descricao = descricao;
        this.criterioDeAtribuicao = criterioDeAtribuicao;
        this.id = id;
    }

    // geters e seters
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
        return "Codigo de Desconto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", criteriodeAtribuicao='" + criterioDeAtribuicao + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}
