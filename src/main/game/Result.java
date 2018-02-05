package main.game;

public enum Result {
    PLAYER_ONE("X"),
    PLAYER_TWO("O"),
    TIE("Tie");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}