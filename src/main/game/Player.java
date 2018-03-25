package main.game;

public interface Player {
    Board playMove(Board board);

    Mark getMark();
}
