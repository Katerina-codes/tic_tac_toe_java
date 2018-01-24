package test.game;

import main.game.Board;
import main.game.Game;
import org.junit.Test;

import static java.util.Arrays.asList;
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
    }

}