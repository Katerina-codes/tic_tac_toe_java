package main.game;

public enum GameMode {

    OPTION_ONE("1", "Human vs Human"),

    OPTION_TWO("2", "Human vs Computer"),

    OPTION_THREE("3", "Computer vs Human"),

    OPTION_FOUR("4", "Computer vs Computer");

    private final String gameMode;
    private final String gameModeChoice;

    GameMode(String gameModeChoice, String gameMode) {
        this.gameMode = gameMode;
        this.gameModeChoice = gameModeChoice;
    }


}
