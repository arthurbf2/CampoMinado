package gui;
import game.Difficulty;
import game.GameSolver;
import game.Minesweeper;
import map.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard extends JFrame {
    private JPanel gamePanel;
    private JPanel topPanel;
    private JButton[][] buttons;
    protected Minesweeper minesweeper;
    private ImageIcon bombIcon = new ImageIcon(getClass().getResource("resources/bomb.png"));
    private ImageIcon flagIcon = new ImageIcon(getClass().getResource("resources/flag.png"));
    private JLabel flagCounter;
    private JLabel chronometerLabel;
    private Timer timer;
    private int timeCounter = 0;

    public GameBoard(Difficulty difficulty, boolean AI_mode) {
        int rows = difficulty.getValue();
        int columns = difficulty.getValue();
        this.minesweeper = new Minesweeper(difficulty);
        setTitle("MINESWEEPER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 800);
        setLocationRelativeTo(null);
        gamePanel = new JPanel();
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flagCounter = new JLabel("FLAGS: 0");
        this.chronometer();
        topPanel.add(flagCounter);
        topPanel.add(this.chronometerLabel);
        add(topPanel, BorderLayout.NORTH);
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
        if (AI_mode)
            new GameSolver(this.minesweeper, this);
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
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public void chronometer(){
        this.chronometerLabel = new JLabel();
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                timeCounter++;
                int sec = timeCounter % 60;
                int min = timeCounter/ 60;
                int hour = min / 60;
                min %= 60;
                chronometerLabel.setText(String.format("%02d:%02d:%02d", hour, min, sec));
                topPanel.add(chronometerLabel, BorderLayout.NORTH);
            }
        }, 1000L,1000L);
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

    public void leftClick(int row, int column) {
        JButton button = buttons[row][column];
        if (button.isEnabled()) {
            minesweeper.getMap().selectPosition(row, column);
            printButtons(minesweeper.getMap().isEndOfGame());
            if (minesweeper.getMap().isEndOfGame())
                gameOver();
        }
    }

    public void rightClick(int row, int column) {
        JButton button = buttons[row][column];
        Cell cell = minesweeper.getMap().getCell(row, column);
        if(cell.isVisible())
            return;
        if (cell.isFlag()) {
            cell.setFlag(false);
            button.setIcon(new ImageIcon(""));
            button.setEnabled(true);
            minesweeper.getMap().setFlagCount(minesweeper.getMap().getFlagCount() - 1);
        }
        else if (minesweeper.getMap().getFlagCount() < minesweeper.getMap().getBombQuantity()){
            cell.setFlag(true);
            button.setIcon(flagIcon);
            button.setEnabled(false);
            minesweeper.getMap().setFlagCount(minesweeper.getMap().getFlagCount() + 1);
        }
        flagCounter.setText("FLAGS: " + minesweeper.getMap().getFlagCount());
    }

    private class ButtonClickListener extends MouseAdapter {
        int row;
        int column;

        public ButtonClickListener(int row, int column){
            this.row = row;
            this.column = column;
        }

        public void mouseClicked(MouseEvent e){
            if (e.getButton() == MouseEvent.BUTTON1)
                leftClick(row, column);
            if (e.getButton() == MouseEvent.BUTTON3) {
                rightClick(row, column);
            }
        }
    }
}
