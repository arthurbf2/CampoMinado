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
    private Timer timer;
    private boolean progressMade;
    int i = 0;
    int j = 0;

    public GameSolver(Minesweeper minesweeper, GameBoard board){
        this.board = board;
        this.minesweeper = minesweeper;
        Random random = new Random();
        int x = 8;
        int y = 8;
        //int x = random.nextInt(minesweeper.getDifficulty().getValue());
        //int y = random.nextInt(minesweeper.getDifficulty().getValue());
        minesweeper.getMap().selectPosition(x, y);
        board.printButtons(false);
        timer = new Timer(100, e -> solveGame());
        timer.start();
    }

    public void setFlags(Cell cell, List<Cell> invisibleNeighbors){
        if (cell.getFlaggedNeighbors().size() == cell.getNeighboringBombsCount())
            return;
        if (invisibleNeighbors.size() == cell.getNeighboringBombsCount()) {
            for (Cell neighbor : invisibleNeighbors) {
                if (!neighbor.isFlag()) {
                    System.out.println("Cell: " + cell);
                    System.out.println("Flagging: " + neighbor);
                    board.rightClick(neighbor.getRow(), neighbor.getColumn());
                    progressMade = true;
                }
            }
        }
    }

    public void selectCell(Cell cell) {
        if (cell.isVisible() && cell.getNeighboringBombsCount() == cell.getFlaggedNeighbors().size()) {
            for (Cell neighbor : cell.getNeighbors()) {
                if (!neighbor.isVisible() && !neighbor.isFlag()) {
                    System.out.println("Cell: " + cell);
                    System.out.println("Selecting " + neighbor);
                    board.leftClick(neighbor.getRow(), neighbor.getColumn());
                    progressMade = true;
                }
            }
        }
    }

    public void selectRandomCell(){
        Random random = new Random();
        Cell cell;
        do {
            int x = random.nextInt(minesweeper.getDifficulty().getValue());
            int y = random.nextInt(minesweeper.getDifficulty().getValue());
            cell = this.minesweeper.getMap().getCell(x, y);
        } while(cell.isVisible() || cell.isFlag());
        System.out.println("Randomly selecting " + cell);
        board.leftClick(cell.getRow(), cell.getColumn());
    }

    public void solveGame() {
        if(i == 0 && j == 0)
            progressMade = false;
        if (minesweeper.getMap().isEndOfGame()) {
            timer.stop();
            if (minesweeper.getMap().isGameWon()) {
                System.out.println("GAME WON!!");
            } else {
                System.out.println("YOU LOST");
            }
            return;
        }
        Cell cell = minesweeper.getMap().getCell(i, j);
        List<Cell> invisibleNeighbors = new ArrayList<>();
        for (Cell neighbor : cell.getNeighbors()) {
            if (!neighbor.isFlag() && !neighbor.isVisible()) {
                invisibleNeighbors.add(neighbor);
            } else if (neighbor.isFlag() && !cell.getFlaggedNeighbors().contains(neighbor)) {
                cell.getFlaggedNeighbors().add(neighbor);
            }
        }
        setFlags(cell, invisibleNeighbors);
        selectCell(cell);
        if (!progressMade && i == minesweeper.getDifficulty().getValue() - 1 && j == minesweeper.getDifficulty().getValue() - 1) {
            selectRandomCell();
        }
        j++;
        if (j >= minesweeper.getDifficulty().getValue()) {
            j = 0;
            i++;
        }
        if (i >= minesweeper.getDifficulty().getValue()) {
            i = 0;
        }
        board.printButtons(minesweeper.getMap().isEndOfGame());
    }
}
