package gui;

import game.Difficulty;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    public Menu() {
        setTitle("MINESWEEPER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null);
        JButton easyButton = new JButton("EASY");
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(Difficulty.EASY);
            }
        });
        add(easyButton);

        JButton mediumButton = new JButton("MEDIUM");
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(Difficulty.MEDIUM);
            }
        });
        add(mediumButton);

        JButton hardButton = new JButton("HARD");
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(Difficulty.HARD);
            }
        });
        add(hardButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);

        setVisible(true);
    }

    private void startGame(Difficulty difficulty) {
        dispose();
        new GameBoard(difficulty);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }
}
