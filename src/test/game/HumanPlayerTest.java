package test.game;

import main.game.Board;
import main.game.CommandLineUI;
import main.game.HumanPlayer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static main.game.Marks.X;
import static org.junit.Assert.*;

public class HumanPlayerTest {

    @Test
    public void canPlayAMoveOnTheBoard() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI ui = new CommandLineUI(new PrintStream(output), input);
        Board board = new Board();
        HumanPlayer player = new HumanPlayer(ui, X);

        Integer move = player.getMove(board);
        board.updateMove(move, X);

        assertEquals(X, board.valueAt(0));
    }
}
