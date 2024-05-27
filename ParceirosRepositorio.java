package com.dai2324.prototipodai.repositorio;

import com.dai2324.prototipodai.Classes.Parceiros;
import org.springframework.data.jpa.repository.JpaRepository;

//Dados dos Parceiros - Dados da gestão de Parceiros
public interface ParceirosRepositorio extends JpaRepository<Parceiros, String> {
}
