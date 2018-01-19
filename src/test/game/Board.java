package test.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    public ArrayList createGrid() {
        Integer[] boardSpaces = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> threeByThree = new ArrayList<>(9);
        threeByThree.addAll(Arrays.asList(boardSpaces));
        return threeByThree;
    }
}
