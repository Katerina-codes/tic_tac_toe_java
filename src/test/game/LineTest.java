package test.game;

import main.game.Line;
import org.junit.Test;

import static main.game.Marks.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LineTest {

    @Test
    public void aLineHasAWinner() {
        Line line = new Line(X, X, X);

        assertThat(line.hasWinner(X), is(true));
    }

}
