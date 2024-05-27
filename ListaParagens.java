package com.dai2324.prototipodai.Classes;

import java.util.ArrayList;

// Dados Viagens
public class ListaParagens {

    // Criar a lista de paragens
    private ArrayList<Paragem> listaParagens;

    // Construtor
    public ListaParagens() {
        this.listaParagens = new ArrayList<>();
    }

    // Adicionar e remover paragens
    public void addParagem(Paragem paragem) {
        listaParagens.add(paragem);
    }

    public void removeParagem(Paragem paragem) {
        listaParagens.remove(paragem);
    }

    // Getters e Setters
    public ArrayList<Paragem> getListaParagens() {
        return listaParagens;
    }

    public void setListaParagens(ArrayList<Paragem> listaParagens) {
        this.listaParagens = listaParagens;
    }

    public Paragem getParagemPorNParagem(String nParagem) {
        for (Paragem paragem : listaParagens) {
            if (paragem.getnParagem().equals(nParagem)) {
                return paragem;
            }
        }
        return null;
    }

    public Paragem getParagemPorNome(String nome) {
        for (Paragem paragem : listaParagens) {
            if (paragem.getNome().equals(nome)) {
                return paragem;
            }
        }
        return null;
    }

    // toString
    @Override
    public String toString() {
        return "ListaParagens{" +
                "listaParagens=" + listaParagens +
                '}';
    }

    // Verificar se a paragem já existe
    public boolean contains(Paragem paragem) {
        for (Paragem p : listaParagens) {
            if (p.getnParagem().equals(paragem.getnParagem())) {
                return true;
            }
        }
        return false;
    }
}
