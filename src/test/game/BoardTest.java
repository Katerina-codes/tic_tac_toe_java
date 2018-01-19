package test.game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static test.game.Board.player1Move;

public class BoardTest {

    @Test
    public void createThreeByThreeBoard() {
        Board board = new Board();
        ArrayList grid = board.createGrid();

        assertEquals(9, grid.size());
    }

    @Test
    public void playerOneMoveIsUpdatedOnBoard() {
        Board board = new Board();
        ArrayList grid = board.updateMove(0, "X");

        assertEquals(player1Move, grid.get(0));
        assertEquals(9, grid.size());
    }

    @Test
    public void playerTwoMoveIsUpdatedOnBoard() {
        Board board = new Board();
        ArrayList grid = board.updateMove(0, "O");

        assertEquals("O", grid.get(0));
        assertEquals(9, grid.size());
    }
}
