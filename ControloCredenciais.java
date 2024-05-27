package com.dai2324.prototipodai.Controlo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.dai2324.prototipodai.Classes.Utilizador;
import com.dai2324.prototipodai.repositorio.LoggedInUtilizadorRepositorio;
import com.dai2324.prototipodai.repositorio.TipoPerfilRepositorio;
import com.dai2324.prototipodai.repositorio.UtilizadorRepositorio;
import jakarta.servlet.http.HttpSession;
import com.dai2324.prototipodai.Classes.LoggedInUtilizador;
import com.dai2324.prototipodai.Classes.TipoPerfil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// Controlo das Credenciais
@Controller
public class ControloCredenciais {

    @Autowired
    private UtilizadorRepositorio utilizadorRepositorio;

    // Controlo da associação de perfil especial
    @Autowired
    private TipoPerfilRepositorio tipoPerfilRepositorio;

    @PostMapping("/submitRegistese")
    public String receberRegistese(@RequestParam String nome, @RequestParam String email,
            @RequestParam String numdocIdentificacao, HttpSession session) {

        processInput1(nome, email, numdocIdentificacao);

        Utilizador newUser = new Utilizador();
        newUser.setNome(nome);
        newUser.setEmail(email);

        List<String> emails = new ArrayList<>();
        emails.add(email);

        newUser.setNumdocIdentificacao(numdocIdentificacao);

        TipoPerfil clientePerfil = tipoPerfilRepositorio.findByPerfil("CLIENTE")
                .orElseThrow(() -> new RuntimeException("TipoPerfil CLIENTE não encontrado"));

        Set<TipoPerfil> tipoPerfis = new HashSet<>();
        tipoPerfis.add(clientePerfil);
        newUser.setTipoPerfis(tipoPerfis);

        utilizadorRepositorio.save(newUser);

        session.setAttribute("email", email);
        session.setAttribute("numdocIdentificacao", numdocIdentificacao);

        return "redirect:/Dados.html";
    }

    @PostMapping("/submitForm")
    public String receberDados(@RequestParam String dataDeNascimento, @RequestParam String nif,
            @RequestParam String dataValidadeDocIdentificacao, @RequestParam String codigoPostal,
            @RequestParam String morada, @RequestParam String porta, @RequestParam String telemovel,
            HttpSession session) {

        String email = (String) session.getAttribute("email");
        String numdocIdentificacao = (String) session.getAttribute("numdocIdentificacao");

        Utilizador utilizador = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email, numdocIdentificacao)
                .orElse(null);

        if (utilizador == null
                || !processInput2(dataDeNascimento, nif, dataValidadeDocIdentificacao, codigoPostal, morada, porta,
                        telemovel)) {
            return "redirect:/Erro2.html";
        }

        utilizador.setDataDeNascimento(dataDeNascimento);
        utilizador.setNif(nif);
        utilizador.setDataValidadeDocIdentificacao(dataValidadeDocIdentificacao);
        utilizador.setCodigoPostal(codigoPostal);
        utilizador.setMorada(morada);
        utilizador.setPorta(porta);
        utilizador.setTelemovel(telemovel);

        utilizadorRepositorio.save(utilizador);

