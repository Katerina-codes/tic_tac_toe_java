package main.game;

public class HumanPlayer {

    private final UI inputOutput;
    private final String mark;

    public HumanPlayer(UI inputOutput, String mark) {
        this.inputOutput = inputOutput;
        this.mark = mark;
    }

    public void playMove(Board board) {
        String playerMove = getMove(board);
        board.updateMove(playerMove, this.mark);
    }

    public String getMove(Board board) {
        inputOutput.askForMove();
        return inputOutput.getValidMove(board);
    }
}
