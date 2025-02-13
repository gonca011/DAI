package com.dai2324.prototipodai.Controlo;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.dai2324.prototipodai.repositorio.ParagemRepositorio;
import com.dai2324.prototipodai.repositorio.PercursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dai2324.prototipodai.Classes.ListaParagens;
import com.dai2324.prototipodai.Classes.Paragem;
import com.dai2324.prototipodai.Classes.ListaPercursos;
import com.dai2324.prototipodai.Classes.Percurso;

//Controlo de Confirmação - Controlo das ações das Paragens
@Controller
public class ControloParagens {

    ListaParagens listaParagens = new ListaParagens();
    ListaPercursos listaPercursos = new ListaPercursos();

    @Autowired
    private ParagemRepositorio paragemRepositorio;

    @Autowired
    private PercursoRepositorio percursoRepositorio;

    @PostMapping("/addParagem")
    public String adicionarParagem(@RequestParam String nome, @RequestParam String rua, @RequestParam String nParagem) {

        Paragem paragem = new Paragem(nome, rua, nParagem, new ListaPercursos());
        paragemRepositorio.save(paragem);

        return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
    }

    @PostMapping("/adiconarPercursoAParagem")
    public String adicionarPercursoAParagem(@RequestParam String id, @RequestParam String nParagem) {

        Optional<Percurso> percursoOptional = percursoRepositorio.findById(id);
        Optional<Paragem> paragemOptional = paragemRepositorio.findByNParagem(nParagem);

        boolean isParagemExist = paragemOptional.isPresent();
        boolean isPercursoExist = percursoOptional.isPresent();

        if (isParagemExist && isPercursoExist) {
            Paragem paragemExistente = paragemOptional.get();
            paragemExistente.getPercursos().add(percursoOptional.get());
            paragemRepositorio.save(paragemExistente);
        }

        if (!isPercursoExist) {
            System.err.println("Percurso não encontrado");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
    }

    @GetMapping("/paragensDoPercurso/{id}")
    public ResponseEntity<List<Paragem>> getParagensDoPercurso(@PathVariable String id) {

        List<Paragem> allParagens = paragemRepositorio.findAll();

        List<Paragem> filteredParagens = allParagens.stream()
                .filter(paragem -> paragem.getPercursos().stream().anyMatch(percurso -> percurso.getId().equals(id)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredParagens);
    }

    @GetMapping("/paragens")
    public ResponseEntity<List<Paragem>> listarParagens() {
        return ResponseEntity.ok(paragemRepositorio.findAll());
    }

    @PostMapping("/removerPercursoAParagem")
    public String removerPercursoAParagem(@RequestParam String id, @RequestParam String nParagem) {
        Optional<Paragem> optionalParagem = paragemRepositorio.findById(nParagem);
        if (!optionalParagem.isPresent()) {
            System.err.println("Paragem não encontrada");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        Paragem paragem = optionalParagem.get();
        ListaPercursos listaPercursos = paragem.getListaPercursos();
        if (listaPercursos == null) {
            System.err.println("Paragem não tem percursos");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        Optional<Percurso> optionalPercurso = listaPercursos.getpercursos().stream()
                .filter(percurso -> percurso.getId().equals(id))
                .findFirst();

        if (!optionalPercurso.isPresent()) {
            System.err.println("Percurso não encontrado");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        listaPercursos.getpercursos().remove(optionalPercurso.get());
        paragem.setListaPercursos(listaPercursos);
        paragemRepositorio.save(paragem);

        System.out.println("Percurso removido com sucesso" + paragem.toString());
        System.out.println("Percursos restantes na paragem: " + paragem.getListaPercursos().toString());

        return "redirect:/GestordeFrota/RemoverPercursosParagem.html";
    }

    @PostMapping("/alterarPercurso")
    public String alterarPercurso(@RequestParam String oldId, @RequestParam String newId,
            @RequestParam String nParagem) {
        Optional<Paragem> optionalParagem = paragemRepositorio.findById(nParagem);
        if (!optionalParagem.isPresent()) {
            System.err.println("Paragem não encontrada");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        Paragem paragem = optionalParagem.get();
        ListaPercursos listaPercursos = paragem.getListaPercursos();
        if (listaPercursos == null) {
            System.err.println("Paragem não tem percursos");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        Optional<Percurso> optionalOldPercurso = listaPercursos.getpercursos().stream()
                .filter(percurso -> percurso.getId().equals(oldId))
                .findFirst();

        if (!optionalOldPercurso.isPresent()) {
            System.err.println("Percurso antigo não encontrado");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        Optional<Percurso> optionalNewPercurso = percursoRepositorio.findById(newId);
        if (!optionalNewPercurso.isPresent()) {
            System.err.println("Novo percurso não encontrado");
            return "redirect:/GestordeFrota/AdicionarPercursoAParagem.html";
        }

        listaPercursos.getpercursos().remove(optionalOldPercurso.get());
        listaPercursos.getpercursos().add(optionalNewPercurso.get());

        paragemRepositorio.save(paragem);

        System.out.println("Percurso alterado com sucesso" + paragem.toString());
        System.out.println("Percursos na paragem: " + paragem.getListaPercursos().toString());

        return "redirect:/GestordeFrota/AlterarPercursos.html";
    }

    @PostMapping("/editarParagem")
    public String editarParagem(@RequestParam String nome, @RequestParam String rua, @RequestParam String nParagem) {
        Optional<Paragem> optionalParagem = paragemRepositorio.findById(nParagem);
        if (!optionalParagem.isPresent()) {
            System.err.println("Paragem não encontrada");
            return "redirect:/GestordeFrota/EditarParagem.html";
        }

        Paragem paragem = optionalParagem.get();
        paragem.setNome(nome);
        paragem.setRua(rua);
        System.out.println("Paragem editada com sucesso" + paragem.toString());

        paragemRepositorio.save(paragem);

        return "redirect:/GestordeFrota/EditarParagem.html";
    }

    @PostMapping("/removerPercurso")
    public String removerPercurso(@RequestParam String nParagem, @RequestParam String id) {
        Optional<Paragem> optionalParagem = paragemRepositorio.findById(nParagem);
        if (!optionalParagem.isPresent()) {
            System.err.println("Paragem não encontrada");
            return "redirect:/GestordeFrota/RemoverPercurso.html";
        }

        Paragem paragem = optionalParagem.get();
        ListaPercursos listaPercursos = paragem.getListaPercursos();
        if (listaPercursos == null) {
            System.err.println("Paragem não tem percursos");
            return "redirect:/GestordeFrota/RemoverPercurso.html";
        }

        Iterator<Percurso> iterator = listaPercursos.getpercursos().iterator();
        while (iterator.hasNext()) {
            Percurso percurso = iterator.next();

            if (percurso.getId().equals(id)) {
                iterator.remove();
                System.out.println("Percurso removido com sucesso" + percurso.toString());
                break;
            }
        }

        paragemRepositorio.save(paragem);

        return "redirect:/GestordeFrota/RemoverPercurso.html";
    }

    @PostMapping("/removerParagem")
    public String removerParagem(@RequestParam String nParagem) {
        Optional<Paragem> optionalParagem = paragemRepositorio.findByNParagem(nParagem);
        if (!optionalParagem.isPresent()) {
            System.err.println("Paragem não encontrada");
            return "redirect:/GestordeFrota/RemoverParagem.html";
        }

        Paragem paragem = optionalParagem.get();
        paragemRepositorio.delete(paragem);

        return "redirect:/GestordeFrota/RemoverParagem.html";
    }

}
