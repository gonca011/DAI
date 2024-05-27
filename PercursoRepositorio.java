package com.dai2324.prototipodai.repositorio;

import com.dai2324.prototipodai.Classes.Percurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Dados de Viagem - Dados da gestão de Percursos
@Repository
public interface PercursoRepositorio extends JpaRepository<Percurso, String> {
}
