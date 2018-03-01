package main.game;

import java.util.List;

public interface UI {
    void askForGameMode();

    String getUserChoice();

    void askForMove(Mark playerMark);

    String getMove(Board board);

    void displayBoard(List<Mark> rows, int size);

    void announceWinner(Result winner);
}
