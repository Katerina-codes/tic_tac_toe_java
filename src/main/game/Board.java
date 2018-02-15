package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static main.game.Marks.O;
import static main.game.Marks.X;
import static main.game.Result.TIE;

public class Board {

    public final int ROW_COUNT = 3;
    public List<Marks> grid;

    public Board(List<Marks> grid) {
        this.grid = grid;
    }

    public Board() {
        this.grid = asList(null, null, null, null, null, null, null, null, null);
    }

    public List<Marks> updateMove(int position, Marks marks) {
        this.grid.set(position - 1, marks);
        return this.grid;
    }

    public List<List<Marks>> rows() {
        List<List<Marks>> rows = new ArrayList<>();

        for (int i = 0; i < ROW_COUNT * ROW_COUNT; i += 3) {
            List<Marks> row = asList(grid.get(i), grid.get(i + 1), grid.get(i + 2));
            rows.add(row);
        }

        return rows;
    }

    public List<Line> rowLines() {
        List<Line> rows = new ArrayList<>();

        for (int i = 0; i < ROW_COUNT * ROW_COUNT; i += 3) {
            Line line = new Line(grid.get(i), grid.get(i + 1), grid.get(i + 2));
            rows.add(line);
        }

        return rows;
    }

    public boolean isMoveAvailable(int move) {
        return this.grid.get(move - 1) == null;
    }

    public boolean hasAvailableMoves() {
        int count = 0;

        for (Marks space : this.grid) {
            if (spaceIsTaken(space)) {
                count++;
            }
        }
        return count != grid.size();
    }

    public List<Line> columnLines() {
        List<Line> columns = new ArrayList<>();

        for (int i = 0; i < ROW_COUNT; i++) {
            Line line = new Line(grid.get(i), grid.get(i + 3), grid.get(i + 6));
            columns.add(line);
        }
        return columns;
    }

    public boolean findWin(Marks playerMark) {
        for (Line line : lines()) {
            if (line.hasWinner(playerMark)) {
                return true;
            }
        }
        return false;
    }

    public List<Line> diagonalLines() {
        List<Line> diagonals = new ArrayList<>();
        Line diagonalOne = new Line(grid.get(0), grid.get(4), grid.get(8));
        Line diagonalTwo = new Line(grid.get(2), grid.get(4), grid.get(6));
        diagonals.add(diagonalOne);
        diagonals.add(diagonalTwo);
        return diagonals;
    }

    public List<Integer> availableMoves() {
        List<Integer> availableMoves = new ArrayList<>();
        for (int i = 0; i < this.grid.size(); i++) {
            if (this.grid.get(i) == null) {
                availableMoves.add(i);
            }
        }

        return availableMoves;
    }

    public Result findWinner() {
        if (gameIsTied()) {
            return TIE;
        } else if (playerHasWon(X)) {
            return Result.PLAYER_ONE_WIN;
        } else {
            return Result.PLAYER_TWO_WIN;
        }
    }

    public boolean playerHasWon(Marks mark) {
        return findWin(mark);
    }

    public boolean gameIsOver() {
        return playerHasWon(X) || playerHasWon(O) || !hasAvailableMoves();
    }

    public Marks valueAt(int position) {
        return this.grid.get(position);
    }

    private boolean spaceIsTaken(Marks space) {
        return space.equals(X) || space.equals(O);
    }

    private boolean gameIsTied() {
        return !playerHasWon(X) && !playerHasWon(O);
    }

    private List<Line> lines() {
        List<Line> lines = new ArrayList<>();
        lines.addAll(diagonalLines());
        lines.addAll(columnLines());
        lines.addAll(rowLines());
        return lines;
    }
}
