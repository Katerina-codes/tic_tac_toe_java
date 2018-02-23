package main.game;

import java.util.ArrayList;
import java.util.List;

import static main.game.Mark.*;
import static main.game.Result.TIE;

public class Board {

    private int size;
    public List<Mark> grid;

    public Board(int size, List<Mark> grid) {
        this.size = size;
        this.grid = grid;
    }

    public Board(int size) {
        this.size = size;
        this.grid = createGrid();
    }

    private List<Mark> createGrid() {
        List<Mark> spaces = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            spaces.add(EMPTY);
        }
        return spaces;
    }

    public List<Mark> updateMove(int position, Mark mark) {
        this.grid.set(position, mark);
        return this.grid;
    }

    public boolean isMoveAvailable(int move) {
        return this.grid.get(move - 1) == EMPTY;
    }

    public boolean hasAvailableMoves() {
        int count = 0;

        for (Mark space : this.grid) {
            if (spaceIsTaken(space)) {
                count++;
            }
        }
        return count != grid.size();
    }

    public boolean findWin(Mark playerMark) {
        for (Line line : lines()) {
            if (line.hasWinner(playerMark)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> availableMoves() {
        List<Integer> availableMoves = new ArrayList<>();
        for (int i = 0; i < this.grid.size(); i++) {
            if (this.grid.get(i).equals(EMPTY)) {
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

    public boolean playerHasWon(Mark mark) {
        return findWin(mark);
    }

    public boolean gameIsOver() {
        return playerHasWon(X) || playerHasWon(O) || !hasAvailableMoves();
    }

    public Mark valueAt(int position) {
        return this.grid.get(position);
    }

    private List<Line> rowLines() {
        List<Line> rows = new ArrayList<>();
        for (int i = 0; i < grid.size(); i += size) {
            if (size == 3) {
                Line line = new Line(grid.get(i), grid.get(i + 1), grid.get(i + 2));
                rows.add(line);
            } else {
                Line line = new Line(grid.get(i), grid.get(i + 1), grid.get(i + 2), grid.get(i + 3));
                rows.add(line);
            }
        }
        return rows;
    }

    private Line diagonalOne() {
        Line line = null;
        for (int i = 0; i < size; i += 2) {
            if (size == 3) {
                line = new Line(grid.get(0), grid.get(4), grid.get(8));
            } else {
                line = new Line(grid.get(0), grid.get(5), grid.get(10), grid.get(15));
            }
        }
        return line;
    }

    private Line diagonalTwo() {
        Line line = null;
        for (int i = size - 1; i < size; i += size - 1) {
            if (size == 3) {
                line = new Line(grid.get(i), grid.get(4), grid.get(6));
            } else {
                line = new Line(grid.get(i), grid.get(6), grid.get(9), grid.get(12));
            }
        }
        return line;
    }

    private List<Line> diagonalLines() {
        List<Line> diagonals = new ArrayList<>();
        diagonals.add(diagonalOne());
        diagonals.add(diagonalTwo());
        return diagonals;
    }

    private List<Line> columnLines() {
        List<Line> columns = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (size == 3) {
                Line line = new Line(grid.get(i), grid.get(i + 3), grid.get(i + 6));
                columns.add(line);
            } else {
                Line line = new Line(grid.get(i), grid.get(i + 4), grid.get(i + 8), grid.get(i + 12));
                columns.add(line);
            }
        }
        return columns;
    }

    private boolean spaceIsTaken(Mark space) {
        return space == X || space == O;
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
