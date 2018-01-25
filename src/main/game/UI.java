package main.game;

import java.util.List;

public interface UI {
    void askForMove();

    String getPlayerMove(Board board, List<String> grid);

    void displayBoard(List<List<String>> rows);

    void announceWinner(String winner);
}
