package com.dai2324.prototipodai.Controlo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.dai2324.prototipodai.Classes.Promocao;
import com.dai2324.prototipodai.Classes.Utilizador;
import com.dai2324.prototipodai.repositorio.PromocoesRepositorio;
import com.dai2324.prototipodai.repositorio.UtilizadorRepositorio;

// Controlo de Confirmação - Controla as ações das Promoções
@Controller
public class ControloPromocao {

    @Autowired
    private PromocoesRepositorio promocaoRepository;
    @Autowired
    private UtilizadorRepositorio utilizadorRepository;

    @PostMapping("/redeemPromocao")
    public String redeemPromocao(@RequestBody Map<String, String> body) {

        String utilizadorNdoc = body.get("utilizadorNdoc");
        String promocaoId = body.get("promocaoId");

        if (utilizadorNdoc == null || promocaoId == null) {
            return "redirect:/Utilizador/CustomizadoPromocao.html";
        }

        Optional<Utilizador> optionalUtilizador = utilizadorRepository.findByNumdocIdentificacao(utilizadorNdoc);
        Optional<Promocao> optionalPromocao = promocaoRepository.findById(promocaoId);

        if (!optionalUtilizador.isPresent() || !optionalPromocao.isPresent()) {
            return "redirect:/Utilizador/CustomizadoPromocao.html";
        }

        Utilizador utilizador = optionalUtilizador.get();
        Promocao promocao = optionalPromocao.get();

        try {
            utilizador.redeemPromocao(promocao);
        } catch (Exception e) {
            return "redirect:/Utilizador/CustomizadoPromocao.html";
        }

        utilizadorRepository.save(utilizador);
        promocaoRepository.save(promocao);

        return "redirect:/Utilizador/DetalhesPromocao.html";
    }

    @PostMapping("/addPromocao")
    public String adicionarPromocao(@RequestParam String nome, @RequestParam String descricao,
            @RequestParam String criterioDeAtribuicao, @RequestParam String id) {
        Promocao promocao = new Promocao(nome, descricao, criterioDeAtribuicao, id);

        if (!promocaoRepository.existsById(id)) {
            promocaoRepository.save(promocao);
            System.out.println("Prémio adicionado com sucesso" + promocao.toString());
            System.out.println("Prémios atuais:" + promocaoRepository.findAll());
        }

        return "redirect:/Coordenador/AdicionarPromocao.html";
    }

    @PostMapping("/removePromocao")
    public String removerPremio(@RequestParam String id) {
        if (promocaoRepository.existsById(id)) {
            promocaoRepository.deleteById(id);
            System.out.println("Promoção removido com sucesso");
            System.out.println("Promoções atuais:" + promocaoRepository.findAll());
        }

        return "redirect:/Coordenador/RemoverPromocao.html";
    }

    @PostMapping("/editarPromocao")
    public String editarPromocao(@RequestParam String id, @RequestParam String descricao,
            @RequestParam String criterioDeAtribuicao, @RequestParam String nome) {
        Optional<Promocao> optionalPromocao = promocaoRepository.findById(id);

        if (optionalPromocao.isPresent()) {
            Promocao promocao = optionalPromocao.get();
            promocao.setDescricao(descricao);
            promocao.setCriterioDeAtribuicao(criterioDeAtribuicao);
            promocao.setNome(nome);
            promocaoRepository.save(promocao);
            System.out.println("Novo Premio " + promocao);
        }

        return "redirect:/Coordenador/EditarPromocao.html";
    }

    @GetMapping("/listarPromocoes")
    public ResponseEntity<List<Promocao>> listarPromocoes() {
        return ResponseEntity.ok(promocaoRepository.findAll());
    }

}
