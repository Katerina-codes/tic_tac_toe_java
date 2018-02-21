package test.game;

import main.game.Board;
import main.game.CommandLineUI;
import main.game.Mark;
import main.game.Result;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static main.game.Mark.EMPTY;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class CommandLineUITest {

    private ByteArrayOutputStream output;
    private ByteArrayInputStream input;
    private CommandLineUI inputOutput;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        input = new ByteArrayInputStream("".getBytes());
        inputOutput = new CommandLineUI(new PrintStream(output), input);
    }

    @Test
    public void asksUserToChooseBoardSize() {
       inputOutput.askForBoardSize();

       assertTrue(output.toString().contains("Please enter '1' for a 3x3 grid\n" +
               "Please enter '2' for a 4x4 grid:\n"));
    }

    @Test
    public void asksUserForGameMode() {
        inputOutput.askForGameMode();

        assertTrue(output.toString().contains("Enter '1' for Human vs Human\n" +
                "Enter '2' for Human vs Computer\n" +
                "Enter '3' for Computer vs Human"));
    }

    @Test
    public void getsGameModeChoiceFromPlayer() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        assertEquals("1", inputOutput.getUserChoice());
    }

    @Test
    public void threeByThreeBoardIsDisplayed() {
        Board board = new Board();

        inputOutput.displayBoard(board.grid);

        assertTrue(output.toString().contains(" 1 2 3\n" +
                " 4 5 6\n" +
                " 7 8 9\n"));
    }

    @Test
    public void fourByFourBoardIsDisplayed() {
        Board board = new Board(4);

        inputOutput.displayBoard(board.grid);

        assertTrue(output.toString().contains(" 1 2 3 4\n" +
                " 5 6 7 8\n" +
                " 9 10 11 12\n" +
                " 13 14 15 16\n"));
    }

    @Test
    public void askPlayerForMove() {
        inputOutput.askForMove(Mark.X);

        assertTrue(output.toString().contains("Player X place your mark! Pick a move from 1 - 9:"));
    }

    @Test
    public void getPlayerMove() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("1".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        Board board = new Board();

        assertEquals("1", inputOutput.getValidMove(board));
    }

    @Test
    public void playerIsPromptedForAMoveUntilItIsUnique() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input = new ByteArrayInputStream("1\n2".getBytes());
        CommandLineUI inputOutput = new CommandLineUI(new PrintStream(output), input);

        Board board = new Board(3, asList(Mark.X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));

        assertEquals("2", inputOutput.getValidMove(board));
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
}