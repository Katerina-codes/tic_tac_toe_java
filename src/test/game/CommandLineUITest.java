package test.game;

import main.game.CommandLineUI;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class CommandLineUITest {

    @Test
    public void boardIsDisplayed() {
        List<List<String>> rows = asList(asList("1", "2", "3"), asList("4", "5", "6"), asList("7", "8", "9"));

        CommandLineUI inputOutput = new CommandLineUI();
        assertEquals("1 2 3\n" +
                "4 5 6\n" +
                "7 8 9", inputOutput.displayBoard(rows));
    }
}
