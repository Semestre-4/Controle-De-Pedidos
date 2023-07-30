package org.desafio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleClientes {


    private List<Clientes> clientes = new ArrayList<>();

    public ControleClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public List<Clientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public void cadastrarNovoCliente(Scanner scanner) {

    }

    public void listarClientes() {

    }

    public void procurarCliente(Scanner scanner) {

    }

    private void displayClientDetails(Clientes cliente) {

    }

    private void editarCliente(Clientes cliente, Scanner scanner) {

    }

    private void editarEndereco(Clientes cliente, Scanner scanner) {

    }


}
