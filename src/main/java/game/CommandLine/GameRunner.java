package game.CommandLine;

import game.core.Board;
import game.core.Game;

public class GameRunner {

    public static void main(String[] args) {
        CommandLineUI inputOutput = new CommandLineUI(System.out, System.in);
        inputOutput.askForBoardSize();
        int boardSize = inputOutput.getBoardSize();
        Board board = new Board(boardSize);
        Game game = new Game(inputOutput, board);

        game.run();
    }
}
