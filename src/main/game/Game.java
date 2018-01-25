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
        Player playerOne = new Player(inputOutput);
        Player playerTwo = new Player(inputOutput);

        displayBoard();

        String winner = null;
        while (!this.board.gameIsOver()) {

            playMove(playerOne, grid, "X");
            displayBoard();

            if (this.board.gameIsOver()) {
                winner = "X";
                inputOutput.announceWinner(winner);
                return;
            } else {
                playMove(playerTwo, grid, "O");
                winner = "O";
                displayBoard();
            }
        }
        displayBoard();
        inputOutput.announceWinner(winner);
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
