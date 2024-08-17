package map;
import game.Difficulty;

public class EasyMap extends Map {
    public final static Difficulty SIZE = Difficulty.EASY;
    public final static int BOMBS = 10;
    public EasyMap(){
        super(BOMBS, SIZE.getValue());
    }
}
