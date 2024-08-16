package map;
import game.Difficulty;
import java.util.Random;

public abstract class Map {
    private Cell[][] field;
    private int bombs;
    private int visibleCells;
    private boolean endOfGame;
    private boolean gameWon;


    public Map(int bombs, int size) {
        this.field = new Cell[size][size];
        this.bombs = bombs;
    }

    private void distributeBombs(int bombs) {

    }

    private void initializeCells() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
    }


}
