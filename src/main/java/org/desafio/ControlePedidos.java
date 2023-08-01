package org.desafio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlePedidos {
    private ControleClientes controleClientes;
    private final List<Pedido> pedidos;

    public ControlePedidos(ControleClientes controleClientes) {
        this.controleClientes = controleClientes;
        this.pedidos = new ArrayList<>();
    }

    public ControlePedidos() {
        this.pedidos = new ArrayList<>();
    }


    public ControlePedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
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

    public void start() {
        int option;
        do {
            option = printMenuOptions();
            switch (option) {
                case 1 -> realizarPedido();
                case 2 -> imprimirQuantidadePedidos();
                case 3 -> imprimirPedidosEncerrados();
                case 4 -> imprimirPedidosEmAndamento();
                case 0 -> {
                    System.out.println("Voltando ao Menu Principal.");
                    return;
                }
                default -> System.out.println("Opção inválida. Digite novamente.");
            }
        } while (true);
    }

    private int printMenuOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("========== Menu Pedidos ==========");
        System.out.println("1. Realizar Pedido");
        System.out.println("2. Imprimir Quantidade de Pedidos");
        System.out.println("3. Imprimir Pedidos Em Andamento");
        System.out.println("4. Imprimir Pedidos Concluídos");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.println("=================================");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public void realizarPedido() {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = selecionarOuCriarCliente(scanner);
        if (cliente == null) {
            System.out.println("Pedido cancelado.");
            return;
        }

        Pedido pedido = new Pedido(cliente);
        pedido.setDataPedido(LocalDate.now());
        adicionarProdutosAoPedido(pedido);
        finalizarPedido(pedido);

        if (controleClientes.findClienteByCpf(cliente.getCpf()) != null) {
            controleClientes.adicionarCliente(cliente);
            cliente.addPedido(pedido);
        }

        pedidos.add(pedido);
        System.out.println("Pedido efetuado com sucesso.");
        System.out.println("Comprovante de Pedido emetindo...");

    }


    private Cliente selecionarOuCriarCliente(Scanner scanner) {
        Cliente cliente = null;
        while (cliente == null) {
            System.out.println();
            System.out.println("Deseja vincular um cliente já cadastrado ou criar um novo cliente?");
            System.out.println("1. Vincular Cliente Já Cadastrado");
            System.out.println("2. Criar Novo Cliente");
            System.out.print("Digite a opção desejada: ");
            int opcaoCliente = getValidInput(scanner, 1, 2);

            if (opcaoCliente == 1) {
                controleClientes.miniListarCliente();
                cliente = controleClientes.procurarCliente();
            } else if (opcaoCliente == 2) {
                cliente = controleClientes.cadastrarNovo();
                if (cliente == null) {
                    System.out.println("Cadastro cancelado.");
                } else if (controleClientes.existeCliente(cliente.getCpf())) {
                    System.out.println("Cliente já cadastrado. Vinculando ao cliente existente.");
                    cliente = controleClientes.findClienteByCpf(cliente.getCpf());
                }
            }
        }
        return cliente;
    }

    private int getValidInput(Scanner scanner, int min, int max) {
        int input;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite novamente: ");
                scanner.next();
            }
            input = scanner.nextInt();
            scanner.nextLine();

            if (input < min || input > max) {
                System.out.println("Opção inválida. Digite novamente: ");
            }
        } while (input < min || input > max);
        return input;
    }

    private void adicionarProdutosAoPedido(Pedido pedido) {
        int opcao;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("===== Produtos Disponíveis =====");
            for (Produto produto : Produto.values()) {
                System.out.println(produto.ordinal() + 1 + ". " + produto.getNome()
                        + " | Preço: R$" + produto.getPreco());
            }
            System.out.println("0. Finalizar Pedido");
            System.out.println("===============================");
            System.out.print("Digite o número do produto (ou 0 para finalizar): ");
            opcao = getValidInput(scanner, 0, Produto.values().length);
            if (opcao > 0) {
                Produto produtoEscolhido = Produto.values()[opcao - 1];
                pedido.adicionarProduto(produtoEscolhido);
                System.out.println("Produto " + produtoEscolhido.getNome() + " adicionado ao pedido.");
            } else if (opcao == 0) {
                System.out.println("Finalizando a seleção de produtos.");
            }
        } while (opcao != 0);
    }

    private void finalizarPedido(Pedido pedido) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("Deseja finalizar o pedido? (S/N): ");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("S")) {
            pedido.calcularValorTotal();
            pedido.setEmAndamento(true);
            pedido.exibirDetalhes();
            System.out.println("Pedido finalizado e concluído com sucesso.");
        } else {
            System.out.println("Pedido não finalizado.");
        }
    }

    public void imprimirQuantidadePedidos() {
        System.out.println("Quantidade de Pedidos: " + pedidos.size());
    }

    public void imprimirPedidosEncerrados() {
        System.out.println();
        System.out.println("===== Pedidos Encerrados =====");
        for (Pedido pedido : pedidos) {
            if (pedido.isEncerrado()) {
                pedido.exibirDetalhes();
            }
        }
    }

    public void imprimirPedidosEmAndamento() {
        System.out.println();
        System.out.println("===== Pedidos Concluídos =====");
        for (Pedido pedido : pedidos) {
            if (pedido.isEmAdamento()) {
                pedido.exibirDetalhes();
            }
        }
    }

}
