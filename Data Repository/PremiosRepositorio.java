package com.dai2324.prototipodai.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dai2324.prototipodai.Classes.Premio;

// Dados das Recompensas - Dados da gestão de Prémios
public interface PremiosRepositorio extends JpaRepository<Premio, String>{

}
