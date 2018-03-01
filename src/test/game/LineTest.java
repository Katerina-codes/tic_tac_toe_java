package test.game;

import main.game.Line;
import org.junit.Test;

import static java.util.Arrays.asList;
import static main.game.Mark.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LineTest {

    @Test
    public void aLineHasAWinner() {
        Line line = new Line(asList(X, X, X));

        assertThat(line.hasWinner(X), is(true));
    }

    @Test
    public void aLineWithFourElementsCanBeCreated() {
        Line line = new Line(asList(X, X, X));

        assertThat(line.hasWinner(X), is(true));
    }
}
