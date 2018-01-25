package main.game;

public class Player {

    private final UI inputOutput;

    public Player(UI inputOutput) {
        this.inputOutput = inputOutput;
    }

    public String getPlayerMove(Board board) {
        return inputOutput.getPlayerMove(board);
    }
}
