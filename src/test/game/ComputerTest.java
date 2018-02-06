package test.game;

import main.game.Board;
import main.game.Computer;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class ComputerTest {

    @Test
    public void playsARandomMove() {
        Computer computer = new Computer();
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<Integer> possibleMoves = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Random random = new Random();
        int randomMove = random.nextInt(possibleMoves.size());

        computer.playMove(board);

        assertTrue(possibleMoves.contains(randomMove));
    }
}
