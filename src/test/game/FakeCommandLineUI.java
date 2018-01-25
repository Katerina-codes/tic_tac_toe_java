package test.game;

import main.game.Board;
import main.game.UI;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class FakeCommandLineUI implements UI {

    public boolean askForMoveWasCalled = false;
    public boolean getPlayerMoveWasCalled = false;
    private boolean displayBoardWasCalled = false;
    private boolean announceWinnerWasCalled = false;

    @Override
    public void askForMove() {
        this.askForMoveWasCalled = true;
    }

    @Override
    public String getPlayerMove(Board board, List<String> grid) {
        this.getPlayerMoveWasCalled = true;
        List moves = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        int rnd = new Random().nextInt(moves.size());
        return (String) moves.get(rnd);
    }

    @Override
    public void displayBoard(List<List<String>> rows) {
        this.displayBoardWasCalled = true;
    }

    @Override
    public void announceWinner(String winner) {
        this.announceWinnerWasCalled = true;
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
