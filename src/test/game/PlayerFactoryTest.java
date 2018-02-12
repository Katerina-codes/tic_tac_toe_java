package test.game;

import main.game.*;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class PlayerFactoryTest {

    @Test
    public void createsTwoHumanPlayers() {
        UI ui = new FakeCommandLineUI();
        PlayerFactory playerTypes = new PlayerFactory(ui);

        List<Player> players = playerTypes.getPlayerTypes("1");

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void createsOneHumanAndOneComputerPlayer() {
        UI ui = new FakeCommandLineUI();
        PlayerFactory playerTypes = new PlayerFactory(ui);

        List<Player> players = playerTypes.getPlayerTypes("2");

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof Computer);
    }

    @Test
    public void createsComputerPlayerAsPlayerOne() {
        UI ui = new FakeCommandLineUI();
        PlayerFactory playerTypes = new PlayerFactory(ui);

        List<Player> players = playerTypes.getPlayerTypes("3");

        assertTrue(players.get(0) instanceof Computer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }
}
