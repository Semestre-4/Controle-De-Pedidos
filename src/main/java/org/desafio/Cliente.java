package org.desafio;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private List<Endereco> enderecos = new ArrayList<>();

    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String telefone, List<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
        this.telefone = telefone;
    }

    public void addPedido(Pedido pedido) {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
        pedidos.add(pedido);
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(String nome, String cpf, String telefone, Endereco endereco) {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    static boolean isValidCPF(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
