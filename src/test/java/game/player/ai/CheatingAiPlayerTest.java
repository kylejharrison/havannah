package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CheatingAiPlayerTest {

    @Test
    public void testMoveWithOneHexWins() throws Exception {
        HexValue cheatingPlayerColour = HexValue.BLUE;
        Hex move = new CheatingAiPlayer(cheatingPlayerColour).move(getHexCollection(0,0,1));
        Assert.assertEquals(move.getHexValue(), HexValue.EMPTY);
    }

    @Test
    public void testMoveWithThreeOfEachHexWins() throws Exception {
        HexValue cheatingPlayerColour = HexValue.RED;
        List<Hex> gameState = getHexCollection(3, 3, 3);
        Hex move = new CheatingAiPlayer(cheatingPlayerColour).move(gameState);
        assertAllButOneIsColour(cheatingPlayerColour, gameState);
        Assert.assertTrue(AbstractPlayer.isValidMove(move));
    }

    @Test
    public void testMoveWithLoadsOfEmptyHexWins() throws Exception {
        HexValue cheatingPlayerColour = HexValue.RED;
        List<Hex> gameState = getHexCollection(1, 1, 39);
        Hex move = new CheatingAiPlayer(cheatingPlayerColour).move(gameState);
        assertAllButOneIsColour(cheatingPlayerColour, gameState);
        Assert.assertTrue(AbstractPlayer.isValidMove(move));
    }

    private void assertAllButOneIsColour(HexValue cheatingPlayerColour, List<Hex> gameState) {
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

    private static List<Hex> getHexCollection(int numberOfBlue, int numberOfRed, int numberOfEmpty) {
        List<Hex> hexes = getHexCollectionForValue(HexValue.BLUE, numberOfBlue);
        hexes.addAll(getHexCollectionForValue(HexValue.RED, numberOfRed));
        hexes.addAll(getHexCollectionForValue(HexValue.EMPTY, numberOfEmpty));
        return hexes;
    }

    private static List<Hex> getHexCollectionForValue(HexValue value, int number) {
        ArrayList<Hex> hexes = new ArrayList<Hex>();
        for (int i = 0; i < number; i++) {
            Hex e = new Hex(0, i);
            e.setHexValue(value);
            hexes.add(e);
        }
        return hexes;
    }
}