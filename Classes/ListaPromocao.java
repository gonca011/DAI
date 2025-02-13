package com.dai2324.prototipodai.Classes;


import java.util.ArrayList;

// Dados Recompensas
public class ListaPromocao {

    // Criar a lista de promocoes
    private ArrayList<Promocao> listaPromocao = new ArrayList<Promocao>();

    // Construtor
    public ListaPromocao() {
        this.listaPromocao = new ArrayList<>();
    }

    // Adicionar e remover promocoes
    public void adicionarPromocao(Promocao promocao) {
        listaPromocao.add(promocao);
    }

    public void removerPromocao(Promocao promocao) {
        listaPromocao.remove(promocao);
    }

    // Getters e Setters
    public ArrayList<Promocao> getListaPromocao() {
        return listaPromocao;
    }

    public void setListaPromocao(ArrayList<Promocao> listaPromocao) {
        this.listaPromocao = listaPromocao;
    }


    // toString
    @Override
    public String toString() {
        return "ListaPromocao [listaPromocao=" + listaPromocao + "]";
    }

    // Verificar se a promocao existe
    public boolean contains(Promocao promocao) {
        for (Promocao p : listaPromocao) {
            if (p.getNome().equals(promocao.getNome())) {
                return true;
            }
        }
        return false;
    }
}
