package test.game;

import main.game.Board;
import main.game.CommandLineUI;
import main.game.Game;
import main.game.UI;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void runsTheGame() {
        FakeCommandLineUI inputOutput = new FakeCommandLineUI();
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Game game = new Game(inputOutput, board);

        game.runGame();

        assertTrue(inputOutput.displayBoardWasCalled());
        assertTrue(inputOutput.askForMoveWasCalled());
        assertTrue(inputOutput.getPlayerMoveWasCalled());
        assertTrue(inputOutput.announceWinnerWasCalled());
    }

    @Test
    public void playsTheGame() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n4\n2\n5\n3".getBytes());
        UI inputOutput = new CommandLineUI(new PrintStream(output), input);
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        Game game = new Game(inputOutput, board);

        game.runGame();

        assertEquals(board.grid, asList("X", "X", "X", "O", "O", "6", "7", "8", "9"));
        assertThat(output.toString(), containsString("X won!"));
    }

}