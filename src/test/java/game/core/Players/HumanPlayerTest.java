package game.core.Players;

import game.core.Board;
import game.CommandLine.CommandLineUI;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static game.core.Mark.X;
import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    @Test
    public void canPlayAMoveOnTheBoard() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI ui = new CommandLineUI(new PrintStream(output), input);
        Board board = new Board(3);
        HumanPlayer player = new HumanPlayer(ui, X);

        Board updatedBoard = player.playMove(board);

        assertEquals(X, updatedBoard.valueAt(0));
    }
}
