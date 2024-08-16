package map;

public class HardMap {
    public final static Difficulty SIZE = Difficulty.HARD;
    public final static int BOMBS = 99;
    public Hardmap(){
        super(BOMBS, SIZE.getValue());
    }
}
