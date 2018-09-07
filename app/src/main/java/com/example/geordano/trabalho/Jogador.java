package com.example.geordano.trabalho;

import java.util.ArrayList;

/**
 * Created by 20161cmq.ads0220 on 15/06/2018.
 */

public class Jogador {


    private int id;
    private String nome;
    private String apelido;
    private String email;
    private String senha;
    private int pontos = 1000;
    private ArrayList<Criatura>Criaturas;
    private ArrayList<Itens> itens;


    public ArrayList<Criatura> getCriaturas() {
        return Criaturas;
    }

    public Jogador() {
        Criaturas=new ArrayList();
        itens=new ArrayList();
    }

    public void setCriaturas(ArrayList<Criatura> criaturas) {
        this.Criaturas = Criaturas;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Itens> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Itens> itens) {
        this.itens = itens;
    }
}
