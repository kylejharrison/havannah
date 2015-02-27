package game;

import game.elements.Hex;
import game.elements.HexValue;
import game.player.HumanPlayer;
import game.player.Player;
import game.player.ai.CheatingAiAIPlayer;
import game.player.ai.RandomAiAIPlayer;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

import static junit.framework.Assert.assertTrue;

public class GameTest {

    private static final Logger LOG = Logger.getLogger(GameTest.class.getName());

    @Test(expectedExceptions = RuntimeException.class)
    public void testNewGameWithTwoPlayersOfSameValueThrowsException() throws Exception{
        Player player1 = new HumanPlayer(HexValue.BLUE);
        Player player2 = new HumanPlayer(HexValue.BLUE);
        new Game(8,player1,player2);
    }
    @Test(expectedExceptions = RuntimeException.class)
    public void testNewGameWithThreePlayersWithSameValuesThrowsException() throws Exception{
        ArrayList<Player> allPlayers = new ArrayList<>();
        Player player1 = new HumanPlayer(HexValue.BLUE);
        Player player2 = new HumanPlayer(HexValue.BLUE);
        Player player3 = new HumanPlayer(HexValue.RED);
        allPlayers.add(player1);
        allPlayers.add(player2);
        allPlayers.add(player3);
        new Game(8,allPlayers);
    }

    @Test
    public void testGameHasEqualAmountOfMovesPlusOneAtEnd()throws Exception{
        ArrayList<Player> players = new ArrayList<>();
        players.add(new RandomAiAIPlayer(HexValue.BLUE));
        players.add(new RandomAiAIPlayer(HexValue.RED));
        assertValueCountsAreWithinOne(players);
    }

    @Test
    public void testGameHasEqualAmountOfMovesPlusOneAtEndWith3Players()throws Exception{
        ArrayList<Player> players = new ArrayList<>();
        players.add(new RandomAiAIPlayer(HexValue.BLUE));
        players.add(new RandomAiAIPlayer(HexValue.RED));
        players.add(new RandomAiAIPlayer(HexValue.BLACK));
        assertValueCountsAreWithinOne(players);
    }

    private void assertValueCountsAreWithinOne(ArrayList<Player> players) throws InterruptedException {
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            Game game = new Game(boardSize,players);
            Thread gameLoop = game.getGameLoop();
            gameLoop.start();
            while (gameLoop.isAlive()){
                LOG.info("Loop still running");
                Thread.sleep(100);
            }
            Set<Hex> finalHexes = game.getClonedGameHexes();
            Player player1 = players.get(0);
            int countPlayer1 = countHexValue(finalHexes, player1.getPlayerHexValue());
            for (int i = 1; i < players.size(); i++) {
                int countPlayerI = countHexValue(finalHexes,players.get(i).getPlayerHexValue());
                int difference = Math.abs(countPlayer1 - countPlayerI);
                assertTrue(difference <= 1);
            }
        }
    }

    @Test
    public void testCheatingPlayerCannotSetAllHexes()throws Exception{
        ArrayList<Player> players = new ArrayList<>();
        players.add(new CheatingAiAIPlayer(HexValue.BLUE));
        players.add(new RandomAiAIPlayer(HexValue.RED));
        try {
            assertValueCountsAreWithinOne(players);
        } catch (Exception e){
            assertTrue(e.getClass() == RuntimeException.class);
        }
    }

    private int countHexValue(Set<Hex> hexes, HexValue hexValue){
        int count = 0;
        for(Hex hex: hexes){
            if(hex.getHexValue() == hexValue){
                count++;
            }
        }
        return count;
    }
}