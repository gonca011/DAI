package Frontend;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Consola consola = new Consola(5, 10, 12, "Natal", 102, 0);

        int option = -1;
        while (option != 0) {
            System.out.println("Menu:");
            System.out.println("1. Consultar Moedas");
            System.out.println("2. Consultar Promoções Válidas");
            System.out.println("3. Descontar Moedas");
            System.out.println("4. Consultar Dados do Utilizador");
            System.out.println("0. Sair");

            System.out.print("Escolha a opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Moedas: " + consola.calcularMoedas());
                    break;
                case 2:
                    System.out.println("Promoções válidas:\n " + consola.getPromocoes());
                    break;
                case 3:
                    System.out.print("Quantas moedas quer descontar? ");
                    int moedasDescontadas = scanner.nextInt();
                    consola.descontarMoedas(moedasDescontadas);
                    System.out.println("Moedas descontadas!\n Moedas restantes: " + consola.getMoedas());
                    break;
                case 4:
                    System.out.println("Dados do Utilizador:\n" + consola.toString());
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }
}
