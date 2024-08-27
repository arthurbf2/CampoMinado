package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import gui.GameBoard;
import map.Cell;

import javax.swing.*;

public class GameSolver {
    private final GameBoard board;
    protected Minesweeper minesweeper;
    public GameSolver(Minesweeper minesweeper, GameBoard board){
        this.board = board;
        this.minesweeper = minesweeper;
        Random random = new Random();
        int x = random.nextInt(minesweeper.getDifficulty().getValue());
        int y = random.nextInt(minesweeper.getDifficulty().getValue());
        minesweeper.getMap().selectPosition(x, y);
        board.printButtons(false);
        solveGame();
    }

    public void setFlags(Cell cell, List<Cell> invisibleNeighbors){
        if (cell.getFlaggedNeighbors().size() == cell.getNeighboringBombsCount())
            return;
        if (invisibleNeighbors.size() == cell.getNeighboringBombsCount()) {
            for (Cell neighbor : invisibleNeighbors) {
                board.rightClick(neighbor.getRow(), neighbor.getColumn());
            }
        }
    }

    public void selectCell(Cell cell) {
        if (cell.getNeighboringBombsCount() == cell.getFlaggedNeighbors().size()) {
            for (Cell neighbor : cell.getNeighbors()) {
                if (!neighbor.isVisible() && !neighbor.isFlag()) {
                    board.leftClick(cell.getRow(), cell.getColumn());
                }
            }
        }
    }

    public void solveGame() {
        while (!this.minesweeper.getMap().isEndOfGame()) {
            for (int i = 0; i < this.minesweeper.getDifficulty().getValue(); i++) {
                for (int j = 0; j < this.minesweeper.getDifficulty().getValue(); j++) {
                    Cell cell = this.minesweeper.getMap().getCell(i, j);
                    List<Cell> invisibleNeighbors = new ArrayList<>();
                    for (Cell neighbor : cell.getNeighbors()) {
                        if (!neighbor.isFlag() && !neighbor.isVisible())
                            invisibleNeighbors.add(neighbor);
                        else if (neighbor.isFlag())
                            cell.getFlaggedNeighbors().add(neighbor);
                    }
                    setFlags(cell, invisibleNeighbors);
                    selectCell(cell);
                    if (this.minesweeper.getMap().isEndOfGame()) {
                        System.out.println("Last cell: " + cell.getRow() + ", " + cell.getColumn() + ")");
                        return;
                    }
                }
            }
        }
        if (this.minesweeper.getMap().isGameWon())
            System.out.println("GAME WON!!");
        else
            System.out.println("YOU LOST");
    }
}
