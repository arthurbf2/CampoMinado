package mapa;

public class MapaFacil extends Mapa {
    public final static Dificuldade TAMANHO = Dificuldade.FACIL;
    public final static int BOMBAS = 10;
    public MapaFacil(){
        super(BOMBAS, TAMANHO.getValor());
    }
}
