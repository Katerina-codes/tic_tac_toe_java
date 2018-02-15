package test.game;

import main.game.Board;
import main.game.Computer;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static main.game.Marks.O;
import static org.junit.Assert.assertTrue;

public class ComputerTest {

    @Test
    public void playsARandomMove() {
        Computer computer = new Computer(O);
        Board board = new Board();
        List<String> possibleMoves = asList("1", "2", "3", "4", "5", "6", "7", "8", "9");

        int move = computer.move(board);

        assertTrue(possibleMoves.contains(move));
    }


}
