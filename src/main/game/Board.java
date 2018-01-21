package main.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Board {

    private List<Integer> board;

    public Board() {
        this.board = createGrid();
    }

    public ArrayList createGrid() {
        Integer[] boardSpaces = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> threeByThree = new ArrayList<>(9);
        threeByThree.addAll(asList(boardSpaces));
        return threeByThree;
    }

    public List updateMove(int move, String playerType) {
        board.set(move, 1);
        return board;
    }

    public List<List<Integer>> getRows() {
        List<List<Integer>> rows = new ArrayList<>();
        int boardSize = 3;

        for (int i = 0; i < boardSize * boardSize; i += 3) {
            List<Integer> row = asList(board.get(i), board.get(i + 1), board.get(i + 2));
            rows.add(row);
        }

        return rows;
    }
}
