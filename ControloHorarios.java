package com.dai2324.prototipodai.Controlo;

import com.dai2324.prototipodai.Classes.*;
import com.dai2324.prototipodai.repositorio.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

// Controlo de Confirmação - Controla as ações dos Horários
@Controller
public class ControloHorarios {

    @Autowired
    private ParagensPercursosScheduleRepositorio scheduleRepository;

    @Autowired
    private ParagemRepositorio paragemRepository;

    @Autowired
    private PercursoRepositorio percursoRepository;

    @Autowired
    private AnunciosDeAtrasoRepositorio anunciosDeAtrasoRepository;

    @PostMapping("/addSchedule")
    public String addSchedule(@RequestParam String nParagem, @RequestParam String idPercurso,
            @RequestParam String schedule, @RequestParam String idAtraso) throws Exception {
        ParagensPercursosSchedule newSchedule = new ParagensPercursosSchedule();
        newSchedule.setParagem(paragemRepository.findById(nParagem)
                .orElseThrow(() -> new NoSuchElementException("Paragem não encontrada " + nParagem)));
        newSchedule.setPercurso(percursoRepository.findById(idPercurso)
                .orElseThrow(() -> new NoSuchElementException("Percurso não encontrado" + idPercurso)));

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        long ms = format.parse(schedule).getTime();
        Time t = new Time(ms);
        newSchedule.setSchedule(t);

        if (!"0".equals(idAtraso)) {
            newSchedule.setAnuncioDeAtraso(anunciosDeAtrasoRepository.findById(idAtraso)
                    .orElseThrow(() -> new NoSuchElementException("Anúncio não encontrado" + idAtraso)));
        }

        scheduleRepository.save(newSchedule);
        return "redirect:/GestordeFrota/AdicionarHorario.html";
    }

    @PostMapping("/updateScheduleAtraso")
    public String updateScheduleAtraso(@RequestParam Long scheduleId, @RequestParam String idPercurso,
            @RequestParam String scheduleTime, @RequestParam String idAtraso) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        long ms = format.parse(scheduleTime).getTime();
        Time t = new Time(ms);
        Optional<ParagensPercursosSchedule> scheduleOpt = scheduleRepository.findById(scheduleId);
        if (scheduleOpt.isPresent()) {
            ParagensPercursosSchedule schedule = scheduleOpt.get();
            Percurso percurso = percursoRepository.findById(idPercurso)
                    .orElseThrow(() -> new NoSuchElementException("Percurso não encontrado " + idPercurso));
            schedule.setPercurso(percurso);
            schedule.setSchedule(t);
            AnunciosDeAtraso atraso = anunciosDeAtrasoRepository.findById(idAtraso).orElse(null);
            schedule.setAnuncioDeAtraso(atraso);
            scheduleRepository.save(schedule);
        }
        return "redirect:/GestordeFrota/EditarHorario.html";
    }

    @PostMapping("/removeSchedule")
    public String removeSchedule(@RequestParam Long scheduleId) {
        if (scheduleRepository.existsById(scheduleId)) {
            scheduleRepository.deleteById(scheduleId);
        } else {
            return "redirect:/GestordeFrota/RemoverHorario.html";

        }
        return "redirect:/GestordeFrota/RemoverHorario.html";
    }

    @GetMapping("/listSchedules")
    public ResponseEntity<List<ParagensPercursosSchedule>> listSchedules() {
        return ResponseEntity.ok(scheduleRepository.findAll());
    }
}
