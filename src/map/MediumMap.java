package map;

public class MediumMap {
    public final static Difficulty SIZE = Difficulty.MEDIUM;
    public final static int BOMBS = 40;
    public MediumMap(){
        super(BOMBS, SIZE.getValue());
    }
}
