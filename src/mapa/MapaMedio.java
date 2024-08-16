package mapa;

public class MapaMedio {
    public final static Dificuldade TAMANHO = Dificuldade.MEDIO;
    public final static int BOMBAS = 40;
    public MapaMedio(){
        super(BOMBAS, TAMANHO.getValor());
    }
}
