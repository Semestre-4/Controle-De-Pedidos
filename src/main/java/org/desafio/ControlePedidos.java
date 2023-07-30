package org.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlePedidos {

    private ControleClientes controleClientes;
    private List<Pedido> pedidos = new ArrayList<>();

    private Scanner scanner;

    public ControlePedidos(ControleClientes controleClientes) {
        this.controleClientes = controleClientes;
        this.pedidos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public ControlePedidos() {
    }

    public ControleClientes getControleClientes() {
        return controleClientes;
    }

    public void setControleClientes(ControleClientes controleClientes) {
        this.controleClientes = controleClientes;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start(){

    }

    private int printMenuOptions() {
        System.out.println();
        System.out.println("========== Menu Pedidos ==========");
        System.out.println("1. Realizar Pedido");
        System.out.println("2. Imprimir Quantidade de Pedidos");
        System.out.println("3. Imprimir Pedidos Encerrados");
        System.out.println("4. Imprimir Pedidos Concluídos");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.println("=================================");
        System.out.print("Digite a opção desejada: ");
        System.out.println();
        return scanner.nextInt();
    }

    public boolean realizarPedido(){
        return true;
    }

    public void imprimirQuantidadePedidos(){}

    public void imprimirPedidosEncerrados() {}

    private void imprimirPedidosConcluidos() {}



}
