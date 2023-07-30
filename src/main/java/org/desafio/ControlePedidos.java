package org.desafio;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlePedidos {

    private List<Pedidos> pedidos = new ArrayList<>();
    
    private Scanner scanner;

    public ControlePedidos() {
        pedidos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public List<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        int option;

        do {
            option = printMenuOptions();

            switch (option) {
                case 1 -> realizarPedido();
                case 2 -> imprimirQuantidadePedidos();
                case 3 -> imprimirPedidosEncerrados();
                case 4 -> imprimirPedidosConcluidos();
                case 0 -> {
                    System.out.println("Saindo do programa...");
                    return;
                }
                default -> System.out.println("Opção inválida. Por favor, tente novamente.");
            }

        } while (true);
    }

    private void imprimirPedidosConcluidos() {
    }

    private void imprimirPedidosEncerrados() {
    }

    private void imprimirQuantidadePedidos() {
    }

    private void realizarPedido() {
    }

    private int printMenuOptions() {
        System.out.println("========== Menu Pedidos ==========");
        System.out.println("1. Realizar Pedido");
        System.out.println("2. Imprimir Quantidade de Pedidos");
        System.out.println("3. Imprimir Pedidos Encerrados");
        System.out.println("4. Imprimir Pedidos Concluídos");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.println("=================================");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }


}
