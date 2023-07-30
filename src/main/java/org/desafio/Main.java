package org.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        ControleClientes controle = new ControleClientes();
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

            System.out.println("\n| ============================= |");
            System.out.println("| ==== Cadastro de Cliente ==== | ");
            System.out.println("| ============================= |");
            System.out.println("|                               |");
            System.out.println("| ===== Digite uma opção ====== |");
            System.out.println("| ===== 1: Cadastrar Novo ===== |");
            System.out.println("| ==== 2: Listar Clientes ===== |");
            System.out.println("| ==== 3: Procurar Cliente ==== |");
            System.out.println("| ========= 0: Voltar ========= |");
            escolha = s.nextInt();

            switch (escolha){


                case 1:

                    controle.cadastrarNovoCliente();
                    break;

                case 2:

                    controle.listarClientes();
                    break;

                case 3:
                    controle.procurarCliente();
                    break;

                default:
                    System.out.println("| ===== Opção invalida! ===== |");
                break;
            }

        }while(escolha != 0);

    }
}