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

    public void askForMove() {
        output.println("Place your mark! Pick a move from 1 - 9:");
    }

    public String getPlayerMove() {
        String move = null;
        try {
            return input.readLine();
        } catch (IOException e) {
            System.out.println("Nothing was entered.");
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

    private String formatBoard(String formattedRows) {
        return formattedRows
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

}
