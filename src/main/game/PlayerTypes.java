package main.game;

public enum PlayerTypes {
    PLAYER_ONE_MARK("X"),
    PLAYER_TWO_MARK("O");

    private final String playerType;

    PlayerTypes(String playerType) {
        this.playerType = playerType;
    }

    public String getPlayer() {
        return playerType;
    }
}
