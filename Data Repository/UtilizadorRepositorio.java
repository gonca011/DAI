package com.dai2324.prototipodai.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dai2324.prototipodai.Classes.Utilizador;
import java.util.List;

// Dados de Utilizadores - Dados da gestão de Contas
@Repository
public interface UtilizadorRepositorio extends JpaRepository<Utilizador, String>{
    Optional<Utilizador> findByEmailAndNumdocIdentificacao(String email, String numdocIdentificacao);
    List<Utilizador> findByTipoPerfis_Perfil(String perfil);
    Optional<Utilizador> findByEmailAndNumdocIdentificacaoAndTipoPerfis_Perfil(String email, String numdocIdentificacao, String perfil);
    Optional<Utilizador> findByNumdocIdentificacao(String numdocIdentificacao);
    Optional<Utilizador> findByTipoPerfis_PerfilAndNumdocIdentificacao(String perfil, String numdocIdentificacao);
}
