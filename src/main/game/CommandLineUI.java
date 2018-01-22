package main.game;

import java.io.PrintStream;
import java.util.List;

public class CommandLineUI {

    private final PrintStream output;

    public CommandLineUI(PrintStream output) {
        this.output = output;
    }
    public void displayBoard(List<List<String>> rows) {
        StringBuilder sb = new StringBuilder();
        String result = null;
        for (int index = 0; index <= rows.size() - 1; index++) {
            List<String> row = rows.get(index);
            sb.append(row);
            sb.append("\n");
            result = sb.toString();
        }
        output.println(formatBoard(result));
    }

    private String formatBoard(String result) {
        return result
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    public void askForMove() {
        output.println("Place your mark! Pick a move from 1 - 9:");
    }
}
