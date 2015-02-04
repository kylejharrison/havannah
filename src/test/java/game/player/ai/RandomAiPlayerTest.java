package game.player.ai;

import game.elements.HexImpl;
import game.elements.HexValue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static game.player.TestHelpers.getHexCollection;

public class RandomAiPlayerTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testCreationThrowsExceptionOnBadColour() throws Exception {
        new RandomAiPlayer(HexValue.EMPTY);
    }

    @Test
    public void testWithOneHex() throws Exception {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(HexValue.BLUE);
        HexImpl onlyEmptyOne = new HexImpl(0, 0);
        HexImpl move = randomAiPlayer.move(Collections.singleton(onlyEmptyOne));
        Assert.assertEquals(move, onlyEmptyOne);
    }

    @Test
    public void testWithManyHexOnlyOne() throws Exception {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(HexValue.BLUE);
        Set<HexImpl> currentState = getHexCollection(3,4,0);
        HexImpl onlyEmpty = new HexImpl(-1,-1);
        currentState.add(onlyEmpty);
        HexImpl move = randomAiPlayer.move(currentState);
        Assert.assertEquals(move,onlyEmpty);
    }

    @Test
    public void testWithManyHexMultipleChoices() throws Exception{
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(HexValue.BLUE);
        Set<HexImpl> currentState = getHexCollection(10,10,0);
        Set<HexImpl> multipleChoice = new HashSet<HexImpl>();
        for (int i = -1; i > -10 ; i--) {
            HexImpl empty = new HexImpl(0,i);
            multipleChoice.add(empty);
        }
        currentState.addAll(multipleChoice);
        HexImpl move = randomAiPlayer.move(currentState);
        Assert.assertTrue(multipleChoice.contains(move));
    }
}