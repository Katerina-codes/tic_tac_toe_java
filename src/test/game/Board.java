package test.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    public final static String player1Move = "X";
    private ArrayList board;

    public Board() {
        this.board = createGrid();
    }

    public ArrayList createGrid() {
        Integer[] boardSpaces = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> threeByThree = new ArrayList<>(9);
        threeByThree.addAll(Arrays.asList(boardSpaces));
        return threeByThree;
    }

    public ArrayList updateMove(int move, String playerType) {
        board.set(move, playerType);
        return board;
    }
}
