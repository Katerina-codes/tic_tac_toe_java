package main.game;

public class gameRunner {

    public static void main(String[] args) {
        CommandLineUI inputOutput = new CommandLineUI(System.out, System.in);
        inputOutput.askForBoardSize();
        int boardSize = inputOutput.getBoardSize();
        Board board = new Board(boardSize);
        Game game = new Game(inputOutput, board);

        game.run();
    }
}
