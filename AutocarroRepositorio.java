package com.dai2324.prototipodai.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dai2324.prototipodai.Classes.Autocarro;

// Dados de Frota - Dados da gestão de Autocarros
public interface AutocarroRepositorio  extends JpaRepository<Autocarro, String>{

}
