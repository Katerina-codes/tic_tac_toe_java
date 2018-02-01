package test.game;

import main.game.Board;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BoardTest {

    private final String PLAYER_ONE = "X";
    private final List<String> emptyBoard = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

    @Test
    public void canMakeMoveOnABoard() {
        Board board = new Board(emptyBoard);
        List grid = board.updateMove("1", PLAYER_ONE);

        assertEquals(PLAYER_ONE, grid.get(0));
    }

    @Test
    public void canReturnRows() {
        Board board = new Board(emptyBoard);
        List<List<String>> rows = board.getRows();

        assertEquals(asList("1", "2", "3"), rows.get(0));
        assertEquals(asList("4", "5", "6"), rows.get(1));
        assertEquals(asList("7", "8", "9"), rows.get(2));
    }

    @Test
    public void canReturnColumns() {
        Board board = new Board(emptyBoard);
        List<List<String>> rows = board.getColumns();

        assertEquals(asList("1", "4", "7"), rows.get(0));
        assertEquals(asList("2", "5", "8"), rows.get(1));
        assertEquals(asList("3", "6", "9"), rows.get(2));
    }

    @Test
    public void canReturnDiagonals() {
        Board board = new Board(emptyBoard);
        List<List<String>> diagonals = board.getDiagonals();

        assertEquals(asList("1", "5", "9"), diagonals.get(0));
        assertEquals(asList("3", "5", "7"), diagonals.get(1));
    }


    @Test
    public void playersCantEnterSameMoveWhenMoveIsX() {
        Board board = new Board(asList("X", "2", "3", "4", "5", "6", "7", "8", "9"));

        assertFalse(board.isMoveAvailable(1));
    }

    @Test
    public void playersCantEnterSameMoveWhenMoveIsO() {
        Board board = new Board(asList("O", "2", "3", "4", "5", "6", "7", "8", "9"));

        assertFalse(board.isMoveAvailable(1));
    }

    @Test
    public void boardHasNoAvailableMovesLeft() {
        Board board = new Board(asList("X", "O", "X", "O", "X", "O", "X", "O", "X"));

        assertFalse(board.hasAvailableMoves());
    }

    @Test
    public void canScoreAHorizontalWin() {
        Board board = new Board(asList("X", "X", "X", "4", "5", "O", "O", "8", "9"));

        assertTrue(board.horizontalWin(PLAYER_ONE));
    }

    @Test
    public void canScoreVerticalWin() {
        Board board = new Board(asList("X", "O", "3", "X", "O", "X", "X", "O", "9"));

        assertTrue(board.columnWin(PLAYER_ONE));
    }

    @Test
    public void canScoreFirstDiagonalWin() {
        Board board = new Board(asList("X", "O", "3", "O", "X", "6", "7", "8", "X"));

        assertTrue(board.diagonalWin(PLAYER_ONE));
    }

    @Test
    public void canScoreSecondDiagonalWin() {
        Board board = new Board(asList("O", "O", "X", "4", "X", "6", "X", "8", "9"));

        assertTrue(board.diagonalWin(PLAYER_ONE));
    }


    @Test
    public void checkIfPlayerHasWon() {
        Board board = new Board(asList("X", "O", "3", "O", "X", "6", "7", "8", "X"));

        assertTrue(board.playerHasWon(PLAYER_ONE));
    }

    @Test
    public void gameIsOverAndXWins() {
        Board board = new Board(asList("X", "O", "3", "O", "X", "6", "7", "8", "X"));

        assertTrue(board.gameIsOver());
    }

    @Test
    public void boardAnnouncesResultOfPlayerOneWin() {
        Board board = new Board(asList("X", "O", "3", "O", "X", "6", "7", "8", "X"));

        assertEquals(PLAYER_ONE, board.findWinner());
    }

    @Test
    public void scoresATie() {
        Board board = new Board(asList("O", "X", "X", "X", "O", "O", "X", "O", "X"));

        assertEquals("Tie", board.findWinner());
    }
}
