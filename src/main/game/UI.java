package main.game;

import java.util.List;

public interface UI {
    void askForGameMode();

    String getUserChoice();

    void askForMove(Mark playerMark);

    String getValidMove(Board board);

    void displayBoard(List<Mark> rows);

    void announceWinner(Result winner);
}
