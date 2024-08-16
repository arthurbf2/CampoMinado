package mapa;

public class MapaDificil {
    public final static Dificuldade TAMANHO = Dificuldade.DIFICIL;
    public final static int BOMBAS = 99;
    public MapaDificil(){
        super(BOMBAS, TAMANHO.getValor());
    }
}
