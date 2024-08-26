package game;

import map.*;

import java.util.Scanner;


public class Minesweeper {

    private Map gameMap;
    private final Difficulty difficulty;

    public Minesweeper(Difficulty difficulty) {
        this.difficulty = difficulty;
        switch (difficulty){
            case EASY:
                this.gameMap = new EasyMap();
                break;
            case MEDIUM:
                this.gameMap = new MediumMap();
                break;
            case HARD:
                this.gameMap = new HardMap();
                break;
        }
        //System.out.println("Welcome");
        //gameMap.printGame(true);
        //startGame();
    }

    public void startGame() {
        do {
            Scanner scan = new Scanner(System.in);
            int i, j;
            System.out.println("Informe a linha: ");
            i = scan.nextInt();
            System.out.println("Informe a coluna: ");
            j = scan.nextInt();
            if (i >= 0 && i < gameMap.getField().length && j >= 0 && j < gameMap.getField().length) {
                gameMap.selectPosition(i, j);
            } else
                startGame();
        } while (!gameMap.isEndOfGame() && !gameMap.isGameWon());
        System.out.println("GAME OVER!");
        if (gameMap.isGameWon())
            System.out.println("YOU WON!!!!!");
        gameMap.printGame(true);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }


    public Map getMap() {
        return gameMap;
    }

}
