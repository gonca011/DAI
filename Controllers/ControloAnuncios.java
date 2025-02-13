package com.dai2324.prototipodai.Controlo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.dai2324.prototipodai.Classes.AnunciodeAlteracaodeRota;
import com.dai2324.prototipodai.Classes.AnunciosDeAtraso;
import com.dai2324.prototipodai.Classes.Paragem;
import com.dai2324.prototipodai.Classes.Percurso;
import com.dai2324.prototipodai.repositorio.*;

// Controlo Confirmação - Controla as ações dos Anúncios
@Controller
public class ControloAnuncios {

    @Autowired
    private AnunciosDeAtrasoRepositorio anunciosDeAtrasoRepositorio;

    @Autowired
    private AnunciodeAlteracaodeRotaRepositorio anuncioDeAlteracaodeRotaRepositorio;

    @Autowired
    private ParagemRepositorio paragemRepositorio;

    @Autowired
    private PercursoRepositorio percursoRepositorio;

    @PostMapping("/addanuncioDeAtraso")
    public String adicionarAnunciosDeAtraso(@RequestParam String idAtraso, @RequestParam String tempoAtraso,
            @RequestParam String motivoAtraso) {

        AnunciosDeAtraso newAnunciosDeAtraso = new AnunciosDeAtraso(tempoAtraso, motivoAtraso, idAtraso);

        Optional<AnunciosDeAtraso> existingAnuncio = anunciosDeAtrasoRepositorio.findById(idAtraso);
        if (!existingAnuncio.isPresent()) {
            anunciosDeAtrasoRepositorio.save(newAnunciosDeAtraso);
            System.out.println("Anuncio adicionado com sucesso" + newAnunciosDeAtraso.toString());
        }

        return "redirect:/GestordeFrota/AdicionarAnunciosDeAtraso.html";
    }

    @PostMapping("/editarAnuncio")
    public String editarAnuncioDeAtraso(@RequestParam String idAtraso, @RequestParam String tempoAtraso,
            @RequestParam String motivoAtraso) {

        Optional<AnunciosDeAtraso> existingAnuncio = anunciosDeAtrasoRepositorio.findById(idAtraso);
        if (existingAnuncio.isPresent()) {
            AnunciosDeAtraso anuncioDeAtraso = existingAnuncio.get();
            anuncioDeAtraso.setTempoAtraso(tempoAtraso);
            anuncioDeAtraso.setMotivoAtraso(motivoAtraso);
            anunciosDeAtrasoRepositorio.save(anuncioDeAtraso);
            System.out.println("AnuncioDeAtraso atualizado " + anuncioDeAtraso);
        }

        return "redirect:/GestordeFrota/EditarAnunciosDeAtraso.html";
    }

    @GetMapping("/listarAnuncios")
    public ResponseEntity<List<AnunciosDeAtraso>> listarAnunciosDeAtraso() {
        List<AnunciosDeAtraso> anunciosDeAtraso = (List<AnunciosDeAtraso>) anunciosDeAtrasoRepositorio.findAll();
        return ResponseEntity.ok(anunciosDeAtraso);
    }

    @PostMapping("/removeAnuncioAtraso")
    public String removerAnunciodeAtraso(@RequestParam String idAtraso) {
        System.out.println("Id a remover:" + idAtraso);

        Optional<AnunciosDeAtraso> existingAnuncio = anunciosDeAtrasoRepositorio.findById(idAtraso);
        if (existingAnuncio.isPresent()) {
            anunciosDeAtrasoRepositorio.delete(existingAnuncio.get());
            System.out.println("Anuncio removido com sucesso");
        }

        return "redirect:/GestordeFrota/RemoverAnunciosDeAtraso.html";
    }

