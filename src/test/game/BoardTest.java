package test.game;

import main.game.Board;
import main.game.Line;
import main.game.Marks;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static main.game.Marks.O;
import static main.game.Marks.X;
import static org.junit.Assert.*;

public class BoardTest {

    private final List<Marks> emptyBoard = asList(null, null, null, null, null, null, null, null, null);
//    private final List<String> emptyBoard = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

    @Test
    public void canMakeMoveOnABoard() {
        Board board = new Board();
        List<Marks> grid = board.updateMove(1, X);

        assertEquals(X, grid.get(0));
    }

    @Test
    public void canReturnRows() {
        Board board = new Board();
        List<Line> rows = board.rowLines();
        assertEquals(createNewLine(null, null, null), rows.get(0));
        assertEquals(createNewLine(null, null, null), rows.get(1));
        assertEquals(createNewLine(null, null, null), rows.get(2));
    }

    @Test
    public void canReturnColumns() {
        Board board = new Board();
        List<Line> rows = board.columnLines();

        assertEquals(createNewLine(null, null, null), rows.get(0));
        assertEquals(createNewLine(null, null, null), rows.get(1));
        assertEquals(createNewLine(null, null, null), rows.get(2));
    }

    @Test
    public void canReturnDiagonals() {
        Board board = new Board();
        List<Line> diagonals = board.diagonalLines();

        assertEquals(createNewLine(null, null, null), diagonals.get(0));
        assertEquals(createNewLine(null, null, null), diagonals.get(1));
    }

    @Test
    public void returnsListOfNineAvailableMoves() {
        Board board = new Board();

        assertEquals(9, board.availableMoves().size());
    }

    @Test
    public void returnsAListOfOneMove() {
        Board board = new Board(asList(null, O, X, O, X, O, X, O, X));
        List<Integer> possibleMoves = asList(0);

        assertEquals(possibleMoves, board.availableMoves());
    }

    @Test
    public void playersCantEnterSameMoveWhenMoveIsX() {
        Board board = new Board(asList(X, null, null, null, null, null, null, null, null));

        assertFalse(board.isMoveAvailable(1));
    }

    @Test
    public void playersCantEnterSameMoveWhenMoveIsO() {
        Board board = new Board(asList(O, null, null, null, null, null, null, null, null));

        assertFalse(board.isMoveAvailable(1));
    }

    @Test
    public void boardHasNoAvailableMovesLeft() {
        Board board = new Board(asList(X, O, X, O, X, O, X, O, X));

        assertFalse(board.hasAvailableMoves());
    }

    @Test
    public void canScoreAHorizontalWin() {
        Board board = new Board(asList(X, X, X, null, null, O, O, null, null));

        assertTrue(board.findWin(X));
    }

    @Test
    public void canScoreVerticalWin() {
        Board board = new Board(asList(X, O, null, X, O, X, X, O, null));

        assertTrue(board.findWin(X));
    }

    @Test
    public void canScoreFirstDiagonalWin() {
        Board board = new Board(asList(X, O, null, O, X, null, null, null, X));

        assertTrue(board.findWin(X));
    }

    @Test
    public void canScoreSecondDiagonalWin() {
        Board board = new Board(asList(O, O, X, null, X, null, X, null, null));

        assertTrue(board.findWin(X));
    }


    @Test
    public void checkIfPlayerHasWon() {
        Board board = new Board(asList(X, O, null, O, X, null, null, null, X));

        assertTrue(board.playerHasWon(X));
    }

    @Test
    public void gameIsOverAndXWins() {
        Board board = new Board(asList(X, O, null, O, X, null, null, null, X));

        assertTrue(board.gameIsOver());
    }

    @Test
    public void boardAnnouncesResultOfPlayerOneWin() {
        Board board = new Board(asList(X, O, null, O, X, null, null, null, X));

        assertEquals("X", board.findWinner().getResult());
    }

    @Test
    public void scoresATie() {
        Board board = new Board(asList(O, X, X, X, O, O, X, O, X));

        assertEquals("Tie", board.findWinner().getResult());
    }

    private Line createNewLine(Marks spaceOne, Marks spaceTwo, Marks spaceThree) {
        return new Line(spaceOne, spaceTwo, spaceThree);
    }
}

