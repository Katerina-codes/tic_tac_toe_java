package main.game;

public interface Player {
    void playMove(Board board);

    Marks getMark();
}
