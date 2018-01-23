package main.game;

import java.util.List;

public class Player {

    private final UI inputOutput;

    public Player(UI inputOutput) {
        this.inputOutput = inputOutput;
    }

    public String playMove(Board board, List<String> grid) {
        return inputOutput.getPlayerMove(board, grid);
    }
}
