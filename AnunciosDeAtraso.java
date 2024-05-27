package com.dai2324.prototipodai.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Dados Frota
@Entity
@Table(name = "anunciosdeatraso")
public class AnunciosDeAtraso {


    public AnunciosDeAtraso() {
    }
    // Atributos
    @Column(name = "tempoatraso")
    private String tempoAtraso;

    @Column(name = "motivoatraso")
    private String motivoAtraso;

    @Id
    @Column(name = "idatraso")
    private String idAtraso;

    // Construtor
    public AnunciosDeAtraso(String tempoAtraso, String motivoAtraso, String idAtraso) {
        this.tempoAtraso = tempoAtraso;
        this.motivoAtraso = motivoAtraso;
        this.idAtraso = idAtraso;
    }

    // Getters e Setters
    public String getTempoAtraso() {
        return tempoAtraso;
    }

    public String getMotivoAtraso() {
        return motivoAtraso;
    }

    public void setTempoAtraso(String tempoAtraso) {
        this.tempoAtraso = tempoAtraso;
    }

    public void setMotivoAtraso(String motivoAtraso) {
        this.motivoAtraso = motivoAtraso;
    }

    public String getIdAtraso() {
        return idAtraso;
    }

    public void setIdAtraso(String idAtraso) {
        this.idAtraso = idAtraso;
    }

    // toString
    @Override
    public String toString() {
        return "Anuncios de Atraso{tempo atraso=" + tempoAtraso + ", motivo=" + motivoAtraso + ", id=" + idAtraso + "}";
    }
}