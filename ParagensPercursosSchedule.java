package com.dai2324.prototipodai.Classes;

import java.sql.Time;
import jakarta.persistence.*;

// Dados Viagens
@Entity
@Table(name = "paragens_percursos_schedule")
public class ParagensPercursosSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "nparagem", referencedColumnName = "nparagem")
    private Paragem paragem;

    @OneToOne
    @JoinColumn(name = "idpercurso", referencedColumnName = "idpercurso")
    private Percurso percurso;

    @Column(name = "schedule")
    private Time schedule;

    @OneToOne
    @JoinColumn(name = "idatraso", referencedColumnName = "idatraso")
    private AnunciosDeAtraso anuncioDeAtraso;

    // Construtores
    public ParagensPercursosSchedule() {
    }

    public ParagensPercursosSchedule(Long id, Paragem paragem, Percurso percurso, Time schedule,
            AnunciosDeAtraso anuncioDeAtraso) {
        this.id = id;
        this.paragem = paragem;
        this.percurso = percurso;
        this.schedule = schedule;
        this.anuncioDeAtraso = anuncioDeAtraso;
    }

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paragem getParagem() {
        return paragem;
    }

    public void setParagem(Paragem paragem) {
        this.paragem = paragem;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

    public Time getSchedule() {
        return schedule;
    }

    public void setSchedule(Time schedule) {
        this.schedule = schedule;
    }

    public AnunciosDeAtraso getAnuncioDeAtraso() {
        return anuncioDeAtraso;
    }

    public void setAnuncioDeAtraso(AnunciosDeAtraso anuncioDeAtraso) {
        this.anuncioDeAtraso = anuncioDeAtraso;
    }
}
