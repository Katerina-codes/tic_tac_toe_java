package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Board {

    private List<String> board;

    public Board(List<String> board) {
        this.board = board;
    }

    public List<String> createGrid() {
        return board;
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
        return !grid.get(move - 1).equals("X") && (!grid.get(move - 1).equals("O"));
    }

    public boolean hasAvailableMoves(List<String> grid) {
        int count = 0;
        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i).equals("X") || grid.get(i).equals("O")) {
                count += 1;
            }
        }
        return count != 9;
    }
}
