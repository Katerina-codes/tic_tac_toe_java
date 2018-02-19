package main.game;

import java.util.List;

public class Game {

    private final UI ui;
    private final Board board;
    private Player currentPlayer;

    public Game(UI ui, Board board) {
        this.ui = ui;
        this.board = board;
    }

    public void runGame() {
        ui.askForGameMode();
        String gameMode = ui.getGameMode();
        PlayerFactory playerTypes = new PlayerFactory(ui);
        List<Player> players = playerTypes.getPlayerTypes(gameMode);

        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);

        currentPlayer = playerOne;
        displayBoard();

        while (gameIsNotOver()) {
            ui.askForMove(currentPlayer.getMark());
            currentPlayer.playMove(board);
            displayBoard();
            switchPlayer(playerOne, playerTwo);
        }
        Result result = board.findWinner();
        ui.announceWinner(result);
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
        List<Mark> activeBoard = this.board.grid;
        ui.displayBoard(activeBoard);
    }

}
