package com.dai2324.prototipodai.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dai2324.prototipodai.Classes.Promocao;

//Dados das Recompensas - Dados da gestão de Promoções
public interface PromocoesRepositorio extends JpaRepository<Promocao, String>{

}
