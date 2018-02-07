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
        output.println("Enter '1' for Human vs Human\nEnter '2' for Human vs Computer");
    }

    public String getGameMode() {
        String gameMode = "";
        try {
            gameMode = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nothing was entered.");
        }
        return gameMode;
    }

    public void askForMove() {
        output.println("Place your mark! Pick a move from 1 - 9:");
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

    public void displayBoard(List<List<String>> rows) {
        StringBuilder sb = new StringBuilder();
        String formattedRows = null;
        for (int index = 0; index <= rows.size() - 1; index++) {
            List<String> row = rows.get(index);
            sb.append(row);
            sb.append("\n");
            formattedRows = sb.toString();
        }
        output.println(formatBoard(formattedRows));
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
            System.out.println("Nothing was entered.");
        }
        return move;
    }

    private String formatBoard(String formattedRows) {
        return formattedRows
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

}
