package org.example;

import java.util.ArrayList;
import java.util.List;

public class Clientes {

    private String nome;
    private String cpf;

    private List<Enderecos> enderecos = new ArrayList<>();

    public Clientes() {
    }

    public Clientes(String nome, String cpf, List<Enderecos> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
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

    public List<Enderecos> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Enderecos> enderecos) {
        this.enderecos = enderecos;
    }
}
