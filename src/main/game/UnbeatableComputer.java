package main.game;

import java.util.List;

import static java.util.Arrays.asList;

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
        int move = findBestMove(board,true).get(0);
        return board.updateMove(move, mark);
    }

    public List<Integer> findBestMove(Board board, Boolean maximisingPlayer) {
        Mark opponentMark = mark == Mark.O ? Mark.X : Mark.O;
        int bestMove = -50;

        if (board.gameIsOver()) {
            int gameScore = gameScore(board, opponentMark);
            return asList(1, gameScore);
        }

        if (maximisingPlayer) {
            int bestValue = -10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, mark);
                int currentScore = findBestMove(board, false).get(1);

                if (currentScore > bestValue) {
                    bestValue = currentScore;
                    bestMove = move;
                }

                board = board.updateMove(move, Mark.EMPTY);
            }
            return asList(bestMove, bestValue);
        } else {
            int bestValue = +10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, opponentMark);
                int currentScore = findBestMove(board, true).get(1);

                if (currentScore < bestValue) {
                    bestValue = currentScore;
                    bestMove = move;
                }

                board = board.updateMove(move, Mark.EMPTY);
            }
            return asList(bestMove, bestValue);
        }
    }

    private int gameScore(Board board, Mark opponentMark) {
        if (board.playerHasWon(mark)) {
            return 1;
        } else if (board.playerHasWon(opponentMark)) {
            return -1;
        } else {
            return 0;
        }
    }
}
