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

    public void setNeighbors(List<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public void searchNeighbors(Cell[][] field){

    }

    public boolean isEmptyCell() {
        return true;
    }
}
