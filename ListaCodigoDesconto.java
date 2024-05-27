package com.dai2324.prototipodai.Classes;

import java.util.ArrayList;


// Dados Frota
public class ListaCodigoDesconto {

    // Criar a lista de códigos de desconto
    private ArrayList<CodigoDesconto> listaCodigoDesconto = new ArrayList<CodigoDesconto>();

    // Construtor
    public ListaCodigoDesconto() {
        this.listaCodigoDesconto = new ArrayList<>();
    }

    // Adicionar e remover códigos de desconto
    public void adicionarCodigoDesconto(CodigoDesconto codigoDesconto) {
        listaCodigoDesconto.add(codigoDesconto);
    }

    public void removerCodigoDesconto(CodigoDesconto codigoDesconto) {
        listaCodigoDesconto.remove(codigoDesconto);
    }

    // Getters e Setters
    public ArrayList<CodigoDesconto> getListaCodigoDesconto() {
        return listaCodigoDesconto;
    }

    public void setListaCodigoDesconto(ArrayList<CodigoDesconto> listaCodigoDesconto) {
        this.listaCodigoDesconto = listaCodigoDesconto;
    }

    public CodigoDesconto getCodigoDesconto(String id) {
        for (CodigoDesconto cd : listaCodigoDesconto) {
            if (cd.getId().equals(id)) {
                return cd;
            }
        }
        return null;
    }


    // toString
    @Override
    public String toString() {
        return "ListaCodigoDesconto [listaCodigoDesconto=" + listaCodigoDesconto + "]";
    }

}
