package com.dai2324.prototipodai.Classes;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Dados Viagens
@Entity
public class Paragem implements java.io.Serializable {

    private String nome;
    private String rua;

    @Id
    @Column(name = "nparagem")
    private String nParagem;

    @ManyToMany
    @JoinTable(name = "paragens_percursos",
            joinColumns = @JoinColumn(name = "nparagem"),
            inverseJoinColumns = @JoinColumn(name = "idpercurso")
    )
    private List<Percurso> percursos = new ArrayList<>();

    @Transient
    private ListaPercursos listaPercursos;

    public Paragem() {
    }

    // Construtor
    public Paragem(String nome, String rua, String nParagem, ListaPercursos listaPercursos) {
        this.nome = nome;
        this.rua = rua;
        this.nParagem = nParagem;
        this.listaPercursos = listaPercursos;
    }

    // Getters e Setters
    public void setListaPercursos(ListaPercursos listaPercursos) {
        this.listaPercursos = listaPercursos;
    }

    public String getNome() {
        return nome;
    }

    public String getRua() {
        return rua;
    }

    public String getnParagem() {
        return nParagem;
    }

    public ListaPercursos getListaPercursos() {
        return listaPercursos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setnParagem(String nParagem) {
        this.nParagem = nParagem;
    }

    public List<Percurso> getPercursos() {
        return percursos;
    }

    public void setPercursos(List<Percurso> percursos) {
        this.percursos = percursos;
    }

    // toString
    @Override
    public String toString() {
        return "Paragem{" +
                "nome='" + nome + '\'' +
                ", rua='" + rua + '\'' +
                ", nParagem='" + nParagem + '\'' +
                ", listaPercursos=" + listaPercursos +
                '}';
    }
}
