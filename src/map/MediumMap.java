package map;
import game.Difficulty;

public class MediumMap extends Map{
    public final static Difficulty SIZE = Difficulty.MEDIUM;
    public final static int BOMBS = 40;
    public MediumMap(){
        super(BOMBS, SIZE.getValue());
    }
}
