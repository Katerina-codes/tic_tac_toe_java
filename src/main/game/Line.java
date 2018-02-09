package main.game;

import java.util.List;

import static java.util.Arrays.asList;

public class Line {
    private List<String> spaces;

    public Line(String spaceOne, String spaceTwo, String spaceThree) {
        spaces = asList(spaceOne, spaceTwo, spaceThree);
    }

    public boolean hasWinner(String mark) {
        int markCounter = 0;

        for (String space : spaces) {
            if (space.equals(mark)) {
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
