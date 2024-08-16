package game;

import map.Map;

public class Minefield {

    private Player player;
    private Map map;
    private Difficulty difficulty;

    public CampoMinado(Player player, Map map, Difficulty difficulty) {
        this.player = player;
        this.map = map;
        this.difficulty = difficulty;
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
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
