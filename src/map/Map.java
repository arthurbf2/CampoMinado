package map;
import game.Difficulty;
import java.util.Random;

public abstract class Map {
    private Cell[][] field;
    private int bombs;
    private int visibleCells;
    private boolean endOfGame;
    private boolean gameWon;
    private int flagCount;


    public Map(int bombs, int size) {
        this.field = new Cell[size][size];
        this.bombs = bombs;
        initializeCells();
        distributeBombs(bombs);
        countBombs();
        traverseNeighbors(field);
    }

    private void initializeCells() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
    }

    private void distributeBombs(int bombs) {
        Random random = new Random();
        for (int i = 0; i < field.length; i++) {
            int x = random.nextInt(field.length);
            int y = random.nextInt(field.length);
            Cell cell = getCell(x, y);
            if (!cell.isBomb())
                cell.setBomb(true);
            else
                i--;
        }
    }

    public void printGame(){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                if (field[i][j].isBomb())
                    System.out.print(" X");
                else {
                    int n = getCell(i, j).getNeighboringBombsCount();
                    System.out.print(" " + n);
                }
            }
            System.out.println();
        }
    }

    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    public int getFlagCount() { return flagCount; }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public int getVisibleCells() {
        return visibleCells;
    }

    public void setVisibleCells(int visibleCells) {
        this.visibleCells = visibleCells;
    }

    public int getBombs() {
        return bombs;
    }

    public void setBombs(int bombs) {
        this.bombs = bombs;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }

    public void countBombs(){
        /*
        Calculates the number of bombs in the vicinity of a cell
         */
        int boundary = field.length;
        for (int i = 0; i < boundary; i++) {
            for (int j = 0; j < boundary; j++) {
                Cell cell = getCell(i, j);
                if (!cell.isBomb()) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        if (k >= 0 && k < boundary)
                            for (int l = j - 1; l <= j + 1; l++)
                                if (l >= 0 && l < boundary){
                                    Cell neighbor = getCell(k, l);
                                    if (neighbor.isBomb()){
                                        cell.setNeighboringBombsCount(cell.getNeighboringBombsCount() + 1);
                                    }
                                }
                    }
                }
            }
        }
    }

    public void traverseNeighbors(Cell[][] field){

    }
}
