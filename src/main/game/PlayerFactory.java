package main.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Player> getPlayerTypes(String players) {
        Player[] humanVsHuman = {new HumanPlayer(ui, playerOne), new HumanPlayer(ui, playerTwo)};
        Player[] humanVsComputer = {new HumanPlayer(ui, playerOne), new Computer()};

        Map<String, List<Player>> playerTypes = new HashMap<>();
        playerTypes.put("1", Arrays.asList(humanVsHuman));
        playerTypes.put("2", Arrays.asList(humanVsComputer));

        return playerTypes.get(players);
    }

}
