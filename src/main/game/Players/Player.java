package main.game.Players;

import main.game.Board;
import main.game.Mark;

public interface Player {
    Board playMove(Board board);

    Mark getMark();
}
