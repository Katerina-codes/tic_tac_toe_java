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
        List<String> board = this.board.createGrid();
        Player player = new Player(inputOutput);

        displayBoard();

        while (this.board.hasAvailableMoves(board)) {
            playMove(grid, player);
            displayBoard();
        }
        displayBoard();
    }

    private void displayBoard() {
        List<List<String>> activeBoard = this.board.getRows();
        inputOutput.displayBoard(activeBoard);
    }

    private void playMove(List grid, Player player) {
        inputOutput.askForMove();
        String playerOneMove = player.playMove(this.board, grid);
        this.board.updateMove(playerOneMove, "X");
    }

}
