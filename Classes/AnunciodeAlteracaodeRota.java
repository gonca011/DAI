package com.dai2324.prototipodai.Classes;

import jakarta.persistence.*;

// Dados Frota
@Entity
@Table(name = "anunciodealteracaoderota")
public class AnunciodeAlteracaodeRota {
    
    @OneToOne
    @JoinColumn(name = "nparagem", referencedColumnName = "nparagem")
    private Paragem paragemAlterada;

    @OneToOne
    @JoinColumn(name = "idpercurso", referencedColumnName = "idpercurso")
    private Percurso percursoAlterado;

    @Id
    @Column(name = "idalteracao")
    private String idAlteracao;

    @Column(name = "motivo")
    private String motivo;


    public AnunciodeAlteracaodeRota() {
    }

    public AnunciodeAlteracaodeRota(Paragem paragem, String idAlteracao2, String motivo2, Percurso percurso) {
        this.paragemAlterada = paragem;
        this.idAlteracao = idAlteracao2;
        this.motivo = motivo2;
        this.percursoAlterado = percurso;
    }

    public Paragem getParagemAlterada() {
        return paragemAlterada;
    }

    public String getIdAlteracao() {
        return idAlteracao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setParagemAlterada(Paragem paragemAlterada) {
        this.paragemAlterada = paragemAlterada;
    }

    public void setIdAlteracao(String idAlteracao) {
        this.idAlteracao = idAlteracao;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Percurso getPercursoAlterado() {
        return percursoAlterado;
    }

    public void setPercursoAlterado(Percurso percursoAlterado) {
        this.percursoAlterado = percursoAlterado;
    }

    @Override
    public String toString() {
        return "AnunciodeAlteracaodeRota{" +
                "paragemAlterada=" + paragemAlterada +
                ", percursoAlterado=" + percursoAlterado +
                ", idAlteracao='" + idAlteracao + '\'' +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
