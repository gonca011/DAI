package com.dai2324.prototipodai.Controlo;

import com.dai2324.prototipodai.Classes.LoggedInUtilizador;
import com.dai2324.prototipodai.Classes.Utilizador;
import com.dai2324.prototipodai.repositorio.UtilizadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dai2324.prototipodai.repositorio.LoggedInUtilizadorRepositorio;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

// Controlo de Credenciais
@Controller
public class ControloInicioSessao {

    @Autowired
    private UtilizadorRepositorio utilizadorRepositorio;

    @Autowired
    LoggedInUtilizadorRepositorio loggedInUtilizadorRepositorio;

    public boolean verificarCredenciais(String email, String numdocIdentificacao) {
        Optional<Utilizador> utilizadorDB = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email,
                numdocIdentificacao);

        if (utilizadorDB.isPresent()) {
            LoggedInUtilizador loggedInUtilizador = new LoggedInUtilizador();
            Utilizador utilizador = utilizadorDB.get();
            loggedInUtilizador.setNif(utilizador.getNif());
            loggedInUtilizador.setNome(utilizador.getNome());
            loggedInUtilizador.setEmail(utilizador.getEmail());
            loggedInUtilizador.setNumdocidentificacao(utilizador.getNumdocIdentificacao());
            loggedInUtilizador.setDatadenascimento(utilizador.getDataDeNascimento());
            loggedInUtilizador.setDatavalidadedocidentificacao(utilizador.getDataValidadeDocIdentificacao());
            loggedInUtilizador.setCodigopostal(utilizador.getCodigoPostal());
            loggedInUtilizador.setMorada(utilizador.getMorada());
            loggedInUtilizador.setPorta(utilizador.getPorta());
            loggedInUtilizador.setTelemovel(utilizador.getTelemovel());
            loggedInUtilizador.setPontos(utilizador.getPontos());

            loggedInUtilizador.setTipoPerfis(new HashSet<>(utilizador.getTipoPerfis()));

            loggedInUtilizadorRepositorio.save(loggedInUtilizador);

            return true;
        }

