package game.core.Players;

import game.core.Board;
import game.core.Mark;

public interface Player {
    Board playMove(Board board);

    Mark getMark();
}
