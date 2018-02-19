package main.game;

import java.util.List;

import static java.util.Arrays.asList;

public class Line {
    private List<Mark> spaces;

    public Line(Mark spaceOne, Mark spaceTwo, Mark spaceThree) {
        spaces = asList(spaceOne, spaceTwo, spaceThree);
    }

    public boolean hasWinner(Mark mark) {
        int markCounter = 0;

        for (Mark space : spaces) {
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
