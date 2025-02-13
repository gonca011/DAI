package com.dai2324.prototipodai.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dai2324.prototipodai.Classes.ParagensPercursosSchedule;

// Dados de Viagem - Dados da gestão de Horários
@Repository
public interface ParagensPercursosScheduleRepositorio extends JpaRepository<ParagensPercursosSchedule, Long> {
    
}