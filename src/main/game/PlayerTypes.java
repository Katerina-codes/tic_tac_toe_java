package main.game;

public enum PlayerTypes {
    X_MARK("X"),
    O_MARK("O");

    private final String playerType;

    PlayerTypes(String playerType) {
        this.playerType = playerType;
    }

    public String getPlayer() {
        return playerType;
    }
}
