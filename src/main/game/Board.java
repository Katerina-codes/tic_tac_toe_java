package main.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public List<Mark> createGrid() {
        Stream<Mark> spaces = Stream.generate(() -> EMPTY).limit(size * size);
        return spaces.collect(Collectors.toList());
    }

    public List<Mark> updateMove(int position, Mark mark) {
        this.grid.set(position, mark);
        return this.grid;
    }

    public boolean isMoveAvailable(int move) {
        return this.grid.get(move - 1) == EMPTY;
    }

    public boolean hasAvailableMoves() {
        return this.grid.stream().anyMatch(space -> space.equals(EMPTY));
    }

    public List<Integer> availableMoves() {
        return IntStream.range(0, this.grid.size()).filter(index -> this.grid.get(index).equals(EMPTY)).boxed().collect(Collectors.toList());
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
        return lines().stream().anyMatch(line -> line.hasWinner(mark));
    }

    public boolean gameIsOver() {
        return playerHasWon(X) || playerHasWon(O) || !hasAvailableMoves();
    }

    public Mark valueAt(int position) {
        return this.grid.get(position);
    }

    private List<Line> lines() {
        List<Line> lines = new ArrayList<>();
        lines.addAll(diagonalLines());
        lines.addAll(columnLines());
        lines.addAll(rowLines());
        return lines;
    }

    private List<Line> rowLines() {
        List<Line> rows = new ArrayList<>();
        for (int i = 0; i < grid.size(); i += size) {
            rowElements(rows, i);
        }
        return rows;
    }

    private void rowElements(List<Line> rows, int i) {
       List<Integer> indicesForRow = IntStream.range(0, size).boxed().collect(Collectors.toList());
       List<Integer> rowIndices = indicesForRow.stream().map(element -> element + i).collect(Collectors.toList());
       List<Mark> rowMarks = rowIndices.stream().map(element -> grid.get(element)).collect(Collectors.toList());

       rows.add(new Line(rowMarks));
    }

    private Line diagonalOne() {
        List<Integer> diagonalOne = IntStream.range(0, grid.size()).filter(index -> index % (size + 1) == 0).boxed().collect(Collectors.toList());
        List<Mark> diagonalElements = diagonalOne.stream().map(element -> this.grid.get(element)).collect(Collectors.toList());
        return new Line(diagonalElements);
    }

    private Line diagonalTwo() {
        List<Integer> diagonal = IntStream.range(size - 1, grid.size() - (size - 1)).filter(index -> index % (size - 1) == 0).boxed().collect(Collectors.toList());
        List<Mark> diagonalElements = diagonal.stream().map(element -> this.grid.get(element)).collect(Collectors.toList());
        return new Line(diagonalElements);
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

    private boolean gameIsTied() {
        return !playerHasWon(X) && !playerHasWon(O) && !hasAvailableMoves();
    }
}
