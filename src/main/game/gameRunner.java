package main.game;

import static java.util.Arrays.asList;

public class gameRunner {

    public static void main(String[] args) {
        CommandLineUI inputOutput = new CommandLineUI(System.out, System.in);
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Game game = new Game(inputOutput, board);

        game.runGame();
    }
}
