package game.core.Players;

import game.core.Mark;
import game.core.UI;

import java.util.Arrays;
import java.util.List;

import static game.CommandLine.CommandLineUI.*;
import static game.core.Mark.O;
import static game.core.Mark.X;

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
        Player[] humanVsUnbeatablePlayer = {new HumanPlayer(ui, playerOne), new UnbeatableComputer(playerTwo)};
        Player[] unbeatablePlayerVsHuman = {new UnbeatableComputer(playerOne), new HumanPlayer(ui, playerTwo)};
        Player[] unbeatableVsUnbeatable = {new UnbeatableComputer(playerOne), new UnbeatableComputer(playerTwo)};

        switch (players) {
            case HUMAN_VS_HUMAN:
                return Arrays.asList(humanVsHuman);
            case HUMAN_VS_COMPUTER:
                return Arrays.asList(humanVsComputer);
            case COMPUTER_VS_HUMAN:
                return Arrays.asList(computerVsHuman);
            case COMPUTER_VS_COMPUTER:
                return Arrays.asList(computerVsComputer);
            case HUMAN_VS_UNBEATABLE_PLAYER:
                return Arrays.asList(humanVsUnbeatablePlayer);
            case UNBEATABLE_PLAYER_VS_HUMAN:
                return Arrays.asList(unbeatablePlayerVsHuman);
            case UNBEATABLE_PLAYER_VS_UNBEATABLE_PLAYER:
                return Arrays.asList(unbeatableVsUnbeatable);
            default:
                throw new RuntimeException("Unsupported player type");
        }
    }

}