        return false;
    }

    public boolean verificarCredenciaisAdministrador(String email, String numdocIdentificacao) {
        Optional<Utilizador> admin = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email,
                numdocIdentificacao);

        if (admin.isPresent()) {

            boolean isAdmin = admin.get().getTipoPerfis().stream()
                    .anyMatch(tipoPerfil -> "ADMINISTRADOR".equals(tipoPerfil.getPerfil()));

            if (isAdmin || admin.get().getEmail().startsWith("admin")) {
                LoggedInUtilizador loggedInUtilizador = new LoggedInUtilizador();
                Utilizador utilizador = admin.get();
                loggedInUtilizador.setNif(utilizador.getNif());
                loggedInUtilizador.setNome(utilizador.getNome());
                loggedInUtilizador.setEmail(utilizador.getEmail());
                loggedInUtilizador.setNumdocidentificacao(utilizador.getNumdocIdentificacao());
                loggedInUtilizador.setDatadenascimento(utilizador.getDataDeNascimento());
                loggedInUtilizador.setDatavalidadedocidentificacao(utilizador.getDataValidadeDocIdentificacao());
                loggedInUtilizador.setCodigopostal(utilizador.getCodigoPostal());
                loggedInUtilizador.setMorada(utilizador.getMorada());
                loggedInUtilizador.setPorta(utilizador.getPorta());
                loggedInUtilizador.setTelemovel(utilizador.getTelemovel());
                loggedInUtilizador.setPontos(utilizador.getPontos());

                loggedInUtilizador.setTipoPerfis(new HashSet<>(utilizador.getTipoPerfis()));

                loggedInUtilizadorRepositorio.save(loggedInUtilizador);

                return true;
            }
        }

        return false;
    }

    public boolean verificarCredenciaisCoordenador(String email, String numdocIdentificacao) {
        Optional<Utilizador> coordenador = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email,
                numdocIdentificacao);

        if (coordenador.isPresent()) {
            boolean isCoordinator = coordenador.get().getTipoPerfis().stream()
                    .anyMatch(tipoPerfil -> "COORDENADOR".equals(tipoPerfil.getPerfil()));

            if (isCoordinator || coordenador.get().getEmail().startsWith("coord")) {
                LoggedInUtilizador loggedInUtilizador = new LoggedInUtilizador();
                Utilizador utilizador = coordenador.get();
                loggedInUtilizador.setNif(utilizador.getNif());
                loggedInUtilizador.setNome(utilizador.getNome());
                loggedInUtilizador.setEmail(utilizador.getEmail());
                loggedInUtilizador.setNumdocidentificacao(utilizador.getNumdocIdentificacao());
                loggedInUtilizador.setDatadenascimento(utilizador.getDataDeNascimento());
                loggedInUtilizador.setDatavalidadedocidentificacao(utilizador.getDataValidadeDocIdentificacao());
                loggedInUtilizador.setCodigopostal(utilizador.getCodigoPostal());
                loggedInUtilizador.setMorada(utilizador.getMorada());
                loggedInUtilizador.setPorta(utilizador.getPorta());
                loggedInUtilizador.setTelemovel(utilizador.getTelemovel());
                loggedInUtilizador.setPontos(utilizador.getPontos());

                loggedInUtilizador.setTipoPerfis(new HashSet<>(utilizador.getTipoPerfis()));

                loggedInUtilizadorRepositorio.save(loggedInUtilizador);

                return true;
            }
        }

        return false;
    }

    public boolean verificarCredenciaisGestorDeFrota(String email, String numdocIdentificacao) {
        Optional<Utilizador> gestorDeFrota = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email,
                numdocIdentificacao);

        if (gestorDeFrota.isPresent()) {
            boolean isFleetManager = gestorDeFrota.get().getTipoPerfis().stream()
                    .anyMatch(tipoPerfil -> "GESTOR_DE_FROTA".equals(tipoPerfil.getPerfil()));

            if (isFleetManager || gestorDeFrota.get().getEmail().startsWith("gf")) {
                LoggedInUtilizador loggedInUtilizador = new LoggedInUtilizador();
                Utilizador utilizador = gestorDeFrota.get();
                loggedInUtilizador.setNif(utilizador.getNif());
                loggedInUtilizador.setNome(utilizador.getNome());
                loggedInUtilizador.setEmail(utilizador.getEmail());
                loggedInUtilizador.setNumdocidentificacao(utilizador.getNumdocIdentificacao());
                loggedInUtilizador.setDatadenascimento(utilizador.getDataDeNascimento());
                loggedInUtilizador.setDatavalidadedocidentificacao(utilizador.getDataValidadeDocIdentificacao());
                loggedInUtilizador.setCodigopostal(utilizador.getCodigoPostal());
                loggedInUtilizador.setMorada(utilizador.getMorada());
                loggedInUtilizador.setPorta(utilizador.getPorta());
                loggedInUtilizador.setTelemovel(utilizador.getTelemovel());
                loggedInUtilizador.setPontos(utilizador.getPontos());

                loggedInUtilizador.setTipoPerfis(new HashSet<>(utilizador.getTipoPerfis()));

                loggedInUtilizadorRepositorio.save(loggedInUtilizador);

                return true;
            }
        }

        return false;
    }

    public boolean verificarCredenciaisMotorista(String email, String numdocIdentificacao) {
        Optional<Utilizador> motorista = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email,
                numdocIdentificacao);

        if (motorista.isPresent() && motorista.get().getEmail().startsWith("mot")) {
            boolean isDriver = motorista.get().getTipoPerfis().stream()
                    .anyMatch(tipoPerfil -> "MOTORISTA".equals(tipoPerfil.getPerfil()));

            if (isDriver) {
                LoggedInUtilizador loggedInUtilizador = new LoggedInUtilizador();
                Utilizador utilizador = motorista.get();
                loggedInUtilizador.setNif(utilizador.getNif());
                loggedInUtilizador.setNome(utilizador.getNome());
                loggedInUtilizador.setEmail(utilizador.getEmail());
                loggedInUtilizador.setNumdocidentificacao(utilizador.getNumdocIdentificacao());
                loggedInUtilizador.setDatadenascimento(utilizador.getDataDeNascimento());
                loggedInUtilizador.setDatavalidadedocidentificacao(utilizador.getDataValidadeDocIdentificacao());
                loggedInUtilizador.setCodigopostal(utilizador.getCodigoPostal());
                loggedInUtilizador.setMorada(utilizador.getMorada());
                loggedInUtilizador.setPorta(utilizador.getPorta());
                loggedInUtilizador.setTelemovel(utilizador.getTelemovel());
                loggedInUtilizador.setPontos(utilizador.getPontos());

                loggedInUtilizador.setTipoPerfis(new HashSet<>(utilizador.getTipoPerfis()));

                loggedInUtilizadorRepositorio.save(loggedInUtilizador);

                return true;
            }
        }

        return false;
    }

    @PostMapping("/submitInicioSessao") // Lê o HTML InicioSessao
    public String receberInicioSessao(@RequestParam String email, @RequestParam String numdocIdentificacao) {
        email = email.trim(); // Remove os espaços do email
        numdocIdentificacao = numdocIdentificacao.trim(); // Remove os espaços do numdocIdentificacao

        System.out.println("Email: " + email);
        System.out.println("NumdocIdentificacao: " + numdocIdentificacao);

        if (verificarCredenciaisAdministrador(email, numdocIdentificacao)) {
            return "redirect:/Administrador/PaginaAdministrador.html";
        } else if (verificarCredenciaisCoordenador(email, numdocIdentificacao)) {
            return "redirect:/Coordenador/PaginaCoordenador.html";
        } else if (verificarCredenciaisGestorDeFrota(email, numdocIdentificacao)) {
            return "redirect:/GestordeFrota/PaginaGestorDeFrota.html";
        } else if (verificarCredenciaisMotorista(email, numdocIdentificacao)) {
            return "redirect:/Motorista/PaginaMotorista.html";
        } else if (verificarCredenciais(email, numdocIdentificacao)) {
            return "redirect:/Utilizador/Paginautilizador.html";
        } else {
            return "redirect:/Errologin.html";
        }
    }

    @GetMapping("/admNome")
    @ResponseBody
    public List<LoggedInUtilizador> getLoggedInAdmin() {
        return loggedInUtilizadorRepositorio
                .findByTipoPerfis_Perfil("ADMINISTRADOR");
    }
    
    @GetMapping("/coordNome")
    @ResponseBody
    public List<LoggedInUtilizador> getLoggedInCoord() {
        return loggedInUtilizadorRepositorio
                .findByTipoPerfis_Perfil("COORDENADOR");
    }
    
    @GetMapping("/gfNome")
    @ResponseBody
    public List<LoggedInUtilizador> getLoggedInGf() {
        return loggedInUtilizadorRepositorio
                .findByTipoPerfis_Perfil("GESTOR_DE_FROTA");
    }
    
    @GetMapping("/motNome")
    @ResponseBody
    public List<LoggedInUtilizador> getLoggedInMot() {
        return loggedInUtilizadorRepositorio
                .findByTipoPerfis_Perfil("MOTORISTA");
    }
    
    @GetMapping("/cliNome")
    @ResponseBody
    public List<LoggedInUtilizador> getLoggedInCli() {
        return loggedInUtilizadorRepositorio
                .findByTipoPerfis_Perfil("CLIENTE");
    }

    @GetMapping("/getPontos")
    @ResponseBody
    public int getLoggedInUserPoints() {
        List<LoggedInUtilizador> loggedInUtilizadores = loggedInUtilizadorRepositorio.findByTipoPerfis_Perfil("CLIENTE");
        if (!loggedInUtilizadores.isEmpty()) {
            return loggedInUtilizadores.get(0).getPontos();
        }
        return -1;
    }

}