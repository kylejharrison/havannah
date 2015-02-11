package game;

import game.elements.HexValue;
import game.player.HumanPlayer;
import game.player.Player;
import org.testng.annotations.Test;

public class GameTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testNewGameWithTwoPlayersOfSameValueThrowsException() throws Exception{
        Player player1 = new HumanPlayer(HexValue.BLUE);
        Player player2 = new HumanPlayer(HexValue.BLUE);
        new Game(8,player1,player2);
    }

}