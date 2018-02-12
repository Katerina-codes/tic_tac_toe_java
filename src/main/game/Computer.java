package main.game;

import java.util.List;

public class Computer implements Player {

    private final String mark;

    public Computer(String mark) {
        this.mark = mark;
    }

    public void playMove(Board board) {
        String playerMove = move(board);
        board.updateMove(playerMove, mark);
    }

    public String move(Board board) {
        List<String> possibleMoves = board.availableMoves();
        return possibleMoves.get(0);
    }
}
