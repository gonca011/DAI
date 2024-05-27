package com.dai2324.prototipodai.Controlo;

import com.dai2324.prototipodai.Classes.Percurso;
import com.dai2324.prototipodai.repositorio.PercursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Controlo de Confirmação - Controla as ações dos Percursos
@Controller
public class ControloPercurso {

    @Autowired
    private PercursoRepositorio percursoRepositorio;

    @PostMapping("/addLinha") // Lê o HTML AdicionarPercursos
    public String adicionarLinha(@RequestParam String id, @RequestParam String origem, @RequestParam String destino) {

        Percurso novoPercurso = new Percurso(id, origem, destino); // Cria um novo percurso com os parametros recebidos
        percursoRepositorio.save(novoPercurso);

        return "redirect:/GestordeFrota/AdicionarPercursos.html"; // Redireciona para a página AdicionarPercursos.html para adicionar mais percursos
    }

    @PostMapping("/editarPercurso") // Lê o HTML EditarPercursos
    public String editarPercurso(@RequestParam String id, @RequestParam String origem, @RequestParam String destino) {

        // Recebe os parametros do formulario (Ver parte do "form" no HTML EditarPercursos.html)

        Optional<Percurso> percursoExistenteOptional = percursoRepositorio.findById(id);

        if (percursoExistenteOptional.isPresent()) {
            Percurso percurso = percursoExistenteOptional.get();
            percurso.setId(id);
            percurso.setOrigem(origem);
            percurso.setDestino(destino);
            percursoRepositorio.save(percurso);
        } else {
            System.out.println("Percurso não encontrado");
        }

        return "redirect:/GestordeFrota/EditarPercursos.html"; // Redireciona para a página EditarPercursos.html para editar mais percursos
    }

    @PostMapping("/remove") // Lê o HTML RemoverPercursos
    public String removerPercurso(@RequestParam String id) {

        Optional<Percurso> percursoExistenteOptional = percursoRepositorio.findById(id);
        if (percursoExistenteOptional.isPresent()) {
            percursoRepositorio.deleteById(percursoExistenteOptional.get().getId());
        } else {
            System.out.println("Percurso não encontrado");
        }

        return "redirect:/GestordeFrota/RemoverPercursos.html"; // Redireciona para a página RemoverPercursos.html para remover mais percursos
    }

    @GetMapping(value = "/listaPercursos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Percurso>> listaPercursos() {
        return ResponseEntity.ok(percursoRepositorio.findAll());
    }

    @GetMapping(value = "/ordPercursos")
    public ResponseEntity<List<Percurso>> ordPercursos() {
        return ResponseEntity.ok(percursoRepositorio.findAll());
    }

    @GetMapping(value = "/procPercursos")
    public ResponseEntity<List<Percurso>> procPercursos(@RequestParam String destino) {
        List<Percurso> percursos = percursoRepositorio.findAll().stream()
                .filter(percurso -> percurso.getDestino().equals(destino))
                .collect(Collectors.toList());
        return ResponseEntity.ok(percursos);
    }


}