        return "redirect:/Final.html";
    }

    // Controlo da associação de perfil especial
    @PostMapping("/criarMotorista")
    public String criarMotorista(@RequestParam String nome, @RequestParam String email,
            @RequestParam String numdocIdentificacao, HttpSession session) {

        processInput1(nome, email, numdocIdentificacao);
        Utilizador newUser = new Utilizador();
        newUser.setNome(nome);
        newUser.setEmail(email);
        newUser.setNumdocIdentificacao(numdocIdentificacao);
        if (email.toLowerCase().startsWith("motorista")) {
            TipoPerfil motoristaPerfil = tipoPerfilRepositorio.findByPerfil("MOTORISTA")
                    .orElseThrow(() -> new RuntimeException("TipoPerfil MOTORISTA não encontrado"));

            Set<TipoPerfil> tipoPerfis = new HashSet<>();
            tipoPerfis.add(motoristaPerfil);
            newUser.setTipoPerfis(tipoPerfis);
        } else {
            return "Erro: Email inválido";
        }

        utilizadorRepositorio.save(newUser);
        session.setAttribute("email", email);
        session.setAttribute("numdocIdentificacao", numdocIdentificacao);

        return "redirect:/Administrador/CriarMotorista2.html";
    }

    @PostMapping("/criarMotorista2")
    public String receberDadosMotorista(@RequestParam String dataDeNascimento, @RequestParam String nif,
            @RequestParam String dataValidadeDocIdentificacao, @RequestParam String codigoPostal,
            @RequestParam String morada, @RequestParam String porta, @RequestParam String telemovel,
            HttpSession session) {
        String email = (String) session.getAttribute("email");
        String numdocIdentificacao = (String) session.getAttribute("numdocIdentificacao");

        Utilizador utilizador = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email, numdocIdentificacao)
                .orElse(null);

        if (utilizador == null) {
            return "redirect:/Erro1.html";
        }

        if (!processInput2(dataDeNascimento, nif, dataValidadeDocIdentificacao, codigoPostal, morada, porta,
                telemovel)) {
            return "redirect:/Erro2.html";
        }

        utilizador.setDataDeNascimento(dataDeNascimento);
        utilizador.setNif(nif);
        utilizador.setDataValidadeDocIdentificacao(dataValidadeDocIdentificacao);
        utilizador.setCodigoPostal(codigoPostal);
        utilizador.setMorada(morada);
        utilizador.setPorta(porta);
        utilizador.setTelemovel(telemovel);

        utilizadorRepositorio.save(utilizador);

        return "redirect:/Administrador/AdminCriarOutrasContas.html";
    }

    @PostMapping("/criarGestorDeFrota")
    public String criarGestorDeFrota(@RequestParam String nome, @RequestParam String email,
            @RequestParam String numdocIdentificacao, HttpSession session) {

        processInput1(nome, email, numdocIdentificacao); // Verifica se os parametros são válidos
        Utilizador newUser = new Utilizador(); // Cria um novo utilizador
        newUser.setNome(nome); // Define o nome do utilizador
        newUser.setEmail(email); // Define o email do utilizador

        List<String> emails = new ArrayList<>();
        emails.add(email);
        // newUser.setListaEmails(emails); // Adicionar o email à lista de emails do
        // utilizador

        newUser.setNumdocIdentificacao(numdocIdentificacao); // Define o numero de documento de identificação do

        if (email.toLowerCase().startsWith("gf")) {
            TipoPerfil gestorDeFrotaPerfil = tipoPerfilRepositorio.findByPerfil("GESTOR_DE_FROTA")
                    .orElseThrow(() -> new RuntimeException("TipoPerfil GESTOR_DE_FROTA não encontrado"));

            Set<TipoPerfil> tipoPerfis = new HashSet<>();
            tipoPerfis.add(gestorDeFrotaPerfil);
            newUser.setTipoPerfis(tipoPerfis);
        } else {
            return "Erro: Email inválido";
        }

        utilizadorRepositorio.save(newUser);
        session.setAttribute("email", email);
        session.setAttribute("numdocIdentificacao", numdocIdentificacao);

        return "redirect:/Administrador/CriarGestorDeFrota2.html";
    }

    @PostMapping("/criarGestorDeFrota2")
    public String receberDadosGestorDeFrota(@RequestParam String dataDeNascimento, @RequestParam String nif,
            @RequestParam String dataValidadeDocIdentificacao, @RequestParam String codigoPostal,
            @RequestParam String morada, @RequestParam String porta, @RequestParam String telemovel,
            HttpSession session) {
        String email = (String) session.getAttribute("email");
        String numdocIdentificacao = (String) session.getAttribute("numdocIdentificacao");

        Utilizador utilizador = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email, numdocIdentificacao)
                .orElse(null);

        if (utilizador == null) {
            return "redirect:/Erro1.html";
        }

        if (!processInput2(dataDeNascimento, nif, dataValidadeDocIdentificacao, codigoPostal, morada, porta,
                telemovel)) { // Verifica se os parametros são válidos
            return "redirect:/Erro2.html";
        }

        utilizador.setDataDeNascimento(dataDeNascimento);
        utilizador.setNif(nif);
        utilizador.setDataValidadeDocIdentificacao(dataValidadeDocIdentificacao);
        utilizador.setCodigoPostal(codigoPostal);
        utilizador.setMorada(morada);
        utilizador.setPorta(porta);
        utilizador.setTelemovel(telemovel);

        utilizadorRepositorio.save(utilizador);

        return "redirect:/Administrador/AdminCriarOutrasContas.html"; // finalizar o registo
    }

    @PostMapping("/criarCoordenador")
    public String criarCoordenador(@RequestParam String nome, @RequestParam String email,
            @RequestParam String numdocIdentificacao, HttpSession session) {

        processInput1(nome, email, numdocIdentificacao); // Verifica se os parametros são válidos
        Utilizador newUser = new Utilizador(); // Cria um novo utilizador
        newUser.setNome(nome); // Define o nome do utilizador
        newUser.setEmail(email); // Define o email do utilizador

        List<String> emails = new ArrayList<>();
        emails.add(email);
        // newUser.setListaEmails(emails); // Adicionar o email à lista de emails do
        // utilizador

        newUser.setNumdocIdentificacao(numdocIdentificacao); // Define o numero de documento de identificação do

        if (email.toLowerCase().startsWith("coord")) {
            TipoPerfil coordenadorPerfil = tipoPerfilRepositorio.findByPerfil("COORDENADOR")
                    .orElseThrow(() -> new RuntimeException("TipoPerfil COORDENADOR não encontrado"));

            // Set the profile type to COORDENADOR
            Set<TipoPerfil> tipoPerfis = new HashSet<>();
            tipoPerfis.add(coordenadorPerfil);
            newUser.setTipoPerfis(tipoPerfis);
        } else {
            return "Erro: Email inválido";
        }

        utilizadorRepositorio.save(newUser);

        session.setAttribute("email", email);
        session.setAttribute("numdocIdentificacao", numdocIdentificacao);

        return "redirect:/Administrador/CriarCoordenador2.html";
    }

    @PostMapping("/criarCoordenador2")
    public String receberDadosCoordenador(@RequestParam String dataDeNascimento, @RequestParam String nif,
            @RequestParam String dataValidadeDocIdentificacao, @RequestParam String codigoPostal,
            @RequestParam String morada, @RequestParam String porta, @RequestParam String telemovel,
            HttpSession session) {
        String email = (String) session.getAttribute("email");
        String numdocIdentificacao = (String) session.getAttribute("numdocIdentificacao");

        Utilizador utilizador = utilizadorRepositorio.findByEmailAndNumdocIdentificacao(email, numdocIdentificacao)
                .orElse(null);

        if (utilizador == null) {
            return "redirect:/Erro1.html";
        }

        if (!processInput2(dataDeNascimento, nif, dataValidadeDocIdentificacao, codigoPostal, morada, porta,
                telemovel)) { // Verifica se os parametros são válidos
            return "redirect:/Erro2.html";
        }

        utilizador.setDataDeNascimento(dataDeNascimento);
        utilizador.setNif(nif);
        utilizador.setDataValidadeDocIdentificacao(dataValidadeDocIdentificacao);
        utilizador.setCodigoPostal(codigoPostal);
        utilizador.setMorada(morada);
        utilizador.setPorta(porta);
        utilizador.setTelemovel(telemovel);

        utilizadorRepositorio.save(utilizador);

        return "redirect:/Administrador/AdminCriarOutrasContas.html"; // finalizar o registo
    }

    // Funções para verificar se os parametros são válidos
    public boolean processInput1(String nome, String email, String numdocIdentificacao) {
        if (nome == null || nome.isEmpty()) {
            System.out.println("Nome inválido");
            return false;
        }

        if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("Email inválido");
            return false;
        }

        if (numdocIdentificacao == null || numdocIdentificacao.isEmpty()) {
            System.out.println("NumdocIdentificacao inválido");
            return false;
        }

        System.out.println("Válido");
        return true;
    }

    public boolean processInput2(String dataDeNascimento, String nif, String dataValidadeDocIdentificacao,
            String codigoPostal, String morada, String porta, String telemovel) {
        if (dataDeNascimento != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            try {
                Date date = sdf.parse(dataDeNascimento);
            } catch (ParseException e) {
                System.out.println("Data de Nacimento inválida");
                return false;
            }
        }

        if (nif != null && !nif.matches("[125689][0-9]{8}")) {
            System.out.println("NIF inválido");
            return false;
        }

        if (dataValidadeDocIdentificacao != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date date = sdf.parse(dataValidadeDocIdentificacao);
            } catch (ParseException e) {
                System.out.println("Data de Validade do Documento de Identificação inválida");
                return false;
            }
        }

        if (codigoPostal != null && !codigoPostal.matches("[0-9]{4}-[0-9]{3}")) {
            System.out.println("Código Postal inválido");
            return false;
        }

        if (morada != null && morada.isEmpty()) {
            System.out.println("Morada inválida");
            return false;
        }

        if (porta != null && porta.isEmpty()) {
            System.out.println("Porta inválida");
            return false;
        }

        if (telemovel != null && !telemovel.matches("[9][1236][0-9]{7}")) {
            System.out.println("Telemóvel inválido");
            return false;
        }

        return true;
    }

    @Autowired
    private LoggedInUtilizadorRepositorio loggedInUtilizadorRepository;

    @PostMapping("/apagarLinhaCliente")
    public ResponseEntity<Void> apagarLoginCliente() {
        List<LoggedInUtilizador> loggedInUtilizadores = loggedInUtilizadorRepository.findAll();
        if (!loggedInUtilizadores.isEmpty()) {
            LoggedInUtilizador userToDelete = loggedInUtilizadores.get(0); 
            loggedInUtilizadorRepository.delete(userToDelete);
            System.out.println("Apagou a linha do cliente" + userToDelete.getNome() + " ");
        } else {
            System.out.println("Sem utilizadores para remover");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/apagarLinhaMotorista")
    public ResponseEntity<Void> apagarLoginMotorista() {
        List<LoggedInUtilizador> loggedInMotoristas = loggedInUtilizadorRepository.findByTipoPerfis_Perfil("MOTORISTA");
        if (!loggedInMotoristas.isEmpty()) {
            LoggedInUtilizador motoristaToDelete = loggedInMotoristas.get(0); 
            loggedInUtilizadorRepository.delete(motoristaToDelete);
            System.out.println("Apagou a linha do motorista");
        } else {
            System.out.println("Sem motoristas para remover");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/apagarLinhaGestorFrota")
    public ResponseEntity<Void> apagarLoginGestorFrota() {
        List<LoggedInUtilizador> loggedInGestoresFrota = loggedInUtilizadorRepository
                .findByTipoPerfis_Perfil("GESTOR_DE_FROTA");
        if (!loggedInGestoresFrota.isEmpty()) {
            LoggedInUtilizador gestorFrotaToDelete = loggedInGestoresFrota.get(0); 
            loggedInUtilizadorRepository.delete(gestorFrotaToDelete);
            System.out.println("Apagou a linha do gestor de frota");
        } else {
            System.out.println("Sem gestores de frota para remover");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/apagarLinhaAdministrador")
    public ResponseEntity<Void> apagarLoginAdministrador() {
        List<LoggedInUtilizador> loggedInAdministradores = loggedInUtilizadorRepository
                .findByTipoPerfis_Perfil("ADMINISTRADOR");
        if (!loggedInAdministradores.isEmpty()) {
            LoggedInUtilizador administradorToDelete = loggedInAdministradores.get(0); 
            loggedInUtilizadorRepository.delete(administradorToDelete);
            System.out.println("Apagou a linha do administrador");
        } else {
            System.out.println("Sem administradores para remover");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/eliminarConta")
    public String eliminarConta(@RequestParam String numdocIdentificacao) {
        System.out.println("A eliminar conta" + numdocIdentificacao);
        Optional<Utilizador> userToDelete = utilizadorRepositorio.findByNumdocIdentificacao(numdocIdentificacao);
        if (userToDelete.isPresent()) {
            System.out.println("Conta encontrada");
            utilizadorRepositorio.delete(userToDelete.get());
            System.out.println("Conta eliminada");
        } else {
            System.out.println("Não encontrado numdocIdentificacao: " + numdocIdentificacao);
        }
        return "redirect:/Paginainicial.html";
    }

    @GetMapping("/mostrarDadosAdministrador")
    public ResponseEntity<List<LoggedInUtilizador>> mostrarDadosAdministrador() {
        List<LoggedInUtilizador> administradores = loggedInUtilizadorRepository
                .findByTipoPerfis_Perfil("ADMINISTRADOR");
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }

    @GetMapping("/mostrarDadosCliente")
    public ResponseEntity<List<LoggedInUtilizador>> mostrarDadosCliente() {
        List<LoggedInUtilizador> clientes = loggedInUtilizadorRepository.findByTipoPerfis_Perfil("CLIENTE");
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

/* 
--------------------------------------------A não funcionar Corretamente -----------------------------------------------------
    @PostMapping("/editarConta1")
    public String editarConta1(@RequestParam String nome, @RequestParam String dataNascimento,
            @RequestParam String nif, @RequestParam String numdocIdentificacao,
            HttpSession session) {

        String oldNumdocIdentificacao = (String) session.getAttribute("numdocIdentificacao");

        Utilizador utilizador = utilizadorRepositorio.findByNumdocIdentificacao(oldNumdocIdentificacao)
                .orElse(null);

        if (utilizador == null) {
            return "redirect:/Erro.html";
        }

        utilizador.setNome(nome);
        utilizador.setDataDeNascimento(dataNascimento);
        utilizador.setNif(nif);
        utilizador.setNumdocIdentificacao(numdocIdentificacao);

        utilizadorRepositorio.save(utilizador);

        session.setAttribute("numdocIdentificacao", numdocIdentificacao);

        return "redirect:/Dados.html";
    }

    @PostMapping("/editarConta2")
    public String editarConta2(@RequestParam String Código, @RequestParam String Morada, HttpSession session) {

        String numdocIdentificacao = (String) session.getAttribute("numdocIdentificacao");

        Utilizador utilizador = utilizadorRepositorio.findByNumdocIdentificacao(numdocIdentificacao)
                .orElse(null);

        if (utilizador == null) {
            return "redirect:/Erro.html";
        }

        utilizador.setCodigoPostal(Código);
        utilizador.setMorada(Morada);

        utilizadorRepositorio.save(utilizador);

        return "redirect:/Dados.html";
    }

    @PostMapping("/editarConta3")
    public String editarConta3(@RequestParam String Telemovel, @RequestParam String Email, HttpSession session) {

        String numdocIdentificacao = (String) session.getAttribute("numdocIdentificacao");

        Utilizador utilizador = utilizadorRepositorio.findByNumdocIdentificacao(numdocIdentificacao)
                .orElse(null);

        if (utilizador == null) {
            return "redirect:/Erro.html";
        }

        utilizador.setTelemovel(Telemovel);
        utilizador.setEmail(Email);

        utilizadorRepositorio.save(utilizador);

        session.setAttribute("email", Email);

        return "redirect:/Dados.html";
    }
*/
}
