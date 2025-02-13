

package com.dai2324.prototipodai.Classes;

import java.util.ArrayList;
//Dados Frota

//Substituído pelo Repositório
public class ListaAnunciosDeAlteracaoDeRota {
    ArrayList<AnunciodeAlteracaodeRota> anunciosDeAlteracaoDeRota;

    public ListaAnunciosDeAlteracaoDeRota() {
        this.anunciosDeAlteracaoDeRota = new ArrayList<>();
    }

    public void add(AnunciodeAlteracaodeRota anuncio) {
        this.anunciosDeAlteracaoDeRota.add(anuncio);
    }

    public void remove(AnunciodeAlteracaodeRota anuncio) {
        this.anunciosDeAlteracaoDeRota.remove(anuncio);
    }

    public ArrayList<AnunciodeAlteracaodeRota> getAnunciosDeAlteracaoDeRota() {
        return anunciosDeAlteracaoDeRota;
    }

    public void setAnunciosDeAlteracaoDeRota(ArrayList<AnunciodeAlteracaodeRota> anunciosDeAlteracaoDeRota) {
        this.anunciosDeAlteracaoDeRota = anunciosDeAlteracaoDeRota;
    }

    public AnunciodeAlteracaodeRota getAnuncioById(String id) {
        for (AnunciodeAlteracaodeRota anuncio : anunciosDeAlteracaoDeRota) {
            if (anuncio.getIdAlteracao().equals(id)) {
                return anuncio;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Lista de Anuncios de Alteracao de Rota{" + "anuncios=" + anunciosDeAlteracaoDeRota + '}';
    }

}
