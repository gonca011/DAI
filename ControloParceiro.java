package com.dai2324.prototipodai.Controlo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.dai2324.prototipodai.Classes.Parceiros;
import com.dai2324.prototipodai.repositorio.ParceirosRepositorio;

// Controlo de Confirmação - Controla as ações dos Parceiros
@Controller
public class ControloParceiro {

    @Autowired
    ParceirosRepositorio parceirosRepositorio;

    @PostMapping("/addParceiro")
    public String adicionarParceiro(@RequestParam String nome, @RequestParam String descricao,
            @RequestParam String id) {
        Parceiros newParceiro = new Parceiros(nome, descricao, id);

        Optional<Parceiros> optionalParceiro = parceirosRepositorio.findById(id);
        if (!optionalParceiro.isPresent()) {
            parceirosRepositorio.save(newParceiro);
            System.out.println("Parceiro adicionado com sucesso" + newParceiro);
        }

        return "redirect:/Coordenador/AdicionarParceiros.html";
    }

    @PostMapping("/editarParceiro")
    public String editarParceiro(@RequestParam String nome, @RequestParam String descricao, @RequestParam String id) {
        Optional<Parceiros> optionalParceiro = parceirosRepositorio.findById(id);
        if (optionalParceiro.isPresent()) {
            Parceiros parceiro = optionalParceiro.get();
            parceiro.setNome(nome);
            parceiro.setDescricao(descricao);
            parceirosRepositorio.save(parceiro);
            System.out.println("Parceiro atualizado com Sucesso: " + parceiro);
        }

        return "redirect:/Coordenador/EditarParceiros.html";
    }

    @PostMapping("/removerParceiro")
    public String removerParceiro(@RequestParam String id) {
        id = id.trim();
        Optional<Parceiros> optionalParceiro = parceirosRepositorio.findById(id);
        if (optionalParceiro.isPresent()) {
            parceirosRepositorio.deleteById(id);
            System.out.println("Parceiro removido com sucesso: " + id);
        }

        return "redirect:/Coordenador/RemoverParceiro.html";
    }

    @GetMapping(value = "/listarParceiros", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Parceiros>> listarParceiros() {
        return ResponseEntity.ok(parceirosRepositorio.findAll());
    }

}
