package com.dai2324.prototipodai.Classes;


import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

// Dados Utilizador
@Entity
public class Utilizador implements java.io.Serializable {

    public Utilizador() {
    }

    private String nif;

    // Atributos
    private String nome;
    private String email;

    @Id
    @Column(name = "numdocidentificacao")
    private String numdocIdentificacao;

    @Column(name = "datadenascimento")
    private String dataDeNascimento;

    @Column(name = "datavalidadedocidentificacao")
    private String dataValidadeDocIdentificacao;

    @Column(name = "codigopostal")
    private String codigoPostal;

    private String morada;
    private String porta;
    private String telemovel;
    private int pontos;

    @ManyToMany
    @JoinTable(name = "utilizador_tipoperfil", joinColumns = @JoinColumn(name = "utilizador_ndoc", referencedColumnName = "numdocidentificacao"), inverseJoinColumns = @JoinColumn(name = "tipoperfil_id", referencedColumnName = "id"))
    private Set<TipoPerfil> tipoPerfis = new HashSet<>();

    public Set<TipoPerfil> getTipoPerfis() {
        return tipoPerfis;
    }

    @ManyToMany
    @JoinTable(name = "utilizador_premio", joinColumns = @JoinColumn(name = "utilizador_ndoc", referencedColumnName = "numdocidentificacao"), inverseJoinColumns = @JoinColumn(name = "premio_id", referencedColumnName = "id"))
    private Set<Premio> premios = new HashSet<>();

    public Set<Premio> getPremios() {
        return premios;
    }

    public void redeemPremio(Premio premio) {

        if (this.pontos >= premio.getPointsRequired()) {
            this.pontos -= premio.getPointsRequired();
            this.premios.add(premio);
        } else {
            System.out.println("Pontos insuficientes");
        }
    }

    @ManyToMany
    @JoinTable(name = "utilizador_promocao", joinColumns = @JoinColumn(name = "utilizador_ndoc", referencedColumnName = "numdocidentificacao"), inverseJoinColumns = @JoinColumn(name = "promocao_id", referencedColumnName = "id"))
    private Set<Promocao> promocaos = new HashSet<>();

    public Set<Promocao> getPromocaos() {
        return promocaos;
    }

    public void redeemPromocao(Promocao promocao) {
        if (this.pontos >= promocao.getPointsRequired()) {
            this.pontos -= promocao.getPointsRequired();
            this.promocaos.add(promocao);
        } else {
            System.out.println("Pontos Insuficientes");
        }
    }

    public void setTipoPerfis(Set<TipoPerfil> tipoPerfis) {
        this.tipoPerfis = tipoPerfis;
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

    public String getNumdocIdentificacao() {
        return numdocIdentificacao;
    }

    public void setNumdocIdentificacao(String numdocIdentificacao) {
        this.numdocIdentificacao = numdocIdentificacao;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDataValidadeDocIdentificacao() {
        return dataValidadeDocIdentificacao;
    }

    public void setDataValidadeDocIdentificacao(String dataValidadeDocIdentificacao) {
        this.dataValidadeDocIdentificacao = dataValidadeDocIdentificacao;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
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

    public void addTipoPerfil(TipoPerfil tipoPerfil) {
        if (tipoPerfis == null) {
            tipoPerfis = new HashSet<>();
        }
        tipoPerfis.add(tipoPerfil);
    }

    // Criar Utilizador
    public Utilizador createUser() {
        Utilizador newUser = new Utilizador();
        newUser.setNome(nome);
        newUser.setEmail(email);
        //newUser.setListaEmails(listaEmails);
        newUser.setNumdocIdentificacao(numdocIdentificacao);
        newUser.setDataDeNascimento(dataDeNascimento);
        newUser.setNif(nif);
        newUser.setDataValidadeDocIdentificacao(dataValidadeDocIdentificacao);
        newUser.setCodigoPostal(codigoPostal);
        newUser.setMorada(morada);
        newUser.setPorta(porta);
        newUser.setTelemovel(telemovel);
        newUser.setPontos(0);
        return newUser;
    }

    @Override
    public String toString() {
       // String emails = String.join("; ", //listaEmails);
        return "Utilizador{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", numdocIdentificacao='" + numdocIdentificacao + '\'' +
                //", listaEmails=" + emails +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", nif='" + nif + '\'' +
                ", dataValidadeDocIdentificacao='" + dataValidadeDocIdentificacao + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", morada='" + morada + '\'' +
                ", porta='" + porta + '\'' +
                ", telemovel='" + telemovel + '\'' +
                ", pontos='" + pontos + '\'' +
                '}';
    }


    public void removeTipoPerfil(TipoPerfil tipoPerfil) {
        this.tipoPerfis.remove(tipoPerfil);
    }
}
