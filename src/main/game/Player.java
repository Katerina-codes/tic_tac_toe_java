package main.game;

import java.util.List;

public class Player {

    private final CommandLineUI inputOutput;

    public Player(CommandLineUI inputOutput) {
        this.inputOutput = inputOutput;
    }

    public String playMove(Board board, List<String> grid) {
        return inputOutput.getPlayerMove(board, grid);
    }
}
