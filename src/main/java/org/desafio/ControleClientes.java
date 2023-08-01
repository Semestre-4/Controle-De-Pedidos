package org.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class ControleClientes {

    private List<Cliente> clientes = new ArrayList<>();

    List<Endereco> enderecos = new ArrayList<>();

    private ControlePedidos controlePedidos;
    private Scanner scanner;

    public ControleClientes(ControlePedidos controlePedidos) {
        this.clientes = new ArrayList<>();
        this.controlePedidos = controlePedidos;
        scanner = new Scanner(System.in);
    }

    public ControleClientes(List<Cliente> clientes, ControlePedidos controlePedidos) {
        this.clientes = clientes;
        this.controlePedidos = controlePedidos;
    }

    public ControleClientes() {
        clientes = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public List<Cliente> getPedidos() {
        return clientes;
    }

    public void setPedidos(List<Cliente> clientes) {
        this.clientes= clientes;
    }

    public ControlePedidos getControlePedidos() {
        return controlePedidos;
    }

    public void setControlePedidos(ControlePedidos controlePedidos) {
        this.controlePedidos = controlePedidos;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void start() {
        int option;
        do {
            option = printMenuOptions();

            switch (option) {
                case 1 -> cadastrarNovo();
                case 2 -> listarClientes();
                case 3 -> procurarCliente();
                case 4 -> editarCliente();
                case 0 -> {
                    System.out.println("Saindo do programa...");
                    return;
                }
                default -> System.out.println("Opção inválida. Por favor, tente novamente.");
            }

        } while (true);
    }


    public void miniListarCliente() {
        System.out.println("\n======== Lista de Clientes ========");
        for (Cliente cliente : clientes) {
            System.out.println();
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("===============================");
        }
    }

    /**
     * Searches for a client by their CPF and displays their information and associated orders.
     */
    public Cliente procurarCliente() {

            System.out.println("\nDigite o CPF do cliente desejado:");
            String cpfBusca = scanner.next();

            Cliente clienteEncontrado = findClienteByCpf(cpfBusca);

            if (clienteEncontrado != null) {
                displayClienteInfo(clienteEncontrado);
                displayPedidosDoCliente(clienteEncontrado);
            } else {
                System.out.println("Cliente não encontrado! Tente novamente.");
            }

            return clienteEncontrado;
    }

    /**
     * Finds a client by their CPF.
     *
     * @param cpf The CPF to search for.
     * @return The found Cliente or null if not found.
     */
    public Cliente findClienteByCpf(String cpf) {
        return clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    /**
     * Displays the information of a given cliente.
     *
     * @param cliente The Cliente to display.
     */
    private void displayClienteInfo(Cliente cliente) {
        System.out.println("======== Cliente Encontrado ========");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println("======== Endereços ========");
        for (Endereco endereco : cliente.getEnderecos()) {
            System.out.println("===============================");
            System.out.println("Rua: " + endereco.getRua());
            System.out.println("Número: " + endereco.getNumero());
            String complemento = endereco.getComplemento();
            System.out.println("Complemento: " + (complemento.isEmpty() ? "N/A" : complemento));
        }
    }

    /**
     * Displays the orders associated with the given cliente.
     *
     * @param cliente The Cliente whose orders to display.
     */
    private void displayPedidosDoCliente(Cliente cliente) {
        List<Pedido> pedidosDoCliente = new ArrayList<>();
        List<Pedido> pedidos = controlePedidos.getPedidos();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().equals(cliente)) {
                pedidosDoCliente.add(pedido);
            }
        }

        if (!pedidosDoCliente.isEmpty()) {
            System.out.println("======== Pedidos do Cliente ========");
            for (Pedido pedido : pedidosDoCliente) {
                System.out.println("ID do Pedido: " + pedido.getNumeroPedido());
                System.out.println("Data do Pedido: " + pedido.getDataPedido());
                System.out.println("Pedidos: " + pedido.getProdutos());
                System.out.println("===============================");
            }
        } else {
            System.out.println("O cliente ainda não fez nenhum pedido.");
        }
    }

    private List<Pedido> getPedidosDoCliente(Cliente cliente, List<Pedido> pedidos) {
        return pedidos.stream()
                .filter(pedido -> pedido.getCliente().equals(cliente))
                .collect(Collectors.toList());
    }

    public void listarClientes() {
        System.out.println("\n======== Lista de Clientes ========");
        for (Cliente cliente : clientes) {
            System.out.println();
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Telefone: " + cliente.getTelefone());

            // List of Enderecos
            System.out.println();
            System.out.println("======== Endereços ========");
            List<Endereco> enderecoList = cliente.getEnderecos();
            if (enderecoList != null && !enderecoList.isEmpty()) {
                for (int i = 0; i < enderecoList.size(); i++) {
                    Endereco endereco = enderecoList.get(i);
                    System.out.printf("===============%d================%n", i);
                    System.out.println("Rua: " + endereco.getRua());
                    System.out.println("Número: " + endereco.getNumero());
                    String complemento = endereco.getComplemento();
                    System.out.println("Complemento: " + (complemento.isEmpty() ? "N/A" : complemento));
                }
            } else {
                System.out.println("Nenhum endereço cadastrado.");
            }

            // List of Pedidos
            System.out.println("\n======== Histórico de Pedidos ========");
            List<Pedido> pedidoList = cliente.getPedidos();
            if (pedidoList != null && !pedidoList.isEmpty()) {
                for (int i = 0; i < pedidoList.size(); i++) {
                    Pedido pedido = pedidoList.get(i);
                    System.out.printf("===============%d================%n", i);
                    System.out.println("Data do Pedido: " + pedido.getDataPedido());
                    System.out.println("Produtos Comprados: " + pedido.getProdutos());
                }
            } else {
                System.out.println("Nenhum pedido registrado.");
            }
        }
    }

    public Cliente cadastrarNovo() {
        Cliente cliente = new Cliente();
        System.out.println("=================================");
        System.out.println("\nDigite o nome (ou digite 0 para cancelar):");
        String inputNome = scanner.nextLine().trim();

        if (inputNome.equals("0")) {
            System.out.println("Cadastro cancelado.");
            return null;
        }

        cliente.setNome(inputNome);

        String cpf;
        while (true) {
            System.out.printf("\nInsira o CPF de %s (no formato xxx.xxx.xxx-xx) (ou digite 0 para cancelar):%n",
                    cliente.getNome());
            cpf = scanner.nextLine();

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

        System.out.println("=================================");
        System.out.println("Digite o Telefone: ");
        cliente.setTelefone( scanner.next());


        System.out.println("=================================");
        System.out.println("Digite a quantidade de endereços.");

        int numEnderecos = getValidInput(scanner, 1, Integer.MAX_VALUE);
        List<Endereco> enderecos = cadastrarEnderecos(numEnderecos);

        cliente.setEnderecos(enderecos);
        clientes.add(cliente);

        System.out.println("===============================");
        System.out.println("Cliente cadastrado com sucesso.");
        System.out.println("===============================");

        return cliente;
    }

    public int getValidInput(Scanner scanner, int min, int max) {
        int input;
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite novamente: ");
                scanner.next();
            }
            input = scanner.nextInt();
            scanner.nextLine();

            if (input < min || input > max) {
                System.out.println("Opção inválida. Digite novamente: ");
            } else {
                break;
            }
        }
        return input;
    }

    private List<Endereco> cadastrarEnderecos(int numEnderecos) {
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
        return enderecos;
    }

    private static int printMenuOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("========== Menu Cliente ==========");
        System.out.println("1. Cadastrar Novo Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Buscar Cliente");
        System.out.println("4. Editar Cliente");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.println("=================================");
        System.out.print("Digite a opção desejada: ");
        int option;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            option = -1;
        }
        return option;
    }

    public boolean existeCliente(String cpf) {
        return clientes.stream().anyMatch(cliente -> cliente.getCpf().equals(cpf));
    }

    public void adicionarCliente(Cliente cliente) {
        if (!existeCliente(cliente.getCpf())) {
            clientes.add(cliente);
        }
    }


    public void editarCliente(){
    String busca;


        System.out.println("\n|===== Digite o CPF ===== |");
        busca = scanner.next();

        for (int i = 0; i < clientes.size(); i++){
        int opcao;



        if (clientes.get(i).getCpf().equals(busca)){

            System.out.println("| ============================= |");
            System.out.println("| Nome: "+ clientes.get(i).getNome() + " | ");
            System.out.println("| CPF: "+ clientes.get(i).getCpf() + " | ");
            System.out.println("| Telefone: "+ clientes.get(i).getTelefone() + " | ");
            System.out.println("| ============================= |");

            System.out.println("| ========== Endereço ========= |");
            for (int x = 0; x < clientes.get(i).getEnderecos().size(); x++){
                System.out.println("| Rua: "+ clientes.get(i).getEnderecos().get(x).getRua()+ " | ");
                System.out.println("| Num: "+ clientes.get(i).getEnderecos().get(x).getNumero() + " | ");
                System.out.println("| Complemento: "+ clientes.get(i).getEnderecos().get(x).getComplemento() + " | ");
                System.out.println("| ============================= |");}


            do {

                System.out.println("| ======================================= |");
                System.out.println("| =============    Opções    ============ |");
                System.out.println("| ========== 1: Editar  Cliente ========= |");
                System.out.println("| ========== 2: Editar Endereço ========= |");
                System.out.println("| ===========     0: Voltar    ========== |");
                System.out.println("| ======================================= |");
                opcao = scanner.nextInt();

                if (opcao == 1){

                    editarDadosCliente(i);

                }
                if (opcao == 2){

                    editarEndereco(i);

                }
            }while (opcao != 0);

        }else{
            System.out.println("| ======== Não encotrado! ======== |");
        }
    }}




    private void editarDadosCliente( int i) {

        System.out.println("\n|===== Digite o nome ===== |");
        String newnome = scanner.next();

        System.out.println("\n|===== Digite o CPF ===== |");
        String newcpf = scanner.next();

        System.out.println("\n|===== Digite o Telefone ===== |");
        String newtel = scanner.next();

        clientes.get(i).setNome(newnome);
        clientes.get(i).setCpf(newcpf);
        clientes.get(i).setTelefone(newtel);


    }

    private void editarEndereco(int i) {
        System.out.println("\n|===== Digite a rua ===== |");
        String newrua = scanner.next();

        System.out.println("\n|===== Digite o número ===== |");
        int newnum = scanner.nextInt();

        System.out.println("\n|===== Digite o complemento ===== |");
        String newcomp = scanner.next();

        List<Endereco> newEndereco = new ArrayList<>();
        newEndereco.add(new Endereco(newrua, newnum, newcomp));
        clientes.get(i).setEnderecos(newEndereco);

    }


}
