package com.dai2324.prototipodai.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dai2324.prototipodai.Classes.LoggedInUtilizador;

// Dados de Utilizadores - Dados da gestão de Contas
@Repository
public interface LoggedInUtilizadorRepositorio extends JpaRepository<LoggedInUtilizador, Integer> {
    List<LoggedInUtilizador> findByTipoPerfis_Perfil(String perfil);
    LoggedInUtilizador findFirstByTipoPerfis_Perfil(String perfil);
}

