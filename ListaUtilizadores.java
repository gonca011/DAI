package com.dai2324.prototipodai.Classes;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.io.*;

// Dados Utilizador
@Service
public class ListaUtilizadores implements Serializable {

    //Criar a lista de utilizadores
    private ArrayList<Utilizador> listaUtilizadores;

    //Construtor
    public ListaUtilizadores() {
        this.listaUtilizadores = new ArrayList<Utilizador>();
    }

    //Adicionar e remover utilizadores
    public void adicionarUtilizador(Utilizador utilizador) {
        this.listaUtilizadores.add(utilizador);
    }

    public void removerUtilizador(Utilizador utilizador) {
        this.listaUtilizadores.remove(utilizador);
    }

    // Getters e Setters
    public ArrayList<Utilizador> getListaUtilizadores() {
        return this.listaUtilizadores;
    }

    public void setListaUtilizadores(ArrayList<Utilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores;
    }

    public Utilizador getUtilizadorEmail(String email) {
        for (Utilizador utilizador : this.listaUtilizadores) {
            System.out.println("A verificar utilizador: " + utilizador.getEmail());
            if (utilizador.getEmail().equals(email)) {
                return utilizador;
            }
        }
        return null;
    }

    public Utilizador getUtilizador(int index) {
        return this.listaUtilizadores.get(index);
    }

    // Verificar se um utilizador existe
    public boolean existeUtilizador(String email) {
        for (Utilizador utilizador : this.listaUtilizadores) {
            if (utilizador.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    // toString
    @Override
    public String toString() {
        return "ListaUtilizadores{" + "listaUtilizadores=" + listaUtilizadores + '}';
    }
}
