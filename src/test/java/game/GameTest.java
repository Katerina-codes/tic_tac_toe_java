package game;

import game.*;
import game.CommandLine.CommandLineUI;
import org.junit.Test;
import game.CommandLine.FakeCommandLineUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static game.Mark.EMPTY;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void runsTheGame() {
        FakeCommandLineUI inputOutput = new FakeCommandLineUI();
        Board board = new Board(3);
        Game game = new Game(inputOutput, board);

        game.run();

        assertTrue(inputOutput.askForGameModeWasCalled());
        assertTrue(inputOutput.getGameModeWasCalled());
        assertTrue(inputOutput.displayBoardWasCalled());
        assertTrue(inputOutput.askForMoveWasCalled());
        assertTrue(inputOutput.getPlayerMoveWasCalled());
        assertTrue(inputOutput.announceWinnerWasCalled());
        assertTrue(inputOutput.replayWasCalled());
    }

    @Test
    public void playsTheGame() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\n4\n2\n5\n3\n2".getBytes());
        UI inputOutput = new CommandLineUI(new PrintStream(output), input);
        Board board = new Board(3);
        Game game = new Game(inputOutput, board);

        game.run();

        assertEquals(board.grid, asList(Mark.X, Mark.X, Mark.X, Mark.O, Mark.O, EMPTY, EMPTY, EMPTY, EMPTY));
        assertThat(output.toString(), containsString("X won!"));
    }

    @Test
    public void playerCanPlayAgain() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream("1\n1\n4\n2\n5\n3\n1\n1\n1\n1\n2\n3\n5\n4\n8\n2".getBytes());
        UI inputOutput = new CommandLineUI(new PrintStream(output), input);
        Board board = new Board(3);
        Game game = new Game(inputOutput, board);

        game.run();

        assertThat(output.toString(), containsString("X won!"));
        assertThat(output.toString(), containsString("O won!"));
    }
}