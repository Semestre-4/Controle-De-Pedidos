package org.desafio;

public enum Produto {
    MARGHERITA("Margherita", 25.0),
    CALABRESA("Calabresa", 28.5),
    QUATRO_QUEIJOS("Quatro Queijos", 30.0),
    PORTUGUESA("Portuguesa", 27.0),
    FRANGO_CATUPIRY("Frango com Catupiry", 32.0),
    PEPPERONI("Pepperoni", 29.5),
    VEGETARIANA("Vegetariana", 26.0);

    private String nome;
    private double preco;

    Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

}
