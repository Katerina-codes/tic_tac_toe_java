package test.game;

import main.game.Board;
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
    public void canMakeMoveOnABoard() {
        Board board = new Board();
        ArrayList grid = board.updateMove(0, "X");

        assertEquals("X", grid.get(0));
    }
}
