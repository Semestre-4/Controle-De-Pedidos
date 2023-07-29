package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int escolha;
        Scanner s = new Scanner(System.in);

        List<Clientes> clientes = new ArrayList<>();



        do{

            String nome;
            String cpf;
            String telefone;


            String rua;
            int numero;
            String complemento;
            int verificaComplemento;

            System.out.println(" ============================== |");
            System.out.println("| ==== Cadastro de Cliente ==== | ");
            System.out.println("| ============================= |");
            System.out.println("|===== Digite uma opção ===== |");
            System.out.println("| ===== 1: Cadastrar Novo ===== |");
            System.out.println("| ===== 2: Listar Clientes ===== |");
            System.out.println("| ===== 3: Procurar Cliente ===== |");
            System.out.println("| ========= 0: Voltar ========= |");
            escolha = s.nextInt();

            switch (escolha){


                case 1:
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
                        List<Enderecos> enderecos = new ArrayList<>();


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
                    break;
                    }
                    break;

                case 2:
                    for (int i = 0; i < clientes.size(); i++){

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
                    }
                break;

                case 3:
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

                        System.out.println("| ======================================= |");
                        System.out.println("| =============    Opções    ============ |");
                        System.out.println("| ========== 1: Editar  Cliente ========= |");
                        System.out.println("| ========== 2: Editar Endereço ========= |");
                        System.out.println("| ===========     0: Voltar    ========== |");
                        System.out.println("| ======================================= |");
                        opcao = s.nextInt();

                        if (opcao == 1){

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
                        if (opcao == 2){

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
                    }
                    break;



            }

        }while(escolha != 0);

    }
}