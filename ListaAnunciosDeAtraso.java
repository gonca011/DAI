package com.dai2324.prototipodai.Classes;

// Imports
import java.util.ArrayList;


// Dados Frota
public class ListaAnunciosDeAtraso implements java.io.Serializable {

    //Criar a lista de anuncios de atraso
    private ArrayList<AnunciosDeAtraso> anunciosDeAtrasos;

    //Construtor
    public ListaAnunciosDeAtraso() {
        this.anunciosDeAtrasos = new ArrayList<>();
    }

    //Adicionar e remover anuncios de atraso
    public void add(AnunciosDeAtraso anunciosDeAtraso) {
        this.anunciosDeAtrasos.add(anunciosDeAtraso);
    }

    public void remove(AnunciosDeAtraso anunciosDeAtraso) {
        this.anunciosDeAtrasos.remove(anunciosDeAtraso);
    }

    //Getters e Setters
    public ArrayList<AnunciosDeAtraso> getAnunciosDeAtrasos() {
        return anunciosDeAtrasos;
    }

    public void setAnunciosDeAtrasos(ArrayList<AnunciosDeAtraso> anunciosDeAtrasos) {
        this.anunciosDeAtrasos = anunciosDeAtrasos;
    }

    public AnunciosDeAtraso getAnunciosDeAtrasoById(String id) {
        for (AnunciosDeAtraso anunciosDeAtraso : anunciosDeAtrasos) {
            if (anunciosDeAtraso.getIdAtraso().equals(id)) {
                return anunciosDeAtraso;
            }
        }
        return null;
    }

    //toString
    @Override
    public String toString() {
        return "ListaAnunciosDeAtraso{" + "anunciosDeAtraso=" + anunciosDeAtrasos + '}';
    }


}
