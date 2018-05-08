package game.core;

import game.CommandLine.CommandLineUI;
import game.CommandLine.FakeCommandLineUI;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static game.core.Mark.EMPTY;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void runsTheGame() {
        List moves = asList(1, 4, 2, 5, 3);
        List userChoices = asList(false);
        FakeCommandLineUI inputOutput = new FakeCommandLineUI(moves, userChoices);
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
        List moves = asList(1, 4, 2, 5, 3);
        List userChoices = asList(false);
        UI inputOutput = new FakeCommandLineUI(moves, userChoices);
        Board board = new Board(3);
        Game game = new Game(inputOutput, board);

        game.run();

        assertEquals(asList(Mark.X, Mark.X, Mark.X, Mark.O, Mark.O, EMPTY, EMPTY, EMPTY, EMPTY), board.grid);
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