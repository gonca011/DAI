package com.dai2324.prototipodai.Classes;


import java.util.ArrayList;

//Dados Viagens
public class ListaPercursos implements java.io.Serializable {

    //Criar a lista de percursos
    private ArrayList<Percurso> percursos;

    //Construtor
    public ListaPercursos() {
        this.percursos = new ArrayList<>();
    }

    //Adicionar e remover percursos
    public void add(Percurso percurso) {
        this.percursos.add(percurso);
    }

    public void remove(Percurso percurso) {
        this.percursos.remove(percurso);
    }

    //Getters e Setters
    public ArrayList<Percurso> getpercursos() {
        return percursos;
    }

    public void setpercursos(ArrayList<Percurso> percursos) {
        this.percursos = percursos;
    }

    public Percurso getPercursoById(String id) {
        for (Percurso percurso : percursos) {
            if (percurso.getId().equals(id)) {
                return percurso;
            }
        }
        return null;
    }


    // toString
    @Override
    public String toString() {
        return "Listapercursos{" + "percursos=" + percursos + '}';
    }

    //Verificar se um percurso existe
    public boolean contains(Percurso novPercurso) {
        for (Percurso percurso : percursos) {
            if (percurso.getId().equals(novPercurso.getId())) {
                return true;
            }
        }
        return false;
    }
}
