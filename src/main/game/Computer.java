package main.game;

import java.util.List;

public class Computer {
    public int playMove(Board board) {
        List<Integer> possibleMoves = board.getAvailableMoves();
        return possibleMoves.get(0);
    }
}
