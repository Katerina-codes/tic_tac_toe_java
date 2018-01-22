package main.game;

import java.util.List;

public interface UI {
    void askForMove();
    String getPlayerMove();
    void displayBoard(List<List<String>> rows);
}
