package Frontend;
import java.time.LocalDate;
import java.util.Scanner;
import Backend.*;

public class Menu {

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sair;
        do {
            System.out.println("===== TUBrewards =====");
            System.out.println("BEM-VINDO!");
            System.out.println("Pronto para ganhar moedas?");
            System.out.println("1. Menu Utilizador");
            System.out.println("0. Sair");
            
            System.out.print("Escolha uma opção: ");
            sair = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (sair) {
                case 1:
                   MenuUtilizador.utilizador(args);
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (sair != 0);
        scanner.close();
    }
}

public class MenuUtilizador {
    public static void utilizador(String[] args) {
        Utilizador utilizador = new Utilizador("email", "password", 100, 5, 150.55); // Exemplo de inicialização de um utilizador
        LocalDate dataInicioPromocao = LocalDate.of(2024, 12, 18);
        LocalDate dataFimPromocao = LocalDate.of(2025, 1, 6);
        Promocao promocao = new Promocao("Promocao de Natal", 25, dataInicioPromocao, dataFimPromocao, 50, 1, null, "Nova Arcada");     // Exemplo de inicialização de uma promoção
        ListaPromocoes listaPromocoes = new ListaPromocoes(); // Exemplo de inicialização de uma lista de promoções
        Viagem viagem1 = new Viagem(6, LocalDate.now(), "Minho Center", "Nova Arcada", 1.55, "Largo dos Penedos", 1, 10);
        Viagem viagem2 = new Viagem(6, LocalDate.now(), "Nova Arcada", "Minho Center", 1.55, "Conde de Agrolongo", 1, 10);
        ListaViagens listaViagens = new ListaViagens(); // Exemplo de inicialização de uma lista de viagens
        listaViagens.adicionarViagem(viagem1);
        listaViagens.adicionarViagem(viagem2);
        listaPromocoes.adicionarPromocao(promocao);
        utilizador.setListaPromocoes(listaPromocoes); // Adicionar a lista de promoções ao utilizador
        ListaViagens listaViagensRealizadas = new ListaViagens(); // Exemplo de inicialização de uma lista de viagens realizadas
        utilizador.setListaViagensRealizadas(listaViagensRealizadas); // Adicionar a lista de viagens realizadas ao utilizador

        
        int escolha;
        do {
            System.out.println("Menu:");
            System.out.println("1. Mostrar Promoções");
            System.out.println("2. Usar Promoção");
            System.out.println("3. Usar Moedas");
            System.out.println("4. Ver Moedas");
            System.out.println("5. Ver Últimos Pontos Ganhos");
            System.out.println("6. Comprar Viagem");
            System.out.println("7. Realizar Viagem");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    utilizador.mostrarTodasAsPromocoes();
                    break;
                case 2:
                    utilizador.mostrarPromocoesDisponiveis();
                    utilizador.usarPromocao();
                    break;
                case 3:
                    System.out.print("Quantas moedas deseja usar: ");
                    int moedasGastas = scanner.nextInt();
                    utilizador.usarMoedas(moedasGastas);
                    break;
                case 4:
                    System.out.println("Moedas: " + utilizador.getNumMoedas());
                    break;
                case 5:
                    utilizador.verUltimosPontosGanhos();
                    break;
                case 6:
                    scanner.nextLine(); // Limpar o buffer do scanner
                    System.out.println("Origem da viagem: ");
                    String origem = scanner.nextLine();
                    System.out.println("Destino da viagem: ");
                    String destino = scanner.nextLine();
                    boolean viagemDisponivel = false;
                    for (Viagem v : listaViagens.getViagens()){
                        if (v.getOrigem().equals(origem) && v.getDestino().equals(destino)){
                            utilizador.comprarViagem(v);
                            ListaViagens viagensCompradas = new ListaViagens();
                            viagensCompradas.adicionarViagem(v);
                            utilizador.setListaViagensCompradas(viagensCompradas);
                            viagemDisponivel = true;
                        } 
                    }
                    if (!viagemDisponivel){
                        System.out.println("Viagem não disponível");
                    }
                    break;
                case 7: 
                    scanner.nextLine(); // Limpar o buffer do scanner
                    System.out.println("Origem da viagem: ");
                    String origemViagem = scanner.nextLine();
                    System.out.println("Destino da viagem: ");
                    String destinoViagem = scanner.nextLine();
                    for (Viagem v : utilizador.getListaViagensCompradas().getViagens()){
                        if (v.getOrigem().equals(origemViagem) && v.getDestino().equals(destinoViagem)){
                            utilizador.getListaViagensRealizadas().adicionarViagem(v);
                            System.out.println("Viagem realizada");
                        } else {
                            System.out.println("Viagem não disponível");
                        }
                    }
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (escolha != 0);
    }
}
 
}
   



   
      
    





