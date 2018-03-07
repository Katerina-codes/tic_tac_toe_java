package main.game;

import java.util.Arrays;
import java.util.List;

import static main.game.CommandLineUI.*;
import static main.game.Mark.O;
import static main.game.Mark.X;

public class PlayerFactory {

    private final UI ui;
    private final Mark playerOne;
    private final Mark playerTwo;

    public PlayerFactory(UI ui) {
        this.ui = ui;
        this.playerOne = X;
        this.playerTwo = O;
    }

    public List<Player> getPlayerTypes(String players) {
        Player[] humanVsHuman = {new HumanPlayer(ui, playerOne), new HumanPlayer(ui, playerTwo)};
        Player[] humanVsComputer = {new HumanPlayer(ui, playerOne), new Computer(playerTwo)};
        Player[] computerVsHuman = {new Computer(playerOne), new HumanPlayer(ui, playerTwo)};
        Player[] computerVsComputer = {new Computer(playerOne), new Computer(playerTwo)};

        switch (players) {
            case OPTION_ONE:
                return Arrays.asList(humanVsHuman);
            case OPTION_TWO:
                return Arrays.asList(humanVsComputer);
            case OPTION_THREE:
                return Arrays.asList(computerVsHuman);
            case OPTION_FOUR:
                return Arrays.asList(computerVsComputer);
            default:
                throw new RuntimeException("Unsupported player type");
        }
    }

}
