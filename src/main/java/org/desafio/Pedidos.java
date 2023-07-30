package org.desafio;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {

    private static int proximoNumeroPedido = 1;
    private int numeroPedido;
    private Clientes clientes;
    List<Produto> produtos;
    private boolean encerrado;

    public Pedidos() {
    }

    public Pedidos(int numeroPedido, Clientes clientes, List<Produto> produtos, boolean encerrado) {
        this.numeroPedido = proximoNumeroPedido;
        proximoNumeroPedido++;
        this.clientes = clientes;
        this.produtos = new ArrayList<>();
        this.encerrado = false;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isEncerrado() {
        return encerrado;
    }

    public void setEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }
}
