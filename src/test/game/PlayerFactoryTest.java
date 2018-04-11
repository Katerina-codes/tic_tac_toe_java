package test.game;

import main.game.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static main.game.CommandLineUI.*;

public class PlayerFactoryTest {

    private PlayerFactory playerTypes;

    @Before
    public void setUp() {
        FakeCommandLineUI ui = new FakeCommandLineUI();
        playerTypes = new PlayerFactory(ui);
    }

    @Test
    public void createsTwoHumanPlayers() {
        List<Player> players = playerTypes.getPlayerTypes(OPTION_ONE);

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void createsOneHumanAndOneComputerPlayer() {
        List<Player> players = playerTypes.getPlayerTypes(OPTION_TWO);

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof Computer);
    }

    @Test
    public void createsComputerPlayerAsPlayerOne() {
        List<Player> players = playerTypes.getPlayerTypes(OPTION_THREE);

        assertTrue(players.get(0) instanceof Computer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void createsTwoComputerPlayers() {
        List<Player> players = playerTypes.getPlayerTypes(OPTION_FOUR);

        assertTrue(players.get(0) instanceof Computer);
        assertTrue(players.get(1) instanceof Computer);
    }

    @Test
    public void createsOneHumanAndOneUnbeatable() {
        List<Player> players = playerTypes.getPlayerTypes(OPTION_FIVE);

        assertTrue(players.get(0) instanceof HumanPlayer);
        assertTrue(players.get(1) instanceof UnbeatableComputer);
    }

    @Test
    public void unbeatableComputerAsPlayerOne() {
        List<Player> players = playerTypes.getPlayerTypes(OPTION_SIX);

        assertTrue(players.get(0) instanceof UnbeatableComputer);
        assertTrue(players.get(1) instanceof HumanPlayer);
    }

    @Test
    public void createsTwoUnbeatableComputerPlayers() {
        List<Player> players = playerTypes.getPlayerTypes(OPTION_SEVEN);

        assertTrue(players.get(0) instanceof UnbeatableComputer);
        assertTrue(players.get(1) instanceof UnbeatableComputer);
    }
}
