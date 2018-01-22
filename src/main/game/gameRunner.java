package main.game;

public class gameRunner {

    public static void main(String[] args) {
        CommandLineUI inputOutput = new CommandLineUI(System.out, System.in);
        Board board = new Board();
        Game game = new Game(inputOutput, board);

        game.runGame();
    }
}
