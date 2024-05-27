package com.dai2324.prototipodai.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dai2324.prototipodai.Classes.CodigoDesconto;

// Dados dos Pontos - Dados da gestão de Códigos de Desconto
public interface CodigoDescontoRepositorio  extends JpaRepository<CodigoDesconto, String>{

}
