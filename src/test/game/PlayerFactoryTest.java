package test.game;

import main.game.HumanPlayer;
import main.game.PlayerFactory;
import main.game.UI;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class PlayerFactoryTest {

    @Test
    public void createsTwoHumanPlayers() {
        UI ui = new FakeCommandLineUI();
        PlayerFactory playerTypes = new PlayerFactory(ui);

        List<HumanPlayer> players = playerTypes.getPlayerTypes("1");

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }
}
