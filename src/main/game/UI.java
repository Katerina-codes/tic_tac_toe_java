package main.game;

import java.util.List;

public interface UI {
    void askForGameMode();

    String getGameMode();

    void askForMove(Marks playerMark);

    String getValidMove(Board board);

    void displayBoard(List<Marks> rows);

    void announceWinner(Result winner);
}
