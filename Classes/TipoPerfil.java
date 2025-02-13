package com.dai2324.prototipodai.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Dados de Associação de Perfis Especiais
@Entity
@Table(name = "tipoperfil")
public class TipoPerfil {
    @Id
    private Integer id;

    private String perfil;

    public TipoPerfil() {
    }

    public TipoPerfil(Integer id, String perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "TipoPerfil{" +
                "id='" + id + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }

}


