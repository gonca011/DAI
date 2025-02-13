package com.dai2324.prototipodai.Classes;


import java.util.ArrayList;

// Dados Parceiros
public class ListaParceiros implements java.io.Serializable {

    private ArrayList<Parceiros> parceiros;

    public ListaParceiros() {
        this.parceiros = new ArrayList<>();
    }

    public void add(Parceiros parceiro) {
        this.parceiros.add(parceiro);
    }

    public void remove(Parceiros parceiro) {
        this.parceiros.remove(parceiro);
    }

    public ArrayList<Parceiros> getParceiros() {
        return parceiros;
    }

    public void setParceiros(ArrayList<Parceiros> parceiros) {
        this.parceiros = parceiros;
    }

    public Parceiros getParceiroById(String id) {
        for (Parceiros parceiro : parceiros) {
            if (parceiro.getId().equals(id)) {
                return parceiro;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "ListaParceiros{" + "parceiros=" + parceiros;
    }

    public boolean contains(Parceiros novoParceiros) {
        for (Parceiros parceiro : parceiros) {
            if (parceiro.getId().equals(novoParceiros.getId())) { 
                return true;
            }
        }
        return false;
    }
}
