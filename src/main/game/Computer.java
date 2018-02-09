package main.game;

import java.util.List;

import static main.game.PlayerTypes.O_MARK;

public class Computer implements Player {
    public void playMove(Board board) {
        String playerMove = move(board);
        board.updateMove(playerMove, O_MARK.getPlayer());
    }

    public String move(Board board) {
        List<String> possibleMoves = board.availableMoves();
        return possibleMoves.get(0);
    }
}
