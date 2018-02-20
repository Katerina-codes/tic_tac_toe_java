package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static main.game.Mark.*;
import static main.game.Result.TIE;

public class Board {

    private final int ROW_SIZE = 3;
    private int size = 3;
    public List<Mark> grid;

    public Board(List<Mark> grid) {
        this.grid = grid;
    }

    public Board() {
        this.grid = asList(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY);
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
        this.grid.set(position - 1, mark);
        return this.grid;
    }

    public List<Line> rowLines() {
        List<Line> rows = new ArrayList<>();
        for (int i = 0; i < size * size; i += size) {
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

    public List<Line> columnLines() {
        List<Line> columns = new ArrayList<>();

        for (int i = 0; i < ROW_SIZE; i++) {
            Line line = new Line(grid.get(i), grid.get(i + 3), grid.get(i + 6));
            columns.add(line);
        }
        return columns;
    }

    public boolean findWin(Mark playerMark) {
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
            if (this.grid.get(i) == EMPTY) {
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
