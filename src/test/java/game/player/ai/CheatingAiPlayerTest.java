package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static game.player.TestHelpers.getHexCollection;

public class CheatingAiPlayerTest {

    @Test
    public void testMoveWithOneHexWins() throws Exception {
        HexValue cheatingPlayerColour = HexValue.BLUE;
        Hex move = new CheatingAiPlayer(cheatingPlayerColour).move(getHexCollection(0, 0, 1));
        Assert.assertEquals(move.getHexValue(), HexValue.EMPTY);
    }

    @Test
    public void testMoveWithThreeOfEachHexWins() throws Exception {
        HexValue cheatingPlayerColour = HexValue.RED;
        Set<Hex> gameState = getHexCollection(3, 3, 3);
        Hex move = new CheatingAiPlayer(cheatingPlayerColour).move(gameState);
        assertAllButOneIsColour(cheatingPlayerColour, gameState);
        Assert.assertTrue(AbstractPlayer.isValidMove(move));
    }

    @Test
    public void testMoveWithLoadsOfEmptyHexWins() throws Exception {
        HexValue cheatingPlayerColour = HexValue.RED;
        Set<Hex> gameState = getHexCollection(1, 1, 39);
        Hex move = new CheatingAiPlayer(cheatingPlayerColour).move(gameState);
        assertAllButOneIsColour(cheatingPlayerColour, gameState);
        Assert.assertTrue(AbstractPlayer.isValidMove(move));
    }

    private void assertAllButOneIsColour(HexValue cheatingPlayerColour, Set<Hex> gameState) {
        int countColour = 0;
        int countEmpty = 0;
        for (Hex hex : gameState){
            if (hex.getHexValue().equals(cheatingPlayerColour)){
                countColour++;
            }else if (hex.getHexValue().equals(HexValue.EMPTY)){
                countEmpty++;
            }
        }
        Assert.assertEquals(countEmpty, 1);
        Assert.assertEquals(countColour, gameState.size() - 1);
    }

}