package com.dai2324.prototipodai.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dai2324.prototipodai.Classes.TipoPerfil;
import java.util.Optional;

// Dados de Utilizador - Dados da gestão de Perfis
@Repository
public interface TipoPerfilRepositorio extends JpaRepository<TipoPerfil, Integer> {
    Optional<TipoPerfil> findByPerfil(String perfil);
}