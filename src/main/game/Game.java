package main.game;

import java.util.List;

import static java.util.Arrays.asList;

public class Game {

    private final UI inputOutput;
    private final Board board;

    public Game(UI inputOutput, Board board) {
        this.inputOutput = inputOutput;
        this.board = board;
    }

    public void runGame() {
        List grid = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> currentBoard = board.createGrid();
        while (board.hasAvailableMoves(currentBoard)) {
            List<List<String>> rows = board.getRows();

            inputOutput.displayBoard(rows);
            inputOutput.askForMove();
            String playerOneMove = inputOutput.getPlayerMove(board, grid);
            board.updateMove(playerOneMove, "X");
        }
    }

}
