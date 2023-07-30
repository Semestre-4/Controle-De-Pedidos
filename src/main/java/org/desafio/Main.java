package org.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        ControleClientes controleClientes = new ControleClientes();
        Scanner s = new Scanner(System.in);

        List<Clientes> clientes = new ArrayList<>();


        controleClientes.menuClientes();


    }
}