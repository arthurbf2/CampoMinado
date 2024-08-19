package map;
import game.Difficulty;

import java.util.List;
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
        for (int i = 0; i < bombs; i++) {
            int x = random.nextInt(field.length);
            int y = random.nextInt(field.length);
            Cell cell = getCell(x, y);
            if (!cell.isBomb())
                cell.setBomb(true);
            else
                i--;
        }
    }

    public void printGame(boolean debug){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                Cell cell = getCell(i, j);
                if (debug) {
                    if (cell.isBomb())
                        System.out.print(" X");
                    else {
                        System.out.print(" " + cell.getNeighboringBombsCount());
                    }
                }
                else {
                    if (cell.isVisible())
                        System.out.print(" " + cell.getNeighboringBombsCount());
                    else
                        System.out.print(" *");
                }
            }
            System.out.println();
        }
    }

    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    public Cell[][] getField() {
        return field;
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

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isEndOfGame() {
        return endOfGame;
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

    public void traverseNeighbors(Cell[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                Cell cell = getCell(i, j);
                if (!cell.isVisible() && !cell.isBomb()) {
                    cell.searchNeighbors(field, cell);
                }
            }
        }
    }

    public void revealSpaces(Cell cell) {
        /*
        method called when an empty position is selected. Reveals all empty neighboring positions,
        stops when arrives to a non-empty position(position with at least one neighboring bomb)
         */
        List<Cell> neighbors = cell.getNeighbors();
        for (Cell neighbor : neighbors) {
            neighbor.setVisible(true);
            visibleCells++;
            if (neighbor.isEmptyCell() && !neighbor.isVisible()) {
                revealSpaces(neighbor);
            }
        }
    }

    public boolean checkWinCondition(){
        return (visibleCells >= (field.length * field.length) - bombs);
    }

    public void selectPosition(int row, int column) {
        Cell cell = getCell(row, column);
        cell.setVisible(true);
        if (cell.isBomb()) {
            endOfGame = true;
            return;
        }
        if (cell.isEmptyCell()) {
            revealSpaces(cell);
        }
        printGame(false);
        endOfGame = checkWinCondition();
    }
}
