package map;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private boolean flag;
    private boolean bomb;
    private int neighboringBombsCount;
    private boolean visible;
    private int row;
    private int column;
    private List<Cell> neighbors = new ArrayList<Cell>();

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getNeighboringBombsCount() {
        return neighboringBombsCount;
    }

    public void setNeighboringBombsCount(int neighboringBombsCount) {
        this.neighboringBombsCount = neighboringBombsCount;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }


    public void searchNeighbors(Cell[][] field, Cell cell) {
        // Creates a list for every cell containing all its neighboring cells
        int i = Math.max(this.row - 1, 0);
        while (i <= this.row + 1 && i < field.length) {
            int j = Math.max(this.column - 1, 0);
            while (j <= this.column + 1 && j < field.length) {
                if (field[i][j] != cell) {
                    cell.neighbors.add(field[i][j]);
                }
                j++;
            }
            i++;
        }
    }

    public boolean isEmptyCell() {
        return this.neighboringBombsCount == 0;
    }
}
