package com.example.geordano.trabalho;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by 20161cmq.ads0220 on 22/06/2018.
 */

public class Criatura  implements Serializable{

    private String nomeCriatura;
    private String ataque;
    private String defesa;
    private String velocidade;
    private String vida;
    private int pontos;
    private ImageView imagemCriatura;

    public String getNomeCriatura() {
        return nomeCriatura;
    }

    public void setNomeCriatura(String nomeCriatura) {
        this.nomeCriatura = nomeCriatura;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getAtaque() {

        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getDefesa() {
        return defesa;
    }

    public void setDefesa(String defesa) {
        this.defesa = defesa;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    public String getVida() {
        return vida;
    }

    public void setVida(String vida) {
        this.vida = vida;
    }

    public ImageView getImagemCriatura() {
        return imagemCriatura;
    }

    public void setImagemCriatura(ImageView imagemCriatura) {
        this.imagemCriatura = imagemCriatura;
    }
}
