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
}
