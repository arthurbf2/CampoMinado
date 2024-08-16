package jogo;

import mapa.Mapa;

public class CampoMinado {

    private Jogador jogador;
    private Mapa mapa;
    private Dificuldade dificuldade;

    public CampoMinado(Jogador jogador, Mapa mapa, Dificuldade dificuldade) {
        this.jogador = jogador;
        this.mapa = mapa;
        this.dificuldade = dificuldade;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }
}
