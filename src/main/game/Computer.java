package main.game;

import java.util.List;

import static main.game.PlayerTypes.PLAYER_TWO_MARK;

public class Computer implements Player {
    public void playMove(Board board) {
        String playerMove = move(board);
        board.updateMove(playerMove, PLAYER_TWO_MARK.getPlayer());
    }

    public String move(Board board) {
        List<String> possibleMoves = board.getAvailableMoves();
        return possibleMoves.get(0);
    }
}
