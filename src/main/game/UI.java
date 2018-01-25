package main.game;

import java.util.List;

public interface UI {
    void askForMove();

    String getValidMove(Board board);

    void displayBoard(List<List<String>> rows);

    void announceWinner(String winner);
}
