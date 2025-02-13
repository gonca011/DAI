package com.dai2324.prototipodai.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dai2324.prototipodai.Classes.QRCodeImage;

// Dados de Pontos - Dados da gestão de QRCode
public interface QRCodeRepositorio extends JpaRepository<QRCodeImage, Long> {
    QRCodeImage findByUtilizador_NumdocIdentificacao(String userId);
}
