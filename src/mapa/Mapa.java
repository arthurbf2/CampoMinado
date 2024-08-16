package mapa;
import jogo.Dificuldade;
import java.util.Random;

public abstract class Mapa {
    private Celula[][] campo;
    private int bombas;

    public Mapa(int bombas, int tamanho) {
        this.campo = new Celula[tamanho][tamanho];
        this.bombas = bombas;

    }

    private void distribuirBombas(int bombas) {

    }

    private void inicializarCelulas() {
        for (int i = 0; i < campo.length; i++) {
            for (int j = 0; j < campo.length; j++) {
                campo[i][j] = new Celula(i, j);
            }
        }
    }
}
