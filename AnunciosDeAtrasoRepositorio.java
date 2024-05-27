package com.dai2324.prototipodai.repositorio;

import com.dai2324.prototipodai.Classes.AnunciosDeAtraso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Dados de Frota - Dados da gestão de Anúncios
@Repository
public interface AnunciosDeAtrasoRepositorio extends JpaRepository<AnunciosDeAtraso, String> {
}