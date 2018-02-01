package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Board {

    private static final String PLAYER_TWO_MARK = "O";
    private final String PLAYER_ONE_MARK = "X";
    public List<String> board;

    public Board(List<String> board) {
        this.board = board;
    }

    public Board() {
        this.board = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
    }

    public List updateMove(String move, String playerType) {
        int convertedMove = Integer.parseInt(String.valueOf(move)) - 1;
        this.board.set(convertedMove, playerType);
        return this.board;
    }

    public List<List<String>> getRows() {
        List<List<String>> rows = new ArrayList<>();
        int boardSize = 3;

        for (int i = 0; i < boardSize * boardSize; i += 3) {
            List<String> row = asList(board.get(i), board.get(i + 1), board.get(i + 2));
            rows.add(row);
        }

        return rows;
    }

    public boolean isMoveAvailable(int move) {
        return moveIsTakenByX(move) && moveIsTakenByO(move);
    }

    public boolean hasAvailableMoves() {
        int count = 0;

        for (String space : this.board) {
            if (space.equals(PLAYER_ONE_MARK) || space.equals(PLAYER_TWO_MARK)) {
                count += 1;
            }
        }
        return count != 9;
    }

    public boolean horizontalWin(String playerMark) {
        List<List<String>> rows = getRows();

        for (List<String> row : rows) {
            if (isWin(playerMark, row)) {
                return true;
            }
        }

        return false;
    }

    public List<List<String>> getColumns() {
        List<List<String>> rows = new ArrayList<>();
        int boardSize = 3;

        for (int i = 0; i < boardSize; i++) {
            List<String> row = asList(board.get(i), board.get(i + 3), board.get(i + 6));
            rows.add(row);
        }
        return rows;
    }

    public boolean columnWin(String playerMark) {
        List<List<String>> columns = getColumns();

        for (List<String> column : columns) {
            if (isWin(playerMark, column)) {
                return true;
            }
        }
        return false;
    }

    public List<List<String>> getDiagonals() {
        List<List<String>> diagonals = new ArrayList<>();
        List<String> diagonalOne = asList(board.get(0), board.get(4), board.get(8));
        List<String> diagonalTwo = asList(board.get(2), board.get(4), board.get(6));
        diagonals.add(diagonalOne);
        diagonals.add(diagonalTwo);
        return diagonals;
    }

    public boolean diagonalWin(String playerMark) {
        List<List<String>> diagonals = getDiagonals();

        for (List<String> diagonal : diagonals) {
            if (isWin(playerMark, diagonal)) {
                return true;
            }
        }

        return false;
    }

    public boolean playerHasWon(String playerMark) {
        return horizontalWin(playerMark) || columnWin(playerMark) || diagonalWin(playerMark);
    }

    public boolean gameIsOver() {
        return playerHasWon(PLAYER_ONE_MARK) || playerHasWon(PLAYER_TWO_MARK) || !hasAvailableMoves();
    }

    private boolean moveIsTakenByO(int move) {
        return !this.board.get(move - 1).equals(PLAYER_TWO_MARK);
    }

    private boolean moveIsTakenByX(int move) {
        return !this.board.get(move - 1).equals(PLAYER_ONE_MARK);
    }

    private boolean isWin(String playerMark, List<String> row) {
        int markCounter = 0;

        for (String mark : row) {
            if (mark.equals(playerMark)) {
                markCounter++;
            }
        }
        if (markCounter == 3) {
            return true;
        }
        return false;
    }

    public boolean gameIsTied() {
        return neitherPlayerHasWon();
    }

    private boolean neitherPlayerHasWon() {
        return !playerHasWon(PLAYER_ONE_MARK) && !playerHasWon(PLAYER_TWO_MARK);
    }

    public String findWinner() {
        if (gameIsTied()) {
            return "Tie";
        } else if (playerHasWon("X")) {
            return PLAYER_ONE_MARK;
        } else {
            return PLAYER_TWO_MARK;
        }
    }
}
