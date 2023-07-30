package org.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleClientes {


    private List<Clientes> clientes = new ArrayList<>();
    List<Enderecos> enderecos = new ArrayList<>();

    Scanner s = new Scanner(System.in);



    public ControleClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public ControleClientes() {

    }


    public List<Clientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public void cadastrarNovoCliente() {

        String nome;
        String cpf;
        String telefone;


        String rua;
        int numero;
        String complemento;
        int verificaComplemento;



        System.out.println("\n|===== Digite o nome ===== |");
        nome = s.next();

        System.out.println("\n|===== Digite o CPF ===== |");
        cpf = s.next();

        System.out.println("\n|===== Digite o Telefone ===== |");
        telefone = s.next();




        System.out.println("\n|===== Digite a rua ===== |");
        rua = s.next();
        System.out.println("\n|===== Digite o número ===== |");
        numero = s.nextInt();
        System.out.println("\n|===== Possui Complemento? ===== |");
        System.out.println("| ===== 1: Sim ===== |");
        System.out.println("| ===== 2: Não ===== |");
        verificaComplemento = s.nextInt();

        if (verificaComplemento > 0 && verificaComplemento < 3){


            if (verificaComplemento == 1) {
                System.out.println("\n|===== Digite o Complemento ===== |");
                complemento = s.next();

                enderecos.add(new Enderecos(rua, numero, complemento));
            }

            if (verificaComplemento == 2){
                enderecos.add(new Enderecos(rua, numero));
            }

            clientes.add(new Clientes(nome, cpf, telefone, enderecos));

        }else{
            System.out.println("\n|===== Opção invalida!! ===== |");
        }

    }

    public void listarClientes() {

        for (int i = 0; i < clientes.size(); i++){

            System.out.println("| =========== Clientes ============= |");
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
        }


    }

    public void procurarCliente() {


        String busca;


        System.out.println("\n|===== Digite o CPF ===== |");
        busca = s.next();

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
                    opcao = s.nextInt();

                    if (opcao == 1){

                    editarCliente(i);

                    }
                    if (opcao == 2){

                    editarEndereco(i);

                    }
                }while (opcao != 0);

            }else{
                System.out.println("| ======== Não encotrado! ======== |");
            }
        }

    }

    private void displayClientDetails(Clientes cliente) {



    }

    private void editarCliente( int i) {

        System.out.println("\n|===== Digite o nome ===== |");
        String newnome = s.next();

        System.out.println("\n|===== Digite o CPF ===== |");
        String newcpf = s.next();

        System.out.println("\n|===== Digite o Telefone ===== |");
        String newtel = s.next();

        clientes.get(i).setNome(newnome);
        clientes.get(i).setCpf(newcpf);
        clientes.get(i).setTelefone(newtel);


    }

    private void editarEndereco(int i) {
        System.out.println("\n|===== Digite a rua ===== |");
        String newrua = s.next();

        System.out.println("\n|===== Digite o número ===== |");
        int newnum = s.nextInt();

        System.out.println("\n|===== Digite o complemento ===== |");
        String newcomp = s.next();

        List<Enderecos> newEndereco = new ArrayList<>();
        newEndereco.add(new Enderecos(newrua, newnum, newcomp));
        clientes.get(i).setEnderecos(newEndereco);

    }


}
