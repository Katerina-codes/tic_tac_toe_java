package main.game;

import java.util.List;

public interface UI {
    void askForGameMode();

    String getGameMode();

    void askForMove();

    String getValidMove(Board board);

    void displayBoard(List<List<String>> rows);

    void announceWinner(Result winner);
}
