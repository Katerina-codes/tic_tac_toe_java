package test.game;

import main.game.CommandLineUI;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandLineUITest {

    @Test
    public void boardIsDisplayed() {
        List<List<String>> rows = asList(asList("1", "2", "3"), asList("4", "5", "6"), asList("7", "8", "9"));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        inputOutput.displayBoard(rows);

        assertTrue(output.toString().contains("1 2 3\n" +
                "4 5 6\n" +
                "7 8 9"));
    }

    @Test
    public void askPlayerForMove() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        inputOutput.askForMove();

        assertTrue(output.toString().contains("Place your mark! Pick a move from 1 - 9:"));
    }

    @Test
    public void getPlayerMove() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        assertEquals("1", inputOutput.getPlayerMove());
    }
}
