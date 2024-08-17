package game;

public enum Difficulty {

    EASY(9), MEDIUM(16), HARD(32);
    private final int value;

    Difficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}