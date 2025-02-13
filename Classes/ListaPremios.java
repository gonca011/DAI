package com.dai2324.prototipodai.Classes;

import java.util.ArrayList;

// Dados Recompensas
public class ListaPremios {

    // Criar a lista de premios
    private ArrayList<Premio> listaPremios;

    // Construtor
    public ListaPremios() {
        this.listaPremios = new ArrayList<>();
    }

    // Adicionar e remover premios
    public void adicionarPremio(Premio p) {
        listaPremios.add(p);
    }

    public void removePremio(Premio p) {
        listaPremios.remove(p);
    }

    // Getters e Setters
    public ArrayList<Premio> getListaPremios() {
        return listaPremios;
    }

    public void setListaPremios(ArrayList<Premio> listaPremios) {
        this.listaPremios = listaPremios;
    }

    public Premio getPremioPorNome(String nome) {
        for (Premio p : listaPremios) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;
    }


    // toString
    @Override
    public String toString() {
        return "ListaPremios [listaPremios=" + listaPremios + "]";
    }

    // Verificar se um premio existe
    public boolean contains(Premio premio) {
        for (Premio p : listaPremios) {
            if (p.getId().equals(premio.getId())) {
                return true;
            }
        }
        return false;
    }
}
