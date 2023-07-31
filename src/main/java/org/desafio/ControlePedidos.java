package org.desafio;

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
                case 4 -> imprimirPedidosConcluidos();
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
        System.out.println("3. Imprimir Pedidos Encerrados");
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

        pedidos.add(pedido);
        controleClientes.adicionarCliente(cliente); // Add the client to the list in ControleClientes
        cliente.addPedido(pedido); // Update the client's order list
        System.out.println("Pedido realizado com sucesso.");
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
                cliente = cadastrarNovoCliente(scanner);
                if (cliente == null) {
                    System.out.println("Cadastro cancelado.");
                }
            }
        }
        return cliente;
    }

    private Cliente cadastrarNovoCliente(Scanner scanner) {
        Cliente cliente = new Cliente();
        System.out.println("=================================");
        System.out.println("\nDigite o nome (ou digite 0 para cancelar):");
        String inputNome = scanner.nextLine().trim();

        if (inputNome.equals("0")) {
            System.out.println("Cadastro cancelado.");
            return null;
        }

        cliente.setNome(inputNome);

        while (true) {
            System.out.printf("\nInsira o CPF de %s (no formato xxx.xxx.xxx-xx) (ou digite 0 para cancelar):%n",
                    cliente.getNome());
            String cpf = scanner.nextLine().trim();

            if (cpf.equals("0")) {
                System.out.println("Cadastro cancelado.");
                return null;
            }

            if (Cliente.isValidCPF(cpf)) {
                cliente.setCpf(cpf);
                break;
            } else {
                System.out.println("CPF inválido. Por favor, insira um CPF válido.");
            }
        }

        System.out.printf("\nPor favor, insira a quantidade de endereços a serem cadastrados para %s:%n",
                cliente.getNome());
        int numEnderecos = getValidInput(scanner, 1, Integer.MAX_VALUE);
        List<Endereco> enderecos = new ArrayList<>();
        for (int j = 0; j < numEnderecos; j++) {
            Endereco endereco = new Endereco();
            System.out.println("=================================");
            System.out.println("\nInsira a rua do endereço:");
            endereco.setRua(scanner.nextLine());

            System.out.println("\nInsira o número da rua do endereço:");
            int numero = getValidInput(scanner, 1, Integer.MAX_VALUE);
            endereco.setNumero(numero);

            System.out.println("\nInsira o Complemento do endereço:");
            endereco.setComplemento(scanner.nextLine());
            enderecos.add(endereco);
        }
        cliente.setEnderecos(enderecos);
        System.out.println("Cliente cadastrado com sucesso.");
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
            pedido.exibirDetalhes();
            pedido.setEncerrado(true);
            pedido.setConcluido(true);
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
                displayPedido(pedido);
            }
        }
    }

    public void imprimirPedidosConcluidos() {
        System.out.println();
        System.out.println("===== Pedidos Concluídos =====");
        for (Pedido pedido : pedidos) {
            if (pedido.isConcluido()) {
                displayPedido(pedido);
            }
        }
    }

    private void displayPedido(Pedido pedido) {
        System.out.println("Pedido Número: " + pedido.getNumeroPedido());
        System.out.println("Data do Pedido: " + pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Nome do Cliente: " + pedido.getCliente().getNome());
        for (Produto produto : pedido.getProdutos()) {
            System.out.printf("%s - %.2f%n", produto.getNome(), produto.getPreco());
        }
        System.out.printf("Total Pago: %.2f%n", pedido.calcularValorTotal());
        System.out.println("==============================");
    }
}
