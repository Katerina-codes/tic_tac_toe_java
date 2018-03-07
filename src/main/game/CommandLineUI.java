package main.game;

import java.io.*;
import java.util.List;

public class CommandLineUI implements UI {

    public static final String OPTION_ONE = "1";
    public static final String OPTION_TWO = "2";
    public static final String OPTION_THREE = "3";
    private final PrintStream output;
    private final BufferedReader input;

    public CommandLineUI(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new BufferedReader(new InputStreamReader(input));
    }

    public void askForBoardSize() {
        output.println("Please enter " + OPTION_ONE + " for a 3x3 grid\n" +
                "Please enter " + OPTION_TWO + " for a 4x4 grid:");
    }

    public void askForGameMode() {
        output.println("Enter " + OPTION_ONE + " for Human vs Human\n" +
                "Enter " + OPTION_TWO + " for Human vs Computer\n" +
                "Enter " + OPTION_THREE + " for Computer vs Human");
    }

    public String getUserChoice() {
        String gameMode = "";
        try {
            gameMode = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            output.println("Nothing was entered.");
        }
        return gameMode;
    }

    public void askForMove(Mark playerMark, List<Mark> grid) {
        output.format("Player %s place your mark! Pick a move from 1 - %s:\n\n", playerMark, Integer.toString(grid.size()));
    }

    public String getMove(Board board) {
        String move = getMove();

        int convertedMove = Integer.parseInt(move);

        while (moveIsNotAvailable(board, convertedMove)) {
            move = getValidMove();
            convertedMove = Integer.parseInt(move);

        }
        return move;
    }

    public void displayBoard(List<Mark> grid, int size) {
        StringBuilder sb = new StringBuilder();
        String formattedRows = null;
        sb.append("\n");

        for (int i = 0; i < grid.size(); i++) {
            sb.append(" ");
            if (grid.get(i) == Mark.EMPTY) {
                sb.append(i + 1);
            } else {
                sb.append(grid.get(i));
            }

            if ((i + 1) % size == 0) {
                sb.append("\n");
            }
            formattedRows = sb.toString();
        }
        output.println(formattedRows);
    }

    public void announceWinner(Result result) {
        if (result.equals(Result.TIE)) {
            output.println("It's a tie!");
        } else {
            output.format("%s won!", result.getResult());
        }
    }

    private boolean moveIsNotAvailable(Board board, int convertedMove) {
        return !board.isMoveAvailable(convertedMove);
    }

    private String getValidMove() {
        output.println("\nThis move is taken. Place another one:");
        return getMove();
    }

    private String getMove() {
        String move = "";
        try {
            move = input.readLine();
        } catch (IOException e) {
            output.println("Nothing was entered.");
        }
        return move;
    }
}
