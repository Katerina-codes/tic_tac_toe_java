package test.game;

import main.game.Board;
import main.game.UnbeatableComputer;
import org.junit.Test;

import static java.util.Arrays.asList;
import static main.game.Mark.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UnbeatableComputerTest {

    @Test
    public void computerPlaysNextBestMove() {
        UnbeatableComputer unbeatableComputer = new UnbeatableComputer(X);
        Board board = new Board(3, asList(
                EMPTY, X, O,
                O, X, X,
                EMPTY, EMPTY, O));
        assertThat(unbeatableComputer.findBestMove(board, true, -1), is(7));
    }
}

