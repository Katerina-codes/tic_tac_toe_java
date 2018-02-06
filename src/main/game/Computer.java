package main.game;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class Computer {
    public int playMove(Board board) {
        List<Integer> possibleMoves = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Random random = new Random();
        return random.nextInt(possibleMoves.size());
    }
}
