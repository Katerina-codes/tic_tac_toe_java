package main.game;

public class gameRunner {

    public static void main(String[] args) {
        CommandLineUI inputOutput = new CommandLineUI(System.out, System.in);
        inputOutput.askForBoardSize();
        int userChoice = Integer.parseInt(inputOutput.getUserChoice());
        int boardSize;
        if (userChoice == 1) {
            boardSize = 3;
        } else {
            boardSize = 4;
        }
        Board board = new Board(boardSize);
        Game game = new Game(inputOutput, board);

        game.runGame();
    }
}
