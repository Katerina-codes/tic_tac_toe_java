package test.game;

import main.game.Board;
import main.game.Mark;
import main.game.Result;
import main.game.UI;

import java.util.List;

public class FakeCommandLineUI implements UI {

    private boolean askForMoveWasCalled = false;
    private boolean getPlayerMoveWasCalled = false;
    private boolean displayBoardWasCalled = false;
    private boolean announceWinnerWasCalled = false;
    private boolean getGameModeWasCalled = false;
    private boolean askForGameModeWasCalled = false;

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
    public void askForMove(Mark playerMark) {
        this.askForMoveWasCalled = true;
    }

    @Override
    public String getValidMove(Board board) {
        this.getPlayerMoveWasCalled = true;
        List<Integer> moves = board.availableMoves();
        return moves.get(1).toString();
    }

    public boolean getGameModeWasCalled() {
        return getGameModeWasCalled;
    }

    @Override
    public void displayBoard(List<Mark> rows) {
        this.displayBoardWasCalled = true;
    }

    @Override
    public void announceWinner(Result winner) {
        this.announceWinnerWasCalled = true;
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
}
