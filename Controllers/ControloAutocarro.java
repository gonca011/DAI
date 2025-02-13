package com.dai2324.prototipodai.Controlo;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dai2324.prototipodai.Classes.Autocarro;
import com.dai2324.prototipodai.repositorio.*;

// Controlo de Confirmação - Controla as ações dos Autocarros
@Controller
public class ControloAutocarro {

    // Criar uma lista de autocarros para guardar os autocarros
    @Autowired
    private AutocarroRepositorio autocarroRepository;

    @PostMapping("/addAutocarro")
    public String adicionarAutocarros(@RequestParam String matricula, @RequestParam int capacidade,
            @RequestParam int numKm) {
        Autocarro novoAutocarro = new Autocarro(matricula, capacidade, numKm);

        if (!autocarroRepository.existsById(matricula)) {
            autocarroRepository.save(novoAutocarro);
            System.out.println("Autocarro adicionado com sucesso" + novoAutocarro.toString());
        }

        return "redirect:/GestordeFrota/AdicionarAutocarros.html";
    }

    @PostMapping("/removeAutocarro")
    public String removerAutocarro(@RequestParam String matricula) {
        System.out.println("Autocarro a remover:" + matricula);

        if (autocarroRepository.existsById(matricula)) {
            autocarroRepository.deleteById(matricula);
            System.out.println("Autocarro removido com sucesso");
        }

        return "redirect:/GestordeFrota/RemoverAutocarros.html";
    }

    @PostMapping("/editarAutocarro")
    public String editarAutocarro(@RequestParam String matricula, @RequestParam int capacidade,
            @RequestParam int numKm) {
        String matriculanova = matricula;
        if (matricula.contains("=")) {
            matriculanova = matricula.split("=")[1];
        }

        Optional<Autocarro> optionalAutocarro = autocarroRepository.findById(matriculanova);
        if (optionalAutocarro.isPresent()) {
            Autocarro autocarro = optionalAutocarro.get();
            autocarro.setCapacidade(capacidade);
            autocarro.setNumKm(numKm);
            autocarroRepository.save(autocarro);
            System.out.println("Autocarro atualizado " + autocarro);
        }

        return "redirect:/GestordeFrota/EditarAutocarros.html";
    }

    @GetMapping("/listarAutocarros")
    public ResponseEntity<List<Autocarro>> listarAutocarros() {
        List<Autocarro> autocarros = (List<Autocarro>) autocarroRepository.findAll();
        return ResponseEntity.ok(autocarros);
    }

}
