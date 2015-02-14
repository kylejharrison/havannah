package game;

import game.elements.Hex;
import game.elements.HexValue;
import game.player.HumanPlayer;
import game.player.Player;
import game.player.ai.CheatingAiAIPlayer;
import game.player.ai.RandomAiAIPlayer;
import org.testng.annotations.Test;

import java.util.Set;

import static junit.framework.Assert.assertTrue;

public class GameTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testNewGameWithTwoPlayersOfSameValueThrowsException() throws Exception{
        Player player1 = new HumanPlayer(HexValue.BLUE);
        Player player2 = new HumanPlayer(HexValue.BLUE);
        new Game(8,player1,player2);
    }

    @Test
    public void testGameHasEqualAmountOfMovesPlusOneAtEnd()throws Exception{
        Player player1 = new RandomAiAIPlayer(HexValue.BLUE);
        Player player2 = new RandomAiAIPlayer(HexValue.RED);
        assertValueCountsAreWithinOne(player1, player2);
    }

    private void assertValueCountsAreWithinOne(Player player1, Player player2) throws InterruptedException {
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            Game game = new Game(boardSize,player1,player2);
            game.startGameLoop();
            Set<Hex> finalHexes = game.getClonedGameHexes();
            int countBlue = countHexValue(finalHexes,HexValue.BLUE);
            int countRed = countHexValue(finalHexes,HexValue.RED);
            int difference = Math.abs(countBlue - countRed);
            assertTrue(difference <= 1);
        }
    }

    @Test
    public void testCheatingPlayerCannotSetAllHexes()throws Exception{
        Player player1 = new CheatingAiAIPlayer(HexValue.BLUE);
        Player player2 = new RandomAiAIPlayer(HexValue.RED);
        try {
            assertValueCountsAreWithinOne(player1, player2);
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