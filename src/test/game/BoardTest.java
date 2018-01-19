package test.game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void createThreeByThreeBoard() {
        Board board = new Board();
        ArrayList grid = board.createGrid();

        assertEquals(9, grid.size());
    }

    @Test
    public void moveIsUpdatedOnBoard() {
        Board board = new Board();
        ArrayList grid = board.updateMove(0);

        assertEquals("X", grid.get(0));
        assertEquals(9, grid.size());
    }
}
