package game;

import map.*;

import java.util.Scanner;


public class Minesweeper {

    private Player player;
    private Map gameMap;
    private Difficulty difficulty;

    public Minesweeper(Difficulty difficulty) {
        this.player = new Player("Player 1");
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
        gameMap.printGame(false);
        do {
            Scanner scan = new Scanner(System.in);
            int i;
            int j;
            do {
                System.out.println("Select the row: ");
                i = scan.nextInt();
                System.out.println("Select the column ");
                j = scan.nextInt();
            } while (i > 0 && i < gameMap.getField().length && j > 0 && j < gameMap.getField().length);
            gameMap.selectPosition(i, j);
        } while (!gameMap.isEndOfGame());
        System.out.println("GAME OVER!");
        gameMap.printGame(true);
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Map getMap() {
        return gameMap;
    }

    public void setMap(Map map) {
        this.gameMap = map;
    }
}
