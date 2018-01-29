package main.game;

public class Player {

    private final UI inputOutput;

    public Player(UI inputOutput) {
        this.inputOutput = inputOutput;
    }

    public void playMove(String mark, Board board) {
        String playerMove = getMove(board);
        board.updateMove(playerMove, mark);
    }

    public String getMove(Board board) {
        inputOutput.askForMove();
        return inputOutput.getValidMove(board);
    }
}
