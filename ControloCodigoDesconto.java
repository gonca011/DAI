package com.dai2324.prototipodai.Controlo;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.dai2324.prototipodai.Classes.CodigoDesconto;
import com.dai2324.prototipodai.repositorio.CodigoDescontoRepositorio;

// Controlo da Gestão de Pontos
@Controller
public class ControloCodigoDesconto {

    @Autowired
    private CodigoDescontoRepositorio codigoDescontoRepositorio;

    @PostMapping("/addCodigoDesconto")
    public String adicionarCodigoDesconto(@RequestParam String nome, @RequestParam String descricao,
            @RequestParam String criterioDeAtribuicao, @RequestParam String id) {
        CodigoDesconto codigoDesconto = new CodigoDesconto(nome, descricao, criterioDeAtribuicao, id);

        if (!codigoDescontoRepositorio.existsById(id)) {
            codigoDescontoRepositorio.save(codigoDesconto);
            System.out.println("Código de Desconto adicionado com sucesso" + codigoDesconto.toString());
        }

        return "redirect:/Coordenador/AdicionarCodigoDesconto.html";
    }

    @PostMapping("/removeCodigoDesconto")
    public String removerCodigoDesconto(@RequestParam String id) {
        Optional<CodigoDesconto> optionalCodigoDesconto = codigoDescontoRepositorio.findById(id.trim());

        if (optionalCodigoDesconto.isPresent()) {
            CodigoDesconto codigoDesconto = optionalCodigoDesconto.get();
            codigoDescontoRepositorio.delete(codigoDesconto);
            System.out.println("Código de Desconto removido com sucesso" + codigoDesconto.toString());
        } else {
            System.out.println("Código de Desconto a remover não encontrado:" + id);
        }

        return "redirect:/Coordenador/RemoverCodigoDesconto.html";
    }

    @PostMapping("/editarCodigoDesconto")
    public String editarCodigoDesconto(@RequestParam String id, @RequestParam String descricao,
            @RequestParam String criterioDeAtribuicao, @RequestParam String nome) {
        Optional<CodigoDesconto> optionalCodigoDesconto = codigoDescontoRepositorio.findById(id.trim());

        if (optionalCodigoDesconto.isPresent()) {
            CodigoDesconto codigoDesconto = optionalCodigoDesconto.get();
            codigoDesconto.setDescricao(descricao);
            codigoDesconto.setCriterioDeAtribuicao(criterioDeAtribuicao);
            codigoDesconto.setNome(nome);
            codigoDescontoRepositorio.save(codigoDesconto);
            System.out.println("Código de Desconto Novo:" + codigoDesconto.toString());
        } else {
            System.out.println("Código de Desconto a editar não encontrado:" + id);
        }

        return "redirect:/Coordenador/EditarCodigoDesconto.html";
    }

    @GetMapping("/listarCodigoDesconto")
    public ResponseEntity<List<CodigoDesconto>> listarCodigoDesconto() {
        List<CodigoDesconto> codigosDesconto = (List<CodigoDesconto>) codigoDescontoRepositorio.findAll();
        return ResponseEntity.ok(codigosDesconto);
    }
}
