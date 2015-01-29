package player;

import game.elements.Hex;
import game.elements.HexValue;
import game.player.ai.RandomAiPlayer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class RandomAiPlayerTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testCreationThrowsExceptionOnBadColour() throws Exception {
        new RandomAiPlayer(HexValue.EMPTY);
    }

    @Test
    public void testWithOneHex() throws Exception {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(HexValue.BLUE);
        Hex onlyEmptyOne = new Hex();
        Hex move = randomAiPlayer.move(Collections.singletonList(onlyEmptyOne));
        Assert.assertEquals(move, onlyEmptyOne);
    }

    @Test
    public void testWithManyHexOnlyOne() throws Exception {
       //TODO: need to wait for KY to make the Hex work in collections (good lesson for him!)
    }
}