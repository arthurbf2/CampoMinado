package game;

public enum Difficulty {

    EASY(9), MEDIUM(16), HARD(32);
    private final int value;

    private Difficulty(int value) {
        this.value = value;
    }

    public int getvalue() {
        return value;
    }
}