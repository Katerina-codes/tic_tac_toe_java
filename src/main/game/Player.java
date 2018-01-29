package main.game;

public class Player {

    private final UI inputOutput;

    public Player(UI inputOutput) {
        this.inputOutput = inputOutput;
    }

    public String getMove(Board board) {
        inputOutput.askForMove();
        return inputOutput.getValidMove(board);
    }
}
