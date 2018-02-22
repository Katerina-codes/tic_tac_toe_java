package test.game;

import main.game.Board;
import main.game.Line;
import main.game.Mark;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.List;

import static java.util.Arrays.asList;
import static main.game.Mark.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void canMakeMoveOnABoard() {
        Board board = new Board(3);
        List<Mark> grid = board.updateMove(1, X);

        assertEquals(X, grid.get(0));
    }

    @Test
    public void canReturnRows() {
        Board board = new Board(3, asList(X, X, X, O, O, O, X, O, X));

        List<Line> rows = board.rowLines();

        assertEquals(createNewLine(X, X, X), rows.get(0));
        assertEquals(createNewLine(O, O, O), rows.get(1));
        assertEquals(createNewLine(X, O, X), rows.get(2));
    }

    @Test
    public void canReturnRowsForAFourByFour() {
        Board board = new Board(4);
        List<Line> rows = board.rowLines();
        assertEquals(new Line(EMPTY, EMPTY, EMPTY, EMPTY), rows.get(0));
        assertEquals(new Line(EMPTY, EMPTY, EMPTY, EMPTY), rows.get(1));
        assertEquals(new Line(EMPTY, EMPTY, EMPTY, EMPTY), rows.get(2));
        assertEquals(new Line(EMPTY, EMPTY, EMPTY, EMPTY), rows.get(3));
    }

    @Test
    public void canReturnDiagonals() {
        Board board = new Board(3);
        List<Line> diagonals = board.diagonalLines();

        assertEquals(createNewLine(EMPTY, EMPTY, EMPTY), diagonals.get(0));
        assertEquals(createNewLine(EMPTY, EMPTY, EMPTY), diagonals.get(1));
    }

    @Test
    public void canReturnDiagonalsForAFourByFour() {
        Board board = new Board(4);
        List<Line> diagonals = board.diagonalLines();

        assertEquals(new Line(EMPTY, EMPTY, EMPTY, EMPTY), diagonals.get(0));
        assertEquals(new Line(EMPTY, EMPTY, EMPTY, EMPTY), diagonals.get(1));
    }

    @Test
    public void returnsListOfNineAvailableMoves() {
        Board board = new Board(3);

        assertEquals(9, board.availableMoves().size());
    }

    @Test
    public void returnsAListOfOneMove() {
        Board board = new Board(3, asList(EMPTY, O, X, O, X, O, X, O, X));
        List<Integer> possibleMoves = asList(0);

        assertThat(board.availableMoves(), is(possibleMoves));
    }

    @Test
    public void playersCantEnterSameMoveWhenMoveIsX() {
        Board board = new Board(3, asList(X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));

        assertFalse(board.isMoveAvailable(1));
    }

    @Test
    public void playersCantEnterSameMoveWhenMoveIsO() {
        Board board = new Board(3, asList(O, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));

        assertFalse(board.isMoveAvailable(1));
    }

    @Test
    public void boardHasNoAvailableMovesLeft() {
        Board board = new Board(3, asList(X, O, X, O, X, O, X, O, X));

        assertFalse(board.hasAvailableMoves());
    }

    @Test
    public void canScoreAHorizontalWin() {
        Board board = new Board(3, asList(
                X, X, X,
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY));
        Board board2 = new Board(3, asList(
                EMPTY, EMPTY, EMPTY,
                X, X, X,
                EMPTY, EMPTY, EMPTY));
        Board board3 = new Board(3, asList(
                EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY,
                X, X, X));

        assertTrue(board.findWin(X));
        assertTrue(board2.findWin(X));
        assertTrue(board3.findWin(X));
    }

    @Test
    public void canScoreWinForAColumnOnAThreeByThree() {
        Board board = new Board(3, asList(
                X, EMPTY, EMPTY,
                X, EMPTY, EMPTY,
                X, EMPTY, EMPTY));
        Board board2 = new Board(3, asList(
                EMPTY, X, EMPTY,
                EMPTY, X, EMPTY,
                EMPTY, X, EMPTY));
        Board board3 = new Board(3, asList(
                EMPTY, EMPTY, X,
                EMPTY, EMPTY, X,
                EMPTY, EMPTY, X));

        assertTrue(board.findWin(X));
        assertTrue(board2.findWin(X));
        assertTrue(board3.findWin(X));
    }

    @Test
    public void canScoreWinForAColumnOnAFourByFour() {
        Board board = new Board(4, asList(
                X, EMPTY, EMPTY, EMPTY,
                X, EMPTY, EMPTY, EMPTY,
                X, EMPTY, EMPTY, EMPTY,
                X, EMPTY, EMPTY, EMPTY));
        Board board2 = new Board(4, asList(
                EMPTY, X, EMPTY, EMPTY,
                EMPTY, X, EMPTY, EMPTY,
                EMPTY, X, EMPTY, EMPTY,
                EMPTY, X, EMPTY, EMPTY));
        Board board3 = new Board(4, asList(
                EMPTY, EMPTY, X, EMPTY,
                EMPTY, EMPTY, X, EMPTY,
                EMPTY, EMPTY, X, EMPTY,
                EMPTY, EMPTY, X, EMPTY));
        Board board4 = new Board(4, asList(
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, EMPTY, X));

        assertTrue(board.findWin(X));
        assertTrue(board2.findWin(X));
        assertTrue(board3.findWin(X));
        assertTrue(board4.findWin(X));
    }

    @Test
    public void canScoreVerticalWinOnAFourByFour() {
        Board board = new Board(4, asList(O, EMPTY, EMPTY, X, O, O, EMPTY, X, EMPTY, EMPTY, EMPTY, X, EMPTY, EMPTY, EMPTY, X));

        assertTrue(board.findWin(X));
    }


    @Test
    public void canScoreFirstDiagonalWin() {
        Board board = new Board(3, asList(X, O, EMPTY, O, X, EMPTY, EMPTY, EMPTY, X));

        assertTrue(board.findWin(X));
    }

    @Test
    public void canScoreSecondDiagonalWin() {
        Board board = new Board(3, asList(O, O, X, EMPTY, X, EMPTY, X, EMPTY, EMPTY));

        assertTrue(board.findWin(X));
    }


    @Test
    public void checkIfPlayerHasWon() {
        Board board = new Board(3, asList(X, O, EMPTY, O, X, EMPTY, EMPTY, EMPTY, X));

        assertTrue(board.playerHasWon(X));
    }

    @Test
    public void gameIsOverAndXWins() {
        Board board = new Board(3, asList(X, O, EMPTY, O, X, EMPTY, EMPTY, EMPTY, X));

        assertTrue(board.gameIsOver());
    }

    @Test
    public void boardAnnouncesResultOfPlayerOneWin() {
        Board board = new Board(3, asList(X, O, EMPTY, O, X, EMPTY, EMPTY, EMPTY, X));

        assertEquals("X", board.findWinner().getResult());
    }

    @Test
    public void scoresATie() {
        Board board = new Board(3, asList(O, X, X, X, O, O, X, O, X));

        assertEquals("Tie", board.findWinner().getResult());
    }

    private Line createNewLine(Mark spaceOne, Mark spaceTwo, Mark spaceThree) {
        return new Line(spaceOne, spaceTwo, spaceThree);
    }
}

