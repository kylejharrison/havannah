package game.player.ai;

import game.elements.*;
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
        Hex onlyEmptyOne = new HexImpl(0, 0, Edge.NOTANEDGE, Corner.NOTACORNER);
        Hex move = randomAiPlayer.move(Collections.singleton(onlyEmptyOne));
        Assert.assertEquals(move, onlyEmptyOne);
    }

    @Test
    public void testWithManyHexOnlyOne() throws Exception {
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(HexValue.BLUE);
        Set<Hex> currentState = getHexCollection(3,4,0);
        Hex onlyEmpty = new HexImpl(-1,-1, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(onlyEmpty);
        Hex move = randomAiPlayer.move(currentState);
        Assert.assertEquals(move,onlyEmpty);
    }

    @Test
    public void testWithManyHexMultipleChoices() throws Exception{
        RandomAiPlayer randomAiPlayer = new RandomAiPlayer(HexValue.BLUE);
        Set<Hex> currentState = getHexCollection(10,10,0);
        Set<Hex> multipleChoice = new HashSet<Hex>();
        for (int i = -1; i > -10 ; i--) {
            Hex empty = new HexImpl(0,i, Edge.NOTANEDGE, Corner.NOTACORNER);
            multipleChoice.add(empty);
        }
        currentState.addAll(multipleChoice);
        Hex move = randomAiPlayer.move(currentState);
        Assert.assertTrue(multipleChoice.contains(move));
    }
}