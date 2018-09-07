package com.example.geordano.trabalho;

public class Itens {


    private int id;
    private int imagem;
    private String nomeItem ;
    private int quantItem;
    private int valor;



    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Itens(String nomeItem, int quantItem, int valor, int imagem) {
        this.nomeItem = nomeItem;
        this.quantItem = quantItem;
        this.valor = valor;
        this.imagem = imagem;
    }

    public Itens() {
    }

    public int getQuantItem() {

        return quantItem;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setQuantItem(int quantItem) {
        this.quantItem = quantItem;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
