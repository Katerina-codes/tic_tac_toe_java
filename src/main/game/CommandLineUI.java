package main.game;

import java.io.*;
import java.util.List;

public class CommandLineUI implements UI {

    private final PrintStream output;
    private final BufferedReader input;

    public CommandLineUI(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new BufferedReader(new InputStreamReader(input));
    }

    public void askForGameMode() {
        output.println("Enter '1' for Human vs Human\n" +
                "Enter '2' for Human vs Computer\n" +
                "Enter '3' for Computer vs Human");
    }

    public String getGameMode() {
        String gameMode = "";
        try {
            gameMode = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            output.println("Nothing was entered.");
        }
        return gameMode;
    }

    public void askForMove(Mark playerMark) {
        output.format("\nPlayer %s place your mark! Pick a move from 1 - 9:\n\n", playerMark);
    }

    public String getValidMove(Board board) {
        String move = getMove();

        int convertedMove = Integer.parseInt(move);

        while (moveIsNotAvailable(board, convertedMove)) {
            move = getValidMove();
            convertedMove = Integer.parseInt(move);

        }
        return move;
    }

    public void displayBoard(List<Mark> grid) {
        StringBuilder sb = new StringBuilder();
        String formattedRows = null;

        for (int i = 0; i < grid.size(); i++) {
            sb.append(" ");
            if (grid.get(i) == Mark.EMPTY) {
                sb.append(i + 1);
            } else {
                sb.append(grid.get(i));
            }

            if (grid.size() == 9) {
                if ((i + 1) % 3 == 0) {
                    sb.append("\n");
                }
            } else {
                if ((i + 1) % 4 == 0) {
                    sb.append("\n");
                }
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
        output.println("This move is taken. Place another one:");
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
