package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static main.game.Result.TIE;

public class Board {

    public static final int ROW_COUNT = 3;
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

        for (int i = 0; i < ROW_COUNT * ROW_COUNT; i += 3) {
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
        return count != grid.size();
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

        for (int i = 0; i < ROW_COUNT; i++) {
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

    public Result findWinner() {
        if (gameIsTied()) {
            return TIE;
        } else if (playerHasWon(PLAYER_ONE_MARK)) {
            return Result.PLAYER_ONE;
        } else {
            return Result.PLAYER_TWO;
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
        return !playerHasWon(PLAYER_ONE_MARK) && !playerHasWon(PLAYER_TWO_MARK);
    }

}
