package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static main.game.PlayerTypes.O_MARK;
import static main.game.PlayerTypes.X_MARK;
import static main.game.Result.TIE;

public class Board {

    public static final int ROW_COUNT = 3;
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

    public List<Line> getRowLines() {
        List<Line> rows = new ArrayList<>();

        for (int i = 0; i < ROW_COUNT * ROW_COUNT; i += 3) {
            Line line = new Line(grid.get(i), grid.get(i + 1), grid.get(i + 2));
            rows.add(line);
        }

        return rows;
    }

    public boolean isMoveAvailable(int move) {
        return isMoveTaken(move);
    }

    public boolean hasAvailableMoves() {
        int count = 0;

        for (String space : this.grid) {
            if (space.equals(X_MARK.getPlayer()) || space.equals(O_MARK.getPlayer())) {
                count += 1;
            }
        }
        return count != grid.size();
    }

    public List<Line> getColumnLines() {
        List<Line> columns = new ArrayList<>();

        for (int i = 0; i < ROW_COUNT; i++) {
            Line line = new Line(grid.get(i), grid.get(i + 3), grid.get(i + 6));
            columns.add(line);
        }
        return columns;
    }

    public boolean findWin(String playerMark) {
        for (Line line : lines()) {
            if (line.hasWinner(playerMark)) {
                return true;
            }
        }
        return false;
    }

    public List<Line> getDiagonalLines() {
        List<Line> diagonals = new ArrayList<>();
        Line diagonalOne = new Line(grid.get(0), grid.get(4), grid.get(8));
        Line diagonalTwo = new Line(grid.get(2), grid.get(4), grid.get(6));
        diagonals.add(diagonalOne);
        diagonals.add(diagonalTwo);
        return diagonals;
    }

    public List<String> getAvailableMoves() {
        List<String> availableMoves = new ArrayList<>();
        for (String space : this.grid) {
            if (!space.equals(X_MARK.getPlayer()) && !space.equals(O_MARK.getPlayer())) {
                availableMoves.add(space);
            }
        }
        return availableMoves;
    }


    public Result findWinner() {
        if (gameIsTied()) {
            return TIE;
        } else if (playerHasWon(X_MARK.getPlayer())) {
            return Result.PLAYER_ONE_WIN;
        } else {
            return Result.PLAYER_TWO_WIN;
        }
    }

    public boolean playerHasWon(String playerMark) {
        return findWin(playerMark);
    }

    public boolean gameIsOver() {
        return playerHasWon(X_MARK.getPlayer()) || playerHasWon(O_MARK.getPlayer()) || !hasAvailableMoves();
    }

    private boolean isMoveTaken(int move) {
        return !this.grid.get(move - 1).equals(O_MARK.getPlayer()) && !this.grid.get(move - 1).equals(X_MARK.getPlayer());
    }

    private boolean gameIsTied() {
        return !playerHasWon(X_MARK.getPlayer()) && !playerHasWon(O_MARK.getPlayer());
    }

    private List<Line> lines() {
        List<Line> lines = new ArrayList<>();
        lines.addAll(getDiagonalLines());
        lines.addAll(getColumnLines());
        lines.addAll(getRowLines());
        return lines;
    }

}
