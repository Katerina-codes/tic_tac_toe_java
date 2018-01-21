package test.game;

import main.game.Board;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
        List grid = board.updateMove(0, "X");

        assertEquals("X", grid.get(0));
    }

    @Test
    public void canReturnRows() {
        Board board = new Board();
        List<List<Integer>> rows = board.getRows();

        assertEquals(asList(1, 2, 3), rows.get(0));
        assertEquals(asList(4, 5, 6), rows.get(1));
        assertEquals(asList(7, 8, 9), rows.get(2));
    }
}
