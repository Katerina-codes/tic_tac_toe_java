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
        Player player = new Player(inputOutput);

        displayBoard();

        String winner = null;
        while (gameIsNotOver()) {

            player.playMove("X", board);
            displayBoard();

            if (this.board.gameIsOver()) {
                winner = "X";
                inputOutput.announceWinner(winner);
                return;
            } else {
                player.playMove("O", board);
                winner = "O";
                displayBoard();
            }
        }
        displayBoard();
        inputOutput.announceWinner(winner);
    }

    private boolean gameIsNotOver() {
        return !this.board.gameIsOver();
    }

    private void displayBoard() {
        List<List<String>> activeBoard = this.board.getRows();
        inputOutput.displayBoard(activeBoard);
    }

}
