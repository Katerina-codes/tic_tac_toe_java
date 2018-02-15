package main.game;

import java.util.List;

import static java.util.Arrays.asList;

public class Line {
    private List<Marks> spaces;

    public Line(Marks spaceOne, Marks spaceTwo, Marks spaceThree) {
        spaces = asList(spaceOne, spaceTwo, spaceThree);
    }

    public boolean hasWinner(Marks mark) {
        int markCounter = 0;

        for (Marks space : spaces) {
            if (space != null && space.equals(mark)) {
                markCounter++;
            }
        }
        return markCounter == spaces.size();
    }

    @Override
    public boolean equals(Object line) {
        Line otherLine = (Line) line;
        return this.spaces.equals(otherLine.spaces);
    }

}
