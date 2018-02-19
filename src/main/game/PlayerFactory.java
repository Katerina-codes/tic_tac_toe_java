package main.game;

import java.util.Arrays;
import java.util.List;

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

        switch (players) {
            case "1":
                return Arrays.asList(humanVsHuman);
            case "2":
                return Arrays.asList(humanVsComputer);
            case "3":
                return Arrays.asList(computerVsHuman);
            default:
                throw new RuntimeException("Unsupported player type");
        }
    }

}
