package com.dai2324.prototipodai.Controlo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dai2324.prototipodai.Classes.ListaUtilizadores;
import com.dai2324.prototipodai.Classes.Utilizador;
import com.dai2324.prototipodai.repositorio.UtilizadorRepositorio;
import com.dai2324.prototipodai.Classes.TipoPerfil;
import com.dai2324.prototipodai.repositorio.TipoPerfilRepositorio;

// Controlo de Credenciais - Verifica a Associação de Perfis Especiais
@Controller
public class ControloPerfis {

    @Autowired
    private UtilizadorRepositorio utilizadorRepositorio;

    @Autowired
    private TipoPerfilRepositorio tipoPerfilRepositorio;

    ListaUtilizadores listaUtilizadores = new ListaUtilizadores();

    @PostMapping("/associarPerfilEspecial")
    public String associarPerfilEspecial(@RequestParam String numdocIdentificacao, @RequestParam String email,
            @RequestParam String perfil) {
        Optional<Utilizador> optionalUtilizador = utilizadorRepositorio
                .findByEmailAndNumdocIdentificacao(email, numdocIdentificacao);

        if (optionalUtilizador.isPresent()) {
            Utilizador utilizador = optionalUtilizador.get();

            Optional<TipoPerfil> optionalTipoPerfil = tipoPerfilRepositorio.findByPerfil(perfil);

            if (optionalTipoPerfil.isPresent()) {
                utilizador.addTipoPerfil(optionalTipoPerfil.get());

                utilizadorRepositorio.save(utilizador);
            } else {
                throw new IllegalArgumentException("Perfil inválido: " + perfil);
            }
        }

        return "redirect:/Perfis/AssociarPerfilEspecial.html";
    }

    @PostMapping("/associarPerfilDeCliente")
    public String associarPerfilDeCliente(@RequestParam String numdocIdentificacao) {
        Optional<Utilizador> optionalUtilizador = utilizadorRepositorio.findByNumdocIdentificacao(numdocIdentificacao);

        if (optionalUtilizador.isPresent()) {
            Utilizador utilizador = optionalUtilizador.get();

            Optional<TipoPerfil> optionalTipoPerfil = tipoPerfilRepositorio.findByPerfil("CLIENTE");

            if (optionalTipoPerfil.isPresent()) {
                utilizador.addTipoPerfil(optionalTipoPerfil.get());

                utilizadorRepositorio.save(utilizador);
            } else {
                throw new IllegalArgumentException("Perfil de cliente não encontrado");
            }
        }

        return "redirect:/Perfis/AssociarPerfildeCliente.html";
    }

    @PostMapping("/desassociarPerfil")
    public String desassociarPerfil(@RequestParam String numdocIdentificacao, @RequestParam String perfil) {
        Optional<Utilizador> optionalUtilizador = utilizadorRepositorio.findByNumdocIdentificacao(numdocIdentificacao);

        if (optionalUtilizador.isPresent()) {
            Utilizador utilizador = optionalUtilizador.get();

            Optional<TipoPerfil> optionalTipoPerfil = tipoPerfilRepositorio.findByPerfil(perfil);

            if (optionalTipoPerfil.isPresent()) {
                utilizador.removeTipoPerfil(optionalTipoPerfil.get());

                utilizadorRepositorio.save(utilizador);
            } else {
                throw new IllegalArgumentException("Perfil inválido: " + perfil);
            }
        }

        return "redirect:/Perfis/DesassociarPerfil.html";
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/listarPerfis")
    public ResponseEntity<Set<TipoPerfil>> listarPerfis(@RequestParam String numdocIdentificacao) {
        Optional<Utilizador> optionalUtilizador = utilizadorRepositorio.findByNumdocIdentificacao(numdocIdentificacao);

        Set<TipoPerfil> tipoPerfis = new HashSet<>();

        if (optionalUtilizador.isPresent()) {
            Utilizador utilizador = optionalUtilizador.get();

            tipoPerfis = utilizador.getTipoPerfis();
        }

        return ResponseEntity.ok(tipoPerfis);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/searchPerfil")
    public ResponseEntity<TipoPerfil> searchPerfil(@RequestParam String perfil,
            @RequestParam String numdocIdentificacao) {
        Optional<Utilizador> optionalUtilizador = utilizadorRepositorio.findByNumdocIdentificacao(numdocIdentificacao);

        TipoPerfil perfilEncontrado = null;

        Optional<TipoPerfil> optionalTipoPerfil = tipoPerfilRepositorio.findByPerfil(perfil);

        if (optionalTipoPerfil.isPresent()) {
            TipoPerfil tipoPerfil = optionalTipoPerfil.get();

            if (optionalUtilizador.isPresent()) {
                Utilizador utilizador = optionalUtilizador.get();

                if (utilizador.getTipoPerfis().contains(tipoPerfil)) {
                    perfilEncontrado = tipoPerfil;
                }
            }
        } else {
            throw new IllegalArgumentException("Perfil inválido: " + perfil);
        }

        return ResponseEntity.ok(perfilEncontrado);
    }
}
