package test.game;

import main.game.Board;
import main.game.Game;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test
    public void runsTheGame() {
        FakeCommandLineUI inputOutput = new FakeCommandLineUI();
        Board board = new Board();
        Game game = new Game(inputOutput, board);

        game.runGame();

        assertTrue(inputOutput.displayBoardWasCalled());
        assertTrue(inputOutput.askForMoveWasCalled());
        assertTrue(inputOutput.getPlayerMoveWasCalled());
    }
}
