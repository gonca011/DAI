package com.dai2324.prototipodai.Classes;

import java.util.ArrayList;


// Dados Frota
public class ListaAutocarros {

    // Criar a lista de autocarros
    private ArrayList<Autocarro> listaAutocarros;

    // Construtor
    public ListaAutocarros() {
        this.listaAutocarros = new ArrayList<>();
    }

    // Adicionar e remover autocarros
    public void adicionarAutocarro(Autocarro a) {
        listaAutocarros.add(a);
    }

    public void removeAutocarro(Autocarro a) {
        listaAutocarros.remove(a);
    }

    // Getters e Setters
    public ArrayList<Autocarro> getListaAutocarros() {
        return listaAutocarros;
    }

    public void setListaAutocarros(ArrayList<Autocarro> listaAutocarros) {
        this.listaAutocarros = listaAutocarros;
    }

    public Autocarro getAutocarroPorMatricula(String matricula) {
        for (Autocarro a : listaAutocarros) {
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    // toString
    @Override
    public String toString() {
        return "ListaAutocarros{" + "listaAutocarros=" + listaAutocarros + '}';
    }

}
