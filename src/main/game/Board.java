package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Board {

    private List<String> board;

    public Board() {
        this.board = createGrid();
    }

    public List<String> createGrid() {
        return asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    public List updateMove(String move, String playerType) {
        int convertedMove = Integer.parseInt(String.valueOf(move)) - 1;
        board.set(convertedMove, playerType);
        return board;
    }

    public List<List<String>> getRows() {
        List<List<String>> rows = new ArrayList<>();
        int boardSize = 3;

        for (int i = 0; i < boardSize * boardSize; i += 3) {
            List<String> row = asList(board.get(i), board.get(i + 1), board.get(i + 2));
            rows.add(row);
        }

        return rows;
    }

    public boolean isMoveUnique(List<String> grid, int move) {
        if (grid.get(move).equals("X") || (grid.get(move).equals("O"))) {
            return false;
        } else {
            return true;
        }
    }
}
