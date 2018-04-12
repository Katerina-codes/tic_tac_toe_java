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

    public List<Integer> findBestMove(Board board, int depth, int al, int be, Boolean maximisingPlayer) {
        Mark opponentMark = mark == Mark.O ? Mark.X : Mark.O;
        int bestMove = -50;

        if (depth == 0 || board.gameIsOver()) {
            int gameScore = gameScore(board, opponentMark);
            return asList(bestMove, gameScore);
        }

        if (maximisingPlayer) {
            int v = -10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, mark);
                int possibleMoveValue = findBestMove(board, depth - 1, al, be,false).get(1);

                if (possibleMoveValue > v) {
                    v = possibleMoveValue;
                    bestMove = move;
                }
                board = board.updateMove(move, Mark.EMPTY);

                if (v > al) {
                    al = v;
                }

                if (be <= al) {
                    break;
                }

            }
            return asList(bestMove, v);
        } else {
            int v = +10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, opponentMark);

                int possibleMoveValue = findBestMove(board, depth - 1, al, be,true).get(1);

                if (possibleMoveValue < v) {
                    v = possibleMoveValue;
                    bestMove = move;
                }
                board = board.updateMove(move, Mark.EMPTY);

                if (v < be) {
                    be = v;
                }

                if (be <= al) {
                    break;
                }

            }
            return asList(bestMove, v);
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
