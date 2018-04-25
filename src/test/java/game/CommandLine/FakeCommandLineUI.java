package game.CommandLine;

import game.core.Board;
import game.core.Mark;
import game.core.Result;
import game.core.UI;

import java.util.List;

import static game.CommandLine.CommandLineUI.THREE_BY_THREE;

public class FakeCommandLineUI implements UI {

    private boolean askForMoveWasCalled = false;
    private boolean getPlayerMoveWasCalled = false;
    private boolean displayBoardWasCalled = false;
    private boolean announceWinnerWasCalled = false;
    private boolean getGameModeWasCalled = false;
    private boolean askForGameModeWasCalled = false;
    private boolean replayWasCalled = false;

    @Override
    public void askForGameMode() {
        this.askForGameModeWasCalled = true;
    }

    @Override
    public String getUserChoice() {
        this.getGameModeWasCalled = true;
        return "1";
    }

    @Override
    public void askForMove(Mark playerMark, List<Mark> boardSize) {
        this.askForMoveWasCalled = true;
    }

    @Override
    public String getMove(Board board) {
        this.getPlayerMoveWasCalled = true;
        List<Integer> moves = board.availableMoves();
        return moves.get(1).toString();
    }

    @Override
    public int getBoardSize() {
        return THREE_BY_THREE;
    }

    @Override
    public void askForBoardSize() {
    }

    @Override
    public void displayBoard(List<Mark> rows, int size) {
        this.displayBoardWasCalled = true;
    }

    @Override
    public void announceWinner(Result winner) {
        this.announceWinnerWasCalled = true;
    }

    public boolean getGameModeWasCalled() {
        return getGameModeWasCalled;
    }

    @Override
    public boolean replay() {
        this.replayWasCalled = true;
        return false;
    }

    public boolean askForGameModeWasCalled() {
        return askForGameModeWasCalled;
    }

    public boolean askForMoveWasCalled() {
        return askForMoveWasCalled;
    }

    public boolean getPlayerMoveWasCalled() {
        return getPlayerMoveWasCalled;
    }

    public boolean displayBoardWasCalled() {
        return displayBoardWasCalled;
    }

    public boolean announceWinnerWasCalled() {
        return announceWinnerWasCalled;
    }

    public boolean replayWasCalled() {
        return replayWasCalled;
    }
}
