package org.desafio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private static int proximoNumeroPedido = 1;
    private int numeroPedido;
    private LocalDate dataPedido;
    private Cliente cliente;
    private List<Produto> produtos;
    private boolean encerrado;
    private boolean concluido;

    public Pedido() {
        this.numeroPedido = proximoNumeroPedido;
        proximoNumeroPedido++;
        this.produtos = new ArrayList<>();
        this.encerrado = false;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.numeroPedido = proximoNumeroPedido;
        proximoNumeroPedido++;
        this.produtos = new ArrayList<>();
        this.encerrado = false;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    // Método para adicionar um produto ao pedido
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // Método para calcular o valor total do pedido
    public double calcularValorTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    // Método para exibir os detalhes do pedido
    public void exibirDetalhes() {
        System.out.println("===== Detalhes do Pedido =====");
        System.out.println("Número do Pedido: " + numeroPedido);
        System.out.println("Data do Pedido: " + dataPedido);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Produtos:");
        for (Produto produto : produtos) {
            System.out.println("- " + produto.getNome() + " | Preço: R$" + produto.getPreco());
        }
        System.out.println("Valor Total: R$" + calcularValorTotal());
        System.out.println("Encerrado: " + encerrado);
        System.out.println("Concluído: " + concluido);
        System.out.println("==============================");
    }
}
