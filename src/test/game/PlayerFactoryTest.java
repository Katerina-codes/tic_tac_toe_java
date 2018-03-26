package test.game;

import main.game.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class PlayerFactoryTest {

    private PlayerFactory playerTypes;

    @Before
    public void setUp() {
        FakeCommandLineUI ui = new FakeCommandLineUI();
        playerTypes = new PlayerFactory(ui);
    }

    @Test
    public void createsTwoHumanPlayers() {
        List<Player> players = playerTypes.getPlayerTypes("1");

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void createsOneHumanAndOneComputerPlayer() {
        List<Player> players = playerTypes.getPlayerTypes("2");

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof Computer);
    }

    @Test
    public void createsComputerPlayerAsPlayerOne() {
        List<Player> players = playerTypes.getPlayerTypes("3");

        assertTrue(players.get(0) instanceof Computer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void createsTwoComputerPlayers() {
        List<Player> players = playerTypes.getPlayerTypes("4");

        assertTrue(players.get(0) instanceof Computer);
        assertTrue(players.get(1) instanceof Computer);
    }

    @Test
    public void createsOneHumanAndOneUnbeatable() {
        List<Player> players = playerTypes.getPlayerTypes("5");

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof UnbeatableComputer);
    }
}
