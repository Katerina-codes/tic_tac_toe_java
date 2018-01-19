package test.game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    public final String playerOne = "X";
    public final String playerTwo = "O";

    @Test
    public void createThreeByThreeBoard() {
        Board board = new Board();
        ArrayList grid = board.createGrid();

        assertEquals(9, grid.size());
    }

    @Test
    public void playerOneMoveIsUpdatedOnBoard() {
        Board board = new Board();
        ArrayList grid = board.updateMove(0, playerOne);

        assertEquals(playerOne, grid.get(0));
        assertEquals(9, grid.size());
    }

    @Test
    public void playerTwoMoveIsUpdatedOnBoard() {
        Board board = new Board();
        ArrayList grid = board.updateMove(0, playerTwo);

        assertEquals(playerTwo, grid.get(0));
        assertEquals(9, grid.size());
    }
}
