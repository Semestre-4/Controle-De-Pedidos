package org.desafio;

public class Enderecos {

    private String rua;
    private int numero;

    private String complemento;

    public Enderecos() {
    }

    public Enderecos(String rua, int numero, String complemento) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Enderecos(String rua, int numero) {
        this.rua = rua;
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


}
