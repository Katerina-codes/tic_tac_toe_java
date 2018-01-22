package main.game;

import java.util.List;

public class Game {

    private final UI inputOutput;
    private final Board board;

    public Game(UI inputOutput, Board board) {
        this.inputOutput = inputOutput;
        this.board = board;
    }

    public void runGame() {
        List<List<String>> rows = board.getRows();
        inputOutput.displayBoard(rows);
        inputOutput.askForMove();
        String move = inputOutput.getPlayerMove();
        board.updateMove(move, "1");
    }

}
