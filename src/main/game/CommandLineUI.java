package main.game;

import java.util.List;

public class CommandLineUI {
    public String displayBoard(List<List<String>> rows) {
        StringBuilder sb = new StringBuilder();
        String result = null;
        for (int index = 0; index <= rows.size() - 1; index++) {
            List<String> row = rows.get(index);
            sb.append(row);
            sb.append("\n");
            result = sb.toString();
        }
        return formatBoard(result);
    }

    private String formatBoard(String result) {
        return result
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }
}
