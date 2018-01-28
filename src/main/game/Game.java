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
        Player playerOne = new Player(inputOutput);
        Player playerTwo = new Player(inputOutput);

        displayBoard();

        String winner = null;
        while (gameIsNotOver()) {

            playMove(playerOne, "X");
            displayBoard();

            if (this.board.gameIsOver()) {
                winner = "X";
                inputOutput.announceWinner(winner);
                return;
            } else {
                playMove(playerTwo, "O");
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

    private void playMove(Player player, String mark) {
        inputOutput.askForMove();
        String playerMove = player.getPlayerMove(this.board);
        this.board.updateMove(playerMove, mark);
    }

}
