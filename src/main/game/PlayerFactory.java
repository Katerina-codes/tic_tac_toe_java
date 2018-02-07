package main.game;

import java.util.Arrays;
import java.util.List;

import static main.game.PlayerTypes.PLAYER_ONE_MARK;
import static main.game.PlayerTypes.PLAYER_TWO_MARK;

public class PlayerFactory {

    private final UI ui;
    private final String playerOne;
    private final String playerTwo;

    public PlayerFactory(UI ui) {
        this.ui = ui;
        this.playerOne = PLAYER_ONE_MARK.getPlayer();
        this.playerTwo = PLAYER_TWO_MARK.getPlayer();
    }

    public List<Player> getPlayerTypes(String gameMode) {
        Player[] humanVsHuman = { new Player(ui, playerOne), new Player(ui, playerTwo)};
        return Arrays.asList(humanVsHuman);
    }
}
