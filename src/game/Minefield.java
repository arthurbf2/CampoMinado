package game;

import map.*;


public class Minefield {

    private Player player;
    private Map gameMap;
    private Difficulty difficulty;

    public Minefield(Difficulty difficulty) {
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
        System.out.println("Welcome");
        gameMap.printGame();

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
