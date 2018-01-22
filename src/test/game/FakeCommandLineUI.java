package test.game;

import main.game.UI;

import java.util.List;

public class FakeCommandLineUI implements UI {

    public boolean askForMoveWasCalled = false;
    public boolean getPlayerMoveWasCalled = false;
    private boolean displayBoardWasCalled = false;

    @Override
    public void askForMove() {
        this.askForMoveWasCalled = true;
    }

    @Override
    public String getPlayerMove() {
        this.getPlayerMoveWasCalled = true;
        return "1";
    }

    @Override
    public void displayBoard(List<List<String>> rows) {
        this.displayBoardWasCalled = true;
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
}
