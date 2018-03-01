package main.game;

public class gameRunner {

    public static final int THREE_BY_THREE = 3;
    public static final int FOUR_BY_FOUR = 4;
    public static final int FIRST_CHOICE = 1;

    public static void main(String[] args) {
        CommandLineUI inputOutput = new CommandLineUI(System.out, System.in);
        inputOutput.askForBoardSize();
        int userChoice = Integer.parseInt(inputOutput.getUserChoice());
        int boardSize;
        if (userChoice == FIRST_CHOICE) {
            boardSize = THREE_BY_THREE;
        } else {
            boardSize = FOUR_BY_FOUR;
        }
        Board board = new Board(boardSize);
        Game game = new Game(inputOutput, board);

        game.run();
    }
}
