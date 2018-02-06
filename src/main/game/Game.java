package main.game;

import java.util.List;

public class Game {

    private final UI inputOutput;
    private final Board board;
    private Player currentPlayer;

    public Game(UI inputOutput, Board board) {
        this.inputOutput = inputOutput;
        this.board = board;
    }

    public void runGame() {
        Player playerOne = new Player(inputOutput, "X");
        Player playerTwo = new Player(inputOutput, "O");

        displayBoard();

        currentPlayer = playerOne;
        while (gameIsNotOver()) {
            currentPlayer.playMove(board);
            displayBoard();
            switchPlayer(playerOne, playerTwo);
        }
        Result result = board.findWinner();
        inputOutput.announceWinner(result);
    }

    private void switchPlayer(Player playerOne, Player playerTwo) {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    private boolean gameIsNotOver() {
        return !this.board.gameIsOver();
    }

    private void displayBoard() {
        List<List<String>> activeBoard = this.board.getRows();
        inputOutput.displayBoard(activeBoard);
    }

}
