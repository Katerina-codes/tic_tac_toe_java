package test.game;

import main.game.Board;
import main.game.Line;
import main.game.Mark;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static main.game.Mark.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void canMakeMoveOnABoard() {
        Board board = new Board(3);
        List<Mark> grid = board.updateMove(0, X);

        assertEquals(X, grid.get(0));
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

    @Test(expected = RuntimeException.class)
    public void throwsExceptionWhenFindWinnerCalledBeforeEndOfGame() {
        Board board = new Board();
        board.findWinner();
    }

    @Test
    public void canScoreARowWinOnThreeByThree() {
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

        assertThat(board.playerHasWon(X), is(true));
        assertThat(board2.playerHasWon(X), is(true));
        assertThat(board3.playerHasWon(X), is(true));
    }

    @Test
    public void canScoreARowWinOnFourByFour() {
        Board board = new Board(4, asList(
                X, X, X, X,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY));
        Board board2 = new Board(4, asList(
                EMPTY, EMPTY, EMPTY, EMPTY,
                X, X, X, X,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY));
        Board board3 = new Board(4, asList(
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                EMPTY, EMPTY, EMPTY, EMPTY,
                X, X, X, X));

        assertTrue(board.playerHasWon(X));
        assertTrue(board2.playerHasWon(X));
        assertTrue(board3.playerHasWon(X));
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

        assertTrue(board.playerHasWon(X));
        assertTrue(board2.playerHasWon(X));
        assertTrue(board3.playerHasWon(X));
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

        assertTrue(board.playerHasWon(X));
        assertTrue(board2.playerHasWon(X));
        assertTrue(board3.playerHasWon(X));
        assertTrue(board4.playerHasWon(X));
    }

    @Test
    public void canScoreADiagonalWinOnThreeByThree() {
        Board board = new Board(3, asList(
                X, EMPTY, EMPTY,
                EMPTY, X, EMPTY,
                EMPTY, EMPTY, X));
        Board board2 = new Board(3, asList(
                EMPTY, EMPTY, X,
                EMPTY, X, EMPTY,
                X, EMPTY, EMPTY));

        assertTrue(board.playerHasWon(X));
        assertTrue(board2.playerHasWon(X));
    }

    @Test
    public void canScoreADiagonalWinOnFourByFour() {
        Board board = new Board(4, asList(
                X, EMPTY, EMPTY, EMPTY,
                EMPTY, X, EMPTY, EMPTY,
                EMPTY, EMPTY, X, EMPTY,
                EMPTY, EMPTY, EMPTY, X));
        Board board2 = new Board(4, asList(
                EMPTY, EMPTY, EMPTY, X,
                EMPTY, EMPTY, X, EMPTY,
                EMPTY, X, EMPTY, EMPTY,
                X, EMPTY, EMPTY, EMPTY));

        assertTrue(board.playerHasWon(X));
        assertTrue(board2.playerHasWon(X));
    }

    @Test
    public void canScoreVerticalWinOnAFourByFour() {
        Board board = new Board(4, asList(O, EMPTY, EMPTY, X, O, O, EMPTY, X, EMPTY, EMPTY, EMPTY, X, EMPTY, EMPTY, EMPTY, X));

        assertTrue(board.playerHasWon(X));
    }


    @Test
    public void canScoreFirstDiagonalWin() {
        Board board = new Board(3, asList(X, O, EMPTY, O, X, EMPTY, EMPTY, EMPTY, X));

        assertTrue(board.playerHasWon(X));
    }

    @Test
    public void canScoreSecondDiagonalWin() {
        Board board = new Board(3, asList(O, O, X, EMPTY, X, EMPTY, X, EMPTY, EMPTY));

        assertTrue(board.playerHasWon(X));
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

