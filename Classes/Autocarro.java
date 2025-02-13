package com.dai2324.prototipodai.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Dados Frota
@Entity
@Table(name = "autocarro")
public class Autocarro {

    @Id
    private String matricula;
    private int capacidade;
    private int numkm;

    public Autocarro() {
    }

    public Autocarro(String matricula, int capacidade, int numKm) {
        this.matricula = matricula;
        this.capacidade = capacidade;
        this.numkm = numKm;
    }

    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getNumKm() {
        return numkm;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setNumKm(int numKm) {
        this.numkm = numKm;
    }

    // toString
    @Override
    public String toString() {
        return "Autocarro{" + "matricula=" + matricula + ", capacidade=" + capacidade + ", numKm=" + numkm + '}';
    }

}
