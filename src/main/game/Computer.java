package main.game;

import java.util.List;

public class Computer implements Player {

    private final Mark mark;

    public Computer(Mark mark) {
        this.mark = mark;
    }

    public void playMove(Board board) {
        int playerMove = move(board);
        board.updateMove(playerMove, mark);
    }

    public Mark getMark() {
        return mark;
    }

    public int move(Board board) {
        List<Integer> possibleMoves = board.availableMoves();
        return possibleMoves.get(0);
    }
}
