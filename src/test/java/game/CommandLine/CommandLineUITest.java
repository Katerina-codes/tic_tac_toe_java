package game.CommandLine;

import game.Board;
import game.CommandLine.CommandLineUI;
import game.Mark;
import game.Result;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static game.CommandLine.CommandLineUI.*;
import static game.Mark.EMPTY;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CommandLineUITest {

    private ByteArrayOutputStream output;
    private CommandLineUI inputOutput;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
        inputOutput = new CommandLineUI(new PrintStream(output), input);
    }

    @Test
    public void asksUserToChooseBoardSize() {
        inputOutput.askForBoardSize();

        assertTrue(output.toString().contains("Please enter " + HUMAN_VS_HUMAN + " for a 3x3 grid\n" +
                "Please enter " + HUMAN_VS_COMPUTER + " for a 4x4 grid:\n"));
    }

    @Test
    public void asksUserForGameMode() {
        inputOutput.askForGameMode();

        assertTrue(output.toString().contains("\n\nEnter " + HUMAN_VS_HUMAN + " for Human vs Human\n" +
                "Enter " + HUMAN_VS_COMPUTER + " for Human vs Computer\n" +
                "Enter " + COMPUTER_VS_HUMAN + " for Computer vs Human\n" +
                "Enter " + COMPUTER_VS_COMPUTER + " for Computer vs Computer\n" +
                "Enter " + HUMAN_VS_UNBEATABLE_PLAYER + " for Human vs Unbeatable Computer\n" +
                "Enter " + UNBEATABLE_PLAYER_VS_HUMAN + " for Unbeatable Computer vs Human\n" +
                "Enter " + UNBEATABLE_PLAYER_VS_UNBEATABLE_PLAYER + " for Unbeatable Computer vs Unbeatable Computer\n"));
    }

    @Test
    public void getsGameModeChoiceFromPlayer() {
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        assertEquals("1", inputOutput.getUserChoice());
    }

    @Test
    public void threeByThreeBoardIsDisplayed() {
        Board board = new Board(3);

        inputOutput.displayBoard(board.grid, 3);

        assertThat(output.toString(), is(
                "\n --- --- ---\n" +
                "| 1 | 2 | 3 |\n" +
                " --- --- ---\n" +
                "| 4 | 5 | 6 |\n" +
                " --- --- ---\n" +
                "| 7 | 8 | 9 |\n" +
                " --- --- ---\n\n"));
    }

    @Test
    public void fourByFourBoardIsDisplayed() {
        Board board = new Board(4);

        inputOutput.displayBoard(board.grid, 4);

        assertThat(output.toString(), is(
                "\n --- --- --- ---\n" +
                "| 1 | 2 | 3 | 4 |\n" +
                " --- --- --- ---\n" +
                "| 5 | 6 | 7 | 8 |\n" +
                " --- --- --- ---\n" +
                "| 9 | 10| 11| 12|\n" +
                " --- --- --- ---\n" +
                "| 13| 14| 15| 16|\n" +
                " --- --- --- ---\n\n"));
    }

    @Test
    public void askPlayerForMove() {
        inputOutput.askForMove(Mark.X, asList(EMPTY, EMPTY));

        assertTrue(output.toString().contains("Player X place your mark! Pick a move from 1 - 2:"));
    }

    @Test
    public void getPlayerMove() {
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        Board board = new Board(3);

        assertEquals("1", inputOutput.getMove(board));
    }

    @Test
    public void playerIsPromptedForAMoveUntilItIsUnique() {
        InputStream input = new ByteArrayInputStream("1\n2".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        Board board = new Board(3, asList(Mark.X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));

        assertEquals("2", inputOutput.getMove(board));
        assertTrue(output.toString().contains("This move is taken. Place another one:"));
    }

    @Test
    public void displaysXHasWon() {
        inputOutput.announceWinner(Result.PLAYER_ONE_WIN);

        assertThat(output.toString(), containsString("X won!"));
    }

    @Test
    public void canHandleATie() {
        inputOutput.announceWinner(Result.TIE);

        assertThat(output.toString(), containsString("It's a tie!"));
    }

    @Test
    public void asksIfUserWantsToPlayAgain() {
        InputStream input = new ByteArrayInputStream("2".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);
        inputOutput.replay();

        assertTrue(output.toString().contains("\nWould you like to play again?\n" +
                "Enter 1 for yes\n" +
                "Enter 2 for no."));
    }

    @Test
    public void getUserChoiceForPlayingAgain() {
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        assertThat(inputOutput.replay(), is(true));
    }
}