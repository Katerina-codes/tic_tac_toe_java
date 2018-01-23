package test.game;

import main.game.Board;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BoardTest {

    @Test
    public void createThreeByThreeBoard() {
        Board board = new Board();
        List grid = board.createGrid();

        assertEquals(9, grid.size());
    }

    @Test
    public void canMakeMoveOnABoard() {
        Board board = new Board();
        List grid = board.updateMove("1", "X");

        assertEquals("X", grid.get(0));
    }

    @Test
    public void canReturnRows() {
        Board board = new Board();
        List<List<String>> rows = board.getRows();

        assertEquals(asList("1", "2", "3"), rows.get(0));
        assertEquals(asList("4", "5", "6"), rows.get(1));
        assertEquals(asList("7", "8", "9"), rows.get(2));
    }

    @Test
    public void playersCantEnterSameMove() {
        Board board = new Board();
        List<String> grid = asList("X", "2", "3", "4", "5", "6", "7", "8", "9");

        assertFalse(board.isMoveUnique(grid, 0));
    }

    @Test
    public void boardHasNoAvailableMovesLeft() {
        Board board = new Board();
        List<String> grid = asList("X", "O", "X", "O", "X", "O", "X", "O", "X");

        assertFalse(board.hasAvailableMoves(grid));
    }
}
