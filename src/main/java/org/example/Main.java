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
        List<Enderecos> enderecos = new ArrayList<>();



        do{
            String rua;
            int numero;
            String complemento;
            int verificaComplemento;

            System.out.println(" ============================== |");
            System.out.println("| ==== Cadastro de Cliente ==== | ");
            System.out.println("| ============================= |");

            System.out.println("\n|===== Digite uma opção ===== |");
            System.out.println("| ===== 1: Cadastrar Novo ===== |");
            System.out.println("| ===== 2: Listar Clientes ===== |");
            System.out.println("| ========= 0: Voltar ========= |");
            escolha = s.nextInt();

            switch (escolha){

                case 1:
                    System.out.println("\n|===== Digite a rua ===== |");
                    rua = s.nextLine();
                    System.out.println("\n|===== Digite o número ===== |");
                    numero = s.nextInt();
                    System.out.println("\n|===== Possui Complemento? ===== |");
                    System.out.println("| ===== 1: Sim ===== |");
                    System.out.println("| ===== 2: Não ===== |");
                    verificaComplemento = s.nextInt();

                    if (verificaComplemento > 0 && verificaComplemento < 3){

                        if (verificaComplemento == 1) {
                            System.out.println("\n|===== Digite o Complemento ===== |");
                            complemento = s.nextLine();

                            enderecos.add(new Enderecos(rua, numero, complemento));
                        }

                        if (verificaComplemento == 2){

                            enderecos.add(new Enderecos(rua, numero));

                        }


                    }else{
                    System.out.println("\n|===== Opção errada!! ===== |");
                    break;
                    }



                    break;

            }

        }while(escolha == 0);

    }
}