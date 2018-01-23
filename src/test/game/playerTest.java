package test.game;

import main.game.Board;
import main.game.CommandLineUI;
import main.game.Player;
import main.game.UI;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class playerTest {

    @Test
    public void playMove() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<String> grid = board.board;

        Player player = new Player(inputOutput);

        assertEquals("1", player.playMove(board, grid));
    }
}
