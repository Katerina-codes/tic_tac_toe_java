package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Board {

    public List<String> board;

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

    public boolean horizontalWin(String playerMark) {
        List<List<String>> rows = getRows();

        for (List<String> row : rows) {
            if (isWin(playerMark, row)) {
                return true;
            }
        }

        return false;
    }

    private boolean isWin(String playerMark, List<String> row) {
        int markCounter = 0;

        for (String mark : row) {
            if (mark == playerMark) {
                markCounter++;
            }
        }
        if (markCounter == 3) {
            return true;
        }
        return false;
    }

    public List<List<String>> getColumns() {
        List<List<String>> rows = new ArrayList<>();
        int boardSize = 3;

        for (int i = 0; i < boardSize; i++) {
            List<String> row = asList(board.get(i), board.get(i + 3), board.get(i + 6));
            rows.add(row);
        }
        return rows;
    }

    public boolean columnWin(String playerMark) {
        List<List<String>> columns = getColumns();

        for (List<String> column : columns) {
            if (isWin(playerMark, column)) {
                return true;
            }
        }
        return false;
    }
}
