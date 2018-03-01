package main.game;

import java.util.ArrayList;
import java.util.List;

import static main.game.Mark.*;
import static main.game.Result.TIE;

public class Board {

    public int size;
    public List<Mark> grid;

    public Board() {
        this(3);
    }

    public Board(int size) {
        this.size = size;
        this.grid = createGrid();
    }

    public Board(int size, List<Mark> grid) {
        this.size = size;
        this.grid = grid;
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
        } else if (playerHasWon(O)) {
            return Result.PLAYER_TWO_WIN;
        } else {
            throw new RuntimeException("No winner.");
        }

    }

    public boolean playerHasWon(Mark mark) {
        for (Line line : lines()) {
            if (line.hasWinner(mark)) {
                return true;
            }
        }
        return false;
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

            ArrayList<Mark> rowElements = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                rowElements.add(grid.get(i + j));
            }
            rows.add(new Line(rowElements));
        }
        return rows;
    }

    private Line diagonalOne() {
        ArrayList<Mark> diagonal = new ArrayList<>();

        for (int i = 0; i < grid.size(); i += (size + 1)) {
            diagonal.add(grid.get(i));
        }
        return new Line(diagonal);
    }

    private Line diagonalTwo() {
        ArrayList<Mark> diagonal = new ArrayList<>();

        for (int i = size - 1; i < grid.size() - (size - 1); i += size - 1) {
            diagonal.add(grid.get(i));
        }
        return new Line(diagonal);
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
            List<Mark> columnElements = new ArrayList<>();

            for (int j = i; j < grid.size(); j += size) {
                columnElements.add(grid.get(j));
            }
            columns.add(new Line(columnElements));
        }
        return columns;
    }

    private boolean spaceIsTaken(Mark space) {
        return space == X || space == O;
    }

    private boolean gameIsTied() {
        return !playerHasWon(X) && !playerHasWon(O) && !hasAvailableMoves();
    }

    private List<Line> lines() {
        List<Line> lines = new ArrayList<>();
        lines.addAll(diagonalLines());
        lines.addAll(columnLines());
        lines.addAll(rowLines());
        return lines;
    }
}
