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
        Player playerOne = new Player(inputOutput);
        Player playerTwo = new Player(inputOutput);

        displayBoard();

        while (!this.board.gameIsOver()) {

            playMove(playerOne, grid, "X");
            displayBoard();

            if (this.board.gameIsOver()) {
                return;
            } else {
                playMove(playerTwo, grid, "O");
                displayBoard();
            }
        }
        displayBoard();
    }

    private void displayBoard() {
        List<List<String>> activeBoard = this.board.getRows();
        inputOutput.displayBoard(activeBoard);
    }

    private String playMove(Player player, List grid, String mark) {
        inputOutput.askForMove();
        String playerMove = player.playMove(this.board, grid);
        this.board.updateMove(playerMove, mark);
        return playerMove;
    }

}
