package gui;
import game.Difficulty;
import game.Minesweeper;
import map.Cell;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    private JPanel gamePanel;
    private JButton[][] buttons;
    private JLabel statusLabel;
    protected Minesweeper minesweeper;

    public GameBoard(Difficulty difficulty) {
        this.minesweeper = new Minesweeper(difficulty);
        setTitle("MINESWEEPER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 500);

        gamePanel = new JPanel();
        int rows = difficulty.getValue();
        int columns = difficulty.getValue();

        gamePanel.setLayout(new GridLayout(rows, columns));
        buttons = new JButton[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));
                button.setFont(new Font("Arial", Font.PLAIN, 18));
                button.addActionListener(new ButtonClickListener(i, j));
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
                        buttons[i][j].setText("X");
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

    private class ButtonClickListener implements ActionListener {
        int row;
        int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = buttons[row][col];
            minesweeper.getMap().selectPosition(row, col);
            printButtons(minesweeper.getMap().isEndOfGame());
            if (minesweeper.getMap().isEndOfGame())
                gameOver();
        }
    }
}
