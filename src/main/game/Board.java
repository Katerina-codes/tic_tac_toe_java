package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Board {

    private final String PLAYER_TWO_MARK = "O";
    private final String PLAYER_ONE_MARK = "X";
    public List<String> grid;

    public Board(List<String> grid) {
        this.grid = grid;
    }

    public Board() {
        this.grid = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    public List updateMove(String move, String playerType) {
        int convertedMove = Integer.parseInt(String.valueOf(move)) - 1;
        this.grid.set(convertedMove, playerType);
        return this.grid;
    }

    public List<List<String>> getRows() {
        List<List<String>> rows = new ArrayList<>();
        int boardSize = 3;

        for (int i = 0; i < boardSize * boardSize; i += 3) {
            List<String> row = asList(grid.get(i), grid.get(i + 1), grid.get(i + 2));
            rows.add(row);
        }

        return rows;
    }

    public boolean isMoveAvailable(int move) {
        return isMoveTaken(move);
    }

    public boolean hasAvailableMoves() {
        int count = 0;

        for (String space : this.grid) {
            if (space.equals(PLAYER_ONE_MARK) || space.equals(PLAYER_TWO_MARK)) {
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

    public List<List<String>> getColumns() {
        List<List<String>> rows = new ArrayList<>();
        int boardSize = 3;

        for (int i = 0; i < boardSize; i++) {
            List<String> row = asList(grid.get(i), grid.get(i + 3), grid.get(i + 6));
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

    public List<List<String>> getDiagonals() {
        List<List<String>> diagonals = new ArrayList<>();
        List<String> diagonalOne = asList(grid.get(0), grid.get(4), grid.get(8));
        List<String> diagonalTwo = asList(grid.get(2), grid.get(4), grid.get(6));
        diagonals.add(diagonalOne);
        diagonals.add(diagonalTwo);
        return diagonals;
    }

    public boolean diagonalWin(String playerMark) {
        List<List<String>> diagonals = getDiagonals();

        for (List<String> diagonal : diagonals) {
            if (isWin(playerMark, diagonal)) {
                return true;
            }
        }

        return false;
    }

    public String findWinner() {
        if (gameIsTied()) {
            return "Tie";
        } else if (playerHasWon("X")) {
            return PLAYER_ONE_MARK;
        } else {
            return PLAYER_TWO_MARK;
        }
    }

    public boolean playerHasWon(String playerMark) {
        return horizontalWin(playerMark) || columnWin(playerMark) || diagonalWin(playerMark);
    }

    public boolean gameIsOver() {
        return playerHasWon(PLAYER_ONE_MARK) || playerHasWon(PLAYER_TWO_MARK) || !hasAvailableMoves();
    }

    private boolean isMoveTaken(int move) {
        return !this.grid.get(move - 1).equals(PLAYER_TWO_MARK) && !this.grid.get(move - 1).equals(PLAYER_ONE_MARK);
    }

    private boolean isWin(String playerMark, List<String> row) {
        int markCounter = 0;

        for (String mark : row) {
            if (mark.equals(playerMark)) {
                markCounter++;
            }
        }
        return markCounter == 3;
    }

    private boolean gameIsTied() {
        return neitherPlayerHasWon();
    }

    private boolean neitherPlayerHasWon() {
        return !playerHasWon(PLAYER_ONE_MARK) && !playerHasWon(PLAYER_TWO_MARK);
    }
}
