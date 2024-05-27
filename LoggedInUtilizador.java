package com.dai2324.prototipodai.Classes;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Dados Utilizadores
@Entity
@Table(name = "logged_in_utilizadores")
public class LoggedInUtilizador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nif;
    private String nome;
    private String email;
    private String numdocidentificacao;
    private String datadenascimento;
    private String datavalidadedocidentificacao;
    private String codigopostal;
    private String morada;
    private String porta;
    private String telemovel;
    private int pontos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "loggedinutilizador_tipoperfil", 
        joinColumns = @JoinColumn(name = "loggedinutilizador_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "tipoperfil_id", referencedColumnName = "id")
    )
    private Set<TipoPerfil> tipoPerfis = new HashSet<>();

    public LoggedInUtilizador() {
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumdocidentificacao() {
        return numdocidentificacao;
    }

    public void setNumdocidentificacao(String numdocidentificacao) {
        this.numdocidentificacao = numdocidentificacao;
    }

    public String getDatadenascimento() {
        return datadenascimento;
    }

    public void setDatadenascimento(String datadenascimento) {
        this.datadenascimento = datadenascimento;
    }

    public String getDatavalidadedocidentificacao() {
        return datavalidadedocidentificacao;
    }

    public void setDatavalidadedocidentificacao(String datavalidadedocidentificacao) {
        this.datavalidadedocidentificacao = datavalidadedocidentificacao;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Set<TipoPerfil> getTipoPerfis() {
        return tipoPerfis;
    }

    public void setTipoPerfis(Set<TipoPerfil> tipoPerfis) {
        this.tipoPerfis = tipoPerfis;
    }

    public void addTipoPerfil(TipoPerfil tipoPerfil) {
        if (tipoPerfis == null) {
            tipoPerfis = new HashSet<>();
        }
        tipoPerfis.add(tipoPerfil);
    }

}