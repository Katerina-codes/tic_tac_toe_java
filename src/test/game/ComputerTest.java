package test.game;

import main.game.Board;
import main.game.Computer;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class ComputerTest {

    @Test
    public void playsARandomMove() {
        Computer computer = new Computer();
        Board board = new Board(asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<String> possibleMoves = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

        String move = computer.move(board);

        assertTrue(possibleMoves.contains(move));
    }


}
