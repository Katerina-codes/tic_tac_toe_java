package game.Players;

import game.Board;
import game.Mark;
import game.Players.Player;

import java.util.List;

public class Computer implements Player {

    private final Mark mark;

    public Computer(Mark mark) {
        this.mark = mark;
    }

    public Board playMove(Board board) {
        int playerMove = move(board);
        return board.updateMove(playerMove, mark);
    }

    public Mark getMark() {
        return mark;
    }

    public int move(Board board) {
        List<Integer> possibleMoves = board.availableMoves();
        return possibleMoves.get(0);
    }
}