package org.desafio;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ControleClientes controleClientes = new ControleClientes();
        ControlePedidos controlePedidos = new ControlePedidos(controleClientes);

        Scanner scanner = new Scanner(System.in);

        int escolha;
        do {
            // Exibe o menu principal
            exibirMenu();
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1 -> controleClientes.start(); // Executa o controle de clientes
                case 2 -> controlePedidos.start(); // Executa o controle de pedidos
                case 0 -> System.out.println("Saindo do programa...");
                default -> System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (escolha != 0);

        scanner.close();
    }

    // Método para exibir o menu principal
    private static void exibirMenu() {
        System.out.println();
        System.out.println("====== Menu Principal ======");
        System.out.println("1. Controle de Clientes");
        System.out.println("2. Controle de Pedidos");
        System.out.println("0. Sair");
        System.out.println("=================================");
        System.out.print("Digite a opção desejada: ");
    }
}
