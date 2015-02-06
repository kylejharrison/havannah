package game.player.ai;

import game.elements.Corner;
import game.elements.Edge;
import game.elements.HexImpl;
import game.elements.HexValue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static game.player.TestHelpers.getHexCollection;
import static org.testng.Assert.*;

public class CrowdingAiPlayerTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testCreationThrowsExceptionOnBadColour() throws Exception {
        new CrowdingAiPlayer(HexValue.EMPTY);
    }

    @Test
    public void testLegalOnFirstMove() throws Exception {
        CrowdingAiPlayer crowdingAiPlayer = new CrowdingAiPlayer(HexValue.BLUE);
        HexImpl onlyEmptyOne = new HexImpl(0, 0, Edge.NOTANEDGE, Corner.NOTACORNER);
        HexImpl move = crowdingAiPlayer.move(Collections.singleton(onlyEmptyOne));
        Assert.assertEquals(move, onlyEmptyOne);
    }

    @Test
    public void testMovesNextToOpponent() throws Exception {
        CrowdingAiPlayer crowdingAiPlayer = new CrowdingAiPlayer(HexValue.BLUE);
        Set<HexImpl> currentState = new HashSet<HexImpl>();
        HexImpl expected = new HexImpl(0,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(expected);
        HexImpl opponent = new HexImpl(1,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        opponent.setHexValue(HexValue.RED);
        currentState.add(opponent);
        HexImpl notExpect = new HexImpl(12,12, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(notExpect);
        HexImpl move = crowdingAiPlayer.move(currentState);
        Assert.assertNotEquals(move, notExpect);
        Assert.assertEquals(move,expected);
    }

    @Test
    public void testTwosMovesNextToOpponent() throws Exception {
        CrowdingAiPlayer crowdingAiPlayer = new CrowdingAiPlayer(HexValue.BLUE);
        Set<HexImpl> currentState = new HashSet<HexImpl>();
        HexImpl expected = new HexImpl(0,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(expected);
        HexImpl opponent = new HexImpl(1,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        opponent.setHexValue(HexValue.RED);
        currentState.add(opponent);
        HexImpl secondExpected = new HexImpl(12,12, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(secondExpected);
        HexImpl secondOpponent = new HexImpl(13,12, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(secondOpponent);
        HexImpl firstmove = crowdingAiPlayer.move(currentState);
        Assert.assertEquals(firstmove,expected);
        secondOpponent.setHexValue(HexValue.RED);
        HexImpl secondMove = crowdingAiPlayer.move(currentState);
        Assert.assertEquals(secondMove,secondExpected);
    }
}