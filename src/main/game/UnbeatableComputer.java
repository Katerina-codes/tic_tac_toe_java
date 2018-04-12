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
        int move = findBestMove(board, 7, -10, +10, true).get(0);
        return board.updateMove(move, mark);
    }

    public List<Integer> findBestMove(Board board, int depth, int alpha, int beta, Boolean maximisingPlayer) {
        Mark opponentMark = mark == Mark.O ? Mark.X : Mark.O;
        int bestMove = -50;

        if (depth == 0 || board.gameIsOver()) {
            int gameScore = gameScore(board, opponentMark);
            return asList(bestMove, gameScore);
        }

        if (maximisingPlayer) {
            int currentMoveScore = -10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, mark);
                int bestMoveScore = findBestMove(board, depth - 1, alpha, beta,false).get(1);

                if (bestMoveScore > currentMoveScore) {
                    currentMoveScore = bestMoveScore;
                    bestMove = move;
                }
                board = board.updateMove(move, Mark.EMPTY);

                if (currentMoveScore > alpha) {
                    alpha = currentMoveScore;
                }

                if (optimumMoveHasBeenFound(alpha, beta)) break;

            }
            return asList(bestMove, currentMoveScore);
        } else {
            int currentMoveScore = +10;
            List<Integer> bestValue = board.availableMoves();
            for (int move : bestValue) {
                board = board.updateMove(move, opponentMark);

                int bestMoveScore = findBestMove(board, depth - 1, alpha, beta,true).get(1);

                if (bestMoveScore < currentMoveScore) {
                    currentMoveScore = bestMoveScore;
                    bestMove = move;
                }
                board = board.updateMove(move, Mark.EMPTY);

                if (currentMoveScore < beta) {
                    beta = currentMoveScore;
                }

                if (optimumMoveHasBeenFound(alpha, beta)) break;

            }
            return asList(bestMove, currentMoveScore);
        }
    }

    private boolean optimumMoveHasBeenFound(int alpha, int beta) {
        return beta <= alpha;
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
