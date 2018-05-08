package game.core.Players;

import game.CommandLine.CommandLineUI;
import game.CommandLine.FakeCommandLineUI;
import game.core.Board;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static game.core.Mark.X;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    @Test
    public void canPlayAMoveOnTheBoard() {
        List moves = asList(1);
        FakeCommandLineUI ui = new FakeCommandLineUI(moves);
        Board board = new Board(3);
        HumanPlayer player = new HumanPlayer(ui, X);

        Board updatedBoard = player.playMove(board);

        assertEquals(X, updatedBoard.valueAt(0));
    }
}
