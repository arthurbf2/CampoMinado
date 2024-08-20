package gui;
import game.Difficulty;
import game.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard {
    private JFrame frame;
    private JPanel gamePanel;
    private JButton[][] buttons;
    private JLabel statusLabel;
    protected Minesweeper minesweeper;
    //private int rows = 9;
    //private int columns = 9;

    public GameBoard(Difficulty difficulty) {
        this.minesweeper = new Minesweeper(difficulty);
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 500);

        gamePanel = new JPanel();
        int rows = this.minesweeper.getDifficulty().getValue();
        int columns = this.minesweeper.getDifficulty().getValue();

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

        frame.add(gamePanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Bem-vindo ao Campo Minado!");
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = buttons[row][col];
            button.setText("Clicked");
            statusLabel.setText("Clicked cell: (" + row + ", " + col + ")");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater((Runnable) new GameBoard(Difficulty.EASY));
    }
}
