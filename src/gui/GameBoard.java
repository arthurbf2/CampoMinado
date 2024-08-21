package gui;
import game.Difficulty;
import game.Minesweeper;
import map.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JFrame {
    private JPanel gamePanel;
    private JButton[][] buttons;
    protected Minesweeper minesweeper;
    private ImageIcon bombIcon = new ImageIcon(getClass().getResource("resources/bomb.png"));
    private ImageIcon flagIcon = new ImageIcon(getClass().getResource("resources/flag.png"));

    public GameBoard(Difficulty difficulty) {
        this.minesweeper = new Minesweeper(difficulty);
        setTitle("MINESWEEPER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 800);
        setLocationRelativeTo(null);
        gamePanel = new JPanel();
        int rows = difficulty.getValue();
        int columns = difficulty.getValue();

        gamePanel.setLayout(new GridLayout(rows, columns));
        buttons = new JButton[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                button.setMargin(new Insets(0, 0, 0, 0));
                if (minesweeper.getDifficulty().equals(Difficulty.HARD)) {
                    button.setFont(new Font("LCDMono2", Font.BOLD, 10));
                } else
                    button.setFont(new Font("LCDMono2", Font.BOLD, 25));
                button.addMouseListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                gamePanel.add(button);
            }
        }
        add(gamePanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void gameOver(){
        if (this.minesweeper.getMap().isGameWon()){
            JOptionPane.showMessageDialog(null,
                    "YOU WON!");
        } else {
            JOptionPane.showMessageDialog(null,
                    "YOU LOST!");
        }
        this.dispose();
        System.out.println(this);
        Menu menu = new Menu();
        menu.setVisible(true);
    }


    public void printButtons(boolean isEndOfGame){
        for (int i = 0; i < this.minesweeper.getDifficulty().getValue(); i++){
            for (int j = 0; j < this.minesweeper.getDifficulty().getValue(); j++){
                Cell cell = this.minesweeper.getMap().getCell(i, j);
                if (isEndOfGame && cell.isBomb()) {
                        buttons[i][j].setIcon(bombIcon);
                }
                else {
                        if (cell.isVisible()) {
                            buttons[i][j].setEnabled(false);
                            if (cell.isEmptyCell())
                                buttons[i][j].setText("");
                            else
                                buttons[i][j].setText(Integer.toString(cell.getNeighboringBombsCount()));
                        }
                    }
            }
        }
    }

    private class ButtonClickListener extends MouseAdapter {
        int row;
        int column;

        public ButtonClickListener(int row, int column){
            this.row = row;
            this.column = column;
        }

        public void mouseClicked(MouseEvent e){
            JButton button = buttons[row][column];
            Cell cell = minesweeper.getMap().getCell(row, column);
            if (e.getButton() == MouseEvent.BUTTON1 && button.isEnabled()) {
                minesweeper.getMap().selectPosition(row, column);
                printButtons(minesweeper.getMap().isEndOfGame());
                if (minesweeper.getMap().isEndOfGame())
                    gameOver();
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if (cell.isVisible())
                    return;
                if (cell.isFlag()) {
                    cell.setFlag(false);
                    button.setIcon(new ImageIcon(""));
                    button.setEnabled(true);
                    minesweeper.getMap().setFlagCount(minesweeper.getMap().getFlagCount() - 1);
                }
                else {
                    cell.setFlag(true);
                    button.setIcon(flagIcon);
                    button.setEnabled(false);
                    minesweeper.getMap().setFlagCount(minesweeper.getMap().getFlagCount() + 1);
                }
            }
            System.out.println(minesweeper.getMap().getFlagCount());
        }
    }
}
