package com.dai2324.prototipodai.repositorio;

import com.dai2324.prototipodai.Classes.Paragem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Dados de Viagem - Dados da gestão de Paragens
@Repository
public interface ParagemRepositorio extends JpaRepository<Paragem, String> {

    @Query(value = "Select p FROM Paragem p where p.nParagem=:nParagem")
    Optional<Paragem> findByNParagem(String nParagem);
}
