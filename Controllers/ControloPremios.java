package com.dai2324.prototipodai.Controlo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dai2324.prototipodai.Classes.Premio;
import com.dai2324.prototipodai.Classes.Utilizador;
import com.dai2324.prototipodai.repositorio.PremiosRepositorio;
import org.springframework.web.bind.annotation.RequestBody;
import com.dai2324.prototipodai.repositorio.UtilizadorRepositorio;

// Controlo de Confirmação - Controla as ações dos Prémios
@Controller
public class ControloPremios {

    @Autowired
    private PremiosRepositorio premioRepository;

    @Autowired
    private UtilizadorRepositorio utilizadorRepository;

    @PostMapping("/addPremio")
    public String adicionarPremio(@RequestParam String nome, @RequestParam String descricao,
            @RequestParam String criteriodeAtribuicao, @RequestParam String id) {
        Premio premio = new Premio(nome, descricao, criteriodeAtribuicao, id);

        if (!premioRepository.existsById(id)) {
            premioRepository.save(premio);
            System.out.println("Prémio adicionado com sucesso" + premio.toString());
        }

        return "redirect:/Coordenador/AdicionarPremio.html";
    }

    @PostMapping("/removePremio")
    public String removerPremio(@RequestParam String id) {
        if (premioRepository.existsById(id)) {
            premioRepository.deleteById(id);
            System.out.println("Premio removido com sucesso: " + id);
        }

        return "redirect:/Coordenador/RemoverPremio.html";
    }

    @PostMapping("/editarPremio")
    public String editarPremio(@RequestParam String id, @RequestParam String descricao,
            @RequestParam String criteriodeAtribuicao, @RequestParam String nome) {
        Optional<Premio> optionalPremio = premioRepository.findById(id);

        if (optionalPremio.isPresent()) {
            Premio premio = optionalPremio.get();
            premio.setDescricao(descricao);
            premio.setCriteriodeAtribuicao(criteriodeAtribuicao);
            premio.setNome(nome);
            premioRepository.save(premio);
            System.out.println("Novo Premio " + premio);
        }

        return "redirect:/Coordenador/EditarPremio.html";
    }

    @GetMapping(value = "/listarPremios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Premio>> listarPremios() {
        return ResponseEntity.ok(premioRepository.findAll());
    }

    @PostMapping("/redeemPremio")
    public String redeemPremio(@RequestBody Map<String, String> body) {
        String utilizadorNdoc = body.get("utilizadorNdoc");
        String premioId = body.get("premioId");

        if (utilizadorNdoc == null || premioId == null) {
            return "redirect:/Utilizador/CustomizadoPremio.html";
        }

        Optional<Utilizador> optionalUtilizador = utilizadorRepository.findByNumdocIdentificacao(utilizadorNdoc);
        Optional<Premio> optionalPremio = premioRepository.findById(premioId);

        if (!optionalUtilizador.isPresent() || !optionalPremio.isPresent()) {
            return "redirect:/Utilizador/CustomizadoPremio.html";
        }

        Utilizador utilizador = optionalUtilizador.get();
        Premio premio = optionalPremio.get();

        try {
            utilizador.redeemPremio(premio);
        } catch (Exception e) {
            return "redirect:/Utilizador/CustomizadoPremio.html";
        }

        utilizadorRepository.save(utilizador);
        premioRepository.save(premio);

        return "redirect:/Utilizador/DetalhesPremio.html";
    }

}
