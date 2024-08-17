package map;
import game.Difficulty;

public class HardMap extends Map {
    public final static Difficulty SIZE = Difficulty.HARD;
    public final static int BOMBS = 99;
    public HardMap(){
        super(BOMBS, SIZE.getValue());
    }
}
