package main.game;

import java.util.List;

public class UnbeatableComputer implements Player {
    private Mark mark;

    public UnbeatableComputer(Mark mark) {
        this.mark = mark;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public Board playMove(Board board) {
        int move = findBestMove(board, false, -1);
        board.updateMove(move, mark);
        return board;
    }

    public int findBestMove(Board board, Boolean maximisingPlayer, int playerMove) {
        Mark playerMark = mark == Mark.O ? Mark.X : Mark.O;
        if (board.gameIsOver()) {
            return playerMove;
        } else if (maximisingPlayer) {
            int bestValue = -10;

            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, mark);
                int currentScore = findBestMove(board, false, move);
                bestValue = bestValue > currentScore ? bestValue : currentScore;
                board = board.updateMove(move, Mark.EMPTY);
            }
            return bestValue;
        } else {
            int bestValue = +10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, playerMark);
                int currentScore = findBestMove(board, true, move);
                bestValue = bestValue < currentScore ? bestValue : currentScore;
                board = board.updateMove(move, Mark.EMPTY);
            }
            return bestValue;
        }
    }
}

