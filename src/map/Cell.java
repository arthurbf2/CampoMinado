package map;

public class Cell {
    private boolean flag;
    private boolean bomb;
    private int neighboringBombsCount;
    private boolean isVisible;
    private int row;
    private int column;
    private List<Cell> neighbors = new ArrayList<Cell>();

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void searchNeighbors(Cell[][] field){

    }

    public boolean isEmptyCell() {

    }
}
