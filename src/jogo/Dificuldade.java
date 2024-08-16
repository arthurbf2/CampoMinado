package jogo;

public enum Dificuldade {

    FACIL(9), MEDIO(16), DIFICIL(32);
    private final int valor;

    private Dificuldade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}