    @PostMapping("/addanuncioDeRota")
    public String adicionarAnunciosDeAlteracaoDeRota(@RequestParam String nome, @RequestParam String id,
            @RequestParam String idAlteracao, @RequestParam String motivo) {

        Optional<Paragem> optionalParagem = paragemRepositorio.findByNParagem(nome);
        if (!optionalParagem.isPresent()) {
            System.out.println("Paragem não encontrada -> AdicionarAnuncioDeAlteracaoDeRota2.html");
            return "redirect:/GestordeFrota/AdicionarAnuncioDeAlteracaoDeRota.html";
        }

        Paragem paragem = optionalParagem.get();
        Optional<Percurso> optionalPercurso = percursoRepositorio.findById(id);
        if (!optionalPercurso.isPresent()) {
            System.out.println("Percurso não encontrado -> AdicionarAnuncioDeAlteracaoDeRota2.html");
            return "redirect:/GestordeFrota/AdicionarAnuncioDeAlteracaoDeRota.html";
        }

        Percurso percurso = optionalPercurso.get();
        AnunciodeAlteracaodeRota novAnunciodeAlteracaodeRotas = new AnunciodeAlteracaodeRota(paragem, idAlteracao,
                motivo, percurso);
        anuncioDeAlteracaodeRotaRepositorio.save(novAnunciodeAlteracaodeRotas);
        System.out.println("Anuncio adicionado com sucesso" + novAnunciodeAlteracaodeRotas.toString());

        List<Percurso> listaPercursos = paragem.getPercursos();
        if (listaPercursos == null) {
            listaPercursos = new ArrayList<>();
            paragem.setPercursos(listaPercursos);
        }
        listaPercursos.remove(percurso);
        paragemRepositorio.save(paragem);
        System.out.println("Percurso removido com sucesso");
        System.out.println("Paragem atualizada" + paragem.toString());

        return "redirect:/GestordeFrota/AdicionarAnuncioDeAlteracaoDeRota.html";
    }

    @PostMapping("/removeAnuncioAlteracao")
    public String removerAnuncioAlteracaoDeRota(@RequestParam String idAlteracao) {
        Optional<AnunciodeAlteracaodeRota> optionalAnuncio = anuncioDeAlteracaodeRotaRepositorio
                .findById(idAlteracao.trim());
        if (optionalAnuncio.isPresent()) {
            anuncioDeAlteracaodeRotaRepositorio.delete(optionalAnuncio.get());
            System.out.println("Anuncio removido com sucesso");
        } else {
            System.out.println("Anuncio não encontrado");
        }
        return "redirect:/GestordeFrota/RemoverAnunciosDeAlteracaoDeRota.html";
    }

    @PostMapping("/editarAnuncioAlteracao")
    public String editarAnuncioAlteracaoDeRota(@RequestParam String idAlteracao, @RequestParam String motivo,
            @RequestParam String idParagem, @RequestParam String idPercurso) {
        Optional<AnunciodeAlteracaodeRota> optionalAnuncio = anuncioDeAlteracaodeRotaRepositorio
                .findById(idAlteracao.trim());
        if (optionalAnuncio.isPresent()) {
            AnunciodeAlteracaodeRota anuncio = optionalAnuncio.get();
            anuncio.setMotivo(motivo);

            Optional<Paragem> optionalParagem = paragemRepositorio.findByNParagem(idParagem);
            if (optionalParagem.isPresent()) {
                anuncio.setParagemAlterada(optionalParagem.get());
            } else {
                System.out.println("Paragem não encontrada");
            }

            Optional<Percurso> optionalPercurso = percursoRepositorio.findById(idPercurso);
            if (optionalPercurso.isPresent()) {
                anuncio.setPercursoAlterado(optionalPercurso.get());
            } else {
                System.out.println("Percurso não encontrado");
            }

            anuncioDeAlteracaodeRotaRepositorio.save(anuncio);
            System.out.println("Anuncio atualizado com sucesso");
        } else {
            System.out.println("Anuncio não encontrado");
        }
        return "redirect:/GestordeFrota/EditarAnunciosDeAlteracaoDeRota.html";
    }

    @GetMapping("/listarAnunciosAlteracao")
    public ResponseEntity<List<AnunciodeAlteracaodeRota>> listarAnunciosDeAlteracaoDeRota() {
        List<AnunciodeAlteracaodeRota> anunciosDeAlteracaoDeRota = (List<AnunciodeAlteracaodeRota>) anuncioDeAlteracaodeRotaRepositorio
                .findAll();
        return ResponseEntity.ok(anunciosDeAlteracaoDeRota);
    }

}
