package map;
import java.util.List;
import java.util.Random;

public abstract class Map {
    private final Cell[][] field;
    private final int bombs;
    private int visibleCells;
    private boolean endOfGame;
    private boolean gameWon;
    private int flagCount;


    public Map(int bombs, int size) {
        this.field = new Cell[size][size];
        this.bombs = bombs;
        initializeCells();
        distributeBombs(bombs);
        traverseNeighbors(field);
        countBombs(field);
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
        System.out.print("   ");
        for (int x = 0; x < field.length; x++ )
            System.out.printf(" %02d", x);
        System.out.println();
        for (int i = 0; i < field.length; i++){
            System.out.printf(" %02d", i);
            for (int j = 0; j < field.length; j++){
                Cell cell = getCell(i, j);
                if (debug) {
                    if (cell.isBomb())
                        System.out.print("  X");
                    else {
                        System.out.print("  " + cell.getNeighboringBombsCount());
                    }
                }
                else {
                    if (cell.isVisible())
                        System.out.print("  " + cell.getNeighboringBombsCount());
                    else
                        System.out.print("  *");
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


    public void countBombs(Cell[][] field){
        /*
        Calculates the number of bombs in the vicinity of a cell
         */
        for (Cell[] row : field) {
            for (Cell cell: row) {
                List<Cell> neighbors = cell.getNeighbors();
                for (Cell neighbor : neighbors) {
                    if (neighbor.isBomb())
                        cell.setNeighboringBombsCount(cell.getNeighboringBombsCount() + 1);
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

    public void revealBlankCells(Cell cell) {
        /*
        method called when an empty position is selected. Reveals all empty neighboring positions,
        stops upon arrival to a non-empty position(position with at least one neighboring bomb)
         */
        List<Cell> neighbors = cell.getNeighbors();
        for (Cell neighbor : neighbors) {
            if (!neighbor.isVisible()) {
                neighbor.setVisible(true);
                visibleCells++;
                if (neighbor.isEmptyCell()) {
                    revealBlankCells(neighbor);
                }
            }
        }
    }

    public boolean checkWinCondition(){
        // A game is won when all non-bomb cells are opened
        gameWon = (visibleCells >= (field.length * field.length) - bombs);
        return gameWon;
    }

    public void selectPosition(int row, int column) {
        /*
        Takes user input to update game state.
         */
        Cell cell = getCell(row, column);
        if (cell.isVisible())
                return;
        cell.setVisible(true);
        visibleCells++;
        if (cell.isBomb()) {
            endOfGame = true;
            return;
        }
        if (cell.isEmptyCell()) {
            revealBlankCells(cell);
        }
        //printGame(false);
        endOfGame = checkWinCondition();
    }
}
