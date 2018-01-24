package test.game;

import main.game.Board;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void createThreeByThreeBoard() {
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List grid = board.createGrid();

        assertEquals(9, grid.size());
    }

    @Test
    public void canMakeMoveOnABoard() {
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List grid = board.updateMove("1", "X");

        assertEquals("X", grid.get(0));
    }

    @Test
    public void canReturnRows() {
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<List<String>> rows = board.getRows();

        assertEquals(asList("1", "2", "3"), rows.get(0));
        assertEquals(asList("4", "5", "6"), rows.get(1));
        assertEquals(asList("7", "8", "9"), rows.get(2));
    }

    @Test
    public void canReturnColumns() {
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<List<String>> rows = board.getColumns();

        assertEquals(asList("1", "4", "7"), rows.get(0));
        assertEquals(asList("2", "5", "8"), rows.get(1));
        assertEquals(asList("3", "6", "9"), rows.get(2));
    }

    @Test
    public void canReturnDiagonals() {
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<List<String>> diagonals = board.getDiagonals();

        assertEquals(asList("1", "5", "9"), diagonals.get(0));
        assertEquals(asList("9", "5", "1"), diagonals.get(1));
    }


    @Test
    public void playersCantEnterSameMove() {
        Board board = new Board(asList("X", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<String> grid = board.board;

        assertFalse(board.isMoveUnique(grid, 1));
    }

    @Test
    public void boardHasNoAvailableMovesLeft() {
        Board board = new Board(asList("X", "O", "X", "O", "X", "O", "X", "O", "X"));
        List<String> grid = board.board;

        assertFalse(board.hasAvailableMoves(grid));
    }

    @Test
    public void canScoreAHorizontalWin() {
        Board board = new Board(asList("X", "X", "X", "4", "5", "O", "O", "8", "9"));
        String playerMark = "X";

        assertTrue(board.horizontalWin(playerMark));
    }

    @Test
    public void canScoreVerticalWin() {
        Board board = new Board(asList("X", "O", "3", "X", "O", "X", "X", "O", "9"));
        String playerMark = "X";

        assertTrue(board.columnWin(playerMark));
    }
}
