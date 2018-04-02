package main.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UnbeatableComputer implements Player {
    private Mark mark;
    private int bestMove2;

    public UnbeatableComputer(Mark mark) {
        this.mark = mark;
    }

    private List<BestScore> scores = new ArrayList<>();

    @Override
    public Mark getMark() {
        return mark;
    }

    @Override
    public Board playMove(Board board) {
        findBestMove(board, true);
        board.updateMove(bestMove2, mark);
        return board;
    }

    public int findBestMove(Board board, Boolean maximisingPlayer) {
        Mark opponentMark = mark == Mark.O ? Mark.X : Mark.O;

        if (board.gameIsOver()) {
            if (board.playerHasWon(mark)) {
                return 1;
            } else if (board.playerHasWon(opponentMark)) {
                return -1;
            } else if (board.gameIsTied()) {
                return 0;
            }
        }

        if (maximisingPlayer) {
            int bestValue = -10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, mark);
                int currentScore = findBestMove(board, false);
                bestValue = bestValue > currentScore ? bestValue : currentScore;
                board = board.updateMove(move, Mark.EMPTY);
                scores.add(new BestScore(move, bestValue));
            }
            bestMove2 = maxMove(scores).getMove();
            return maxMove(scores).getMoveScore();
        } else {
            int bestValue = +10;
            List<Integer> possibleMoves = board.availableMoves();
            for (int move : possibleMoves) {
                board = board.updateMove(move, opponentMark);
                int currentScore = findBestMove(board, true);
                bestValue = bestValue < currentScore ? bestValue : currentScore;
                board = board.updateMove(move, Mark.EMPTY);
                scores.add(new BestScore(move, bestValue));
            }
            bestMove2 = minMove(scores).getMove();
            return minMove(scores).getMoveScore();
        }
    }

    public BestScore maxMove(List<BestScore> scores) {
        BestScore highestScoringMove = scores.stream()
                .max(Comparator.comparing(BestScore::getMoveScore))
                .orElse(new BestScore(-1, -1));
        return highestScoringMove;
    }

    public BestScore minMove(List<BestScore> scores) {
        BestScore lowestScoringMove = scores.stream()
                .min(Comparator.comparing(BestScore::getMoveScore))
                .orElse(new BestScore(-1, -1));
        return lowestScoringMove;
    }

    public static class BestScore {

        private final int move;
        private final int moveScore;

        public BestScore(int move, int moveScore) {
            this.move = move;
            this.moveScore = moveScore;
        }

        public int getMoveScore() {
            return moveScore;
        }

        public int getMove() {
            return move;
        }
    }
}
