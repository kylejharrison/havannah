package game.player.ai;

import game.elements.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CrowdingAiPlayerTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testCreationThrowsExceptionOnBadColour() throws Exception {
        new CrowdingAiPlayer(HexValue.EMPTY);
    }

    @Test
    public void testLegalOnFirstMove() throws Exception {
        CrowdingAiPlayer crowdingAiPlayer = new CrowdingAiPlayer(HexValue.BLUE);
        Hex onlyEmptyOne = new HexImpl(0, 0, Edge.NOTANEDGE, Corner.NOTACORNER);
        Hex move = crowdingAiPlayer.move(Collections.singleton(onlyEmptyOne));
        Assert.assertEquals(move, onlyEmptyOne);
    }

    @Test
    public void testMovesNextToOpponent() throws Exception {
        CrowdingAiPlayer crowdingAiPlayer = new CrowdingAiPlayer(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<Hex>();
        Hex expected = new HexImpl(0,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(expected);
        HexImpl opponent = new HexImpl(1,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        opponent.setHexValue(HexValue.RED);
        currentState.add(opponent);
        Hex notExpect = new HexImpl(12,12, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(notExpect);
        Hex move = crowdingAiPlayer.move(currentState);
        Assert.assertNotEquals(move, notExpect);
        Assert.assertEquals(move,expected);
    }

    @Test
    public void testTwosMovesNextToOpponent() throws Exception {
        CrowdingAiPlayer crowdingAiPlayer = new CrowdingAiPlayer(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<Hex>();
        Hex expected = new HexImpl(0,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(expected);
        HexImpl opponent = new HexImpl(1,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        opponent.setHexValue(HexValue.RED);
        currentState.add(opponent);
        Hex secondExpected = new HexImpl(12,12, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(secondExpected);
        HexImpl secondOpponent = new HexImpl(13,12, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(secondOpponent);
        Hex firstMove = crowdingAiPlayer.move(currentState);
        Assert.assertEquals(firstMove,expected);
        secondOpponent.setHexValue(HexValue.RED);
        Hex secondMove = crowdingAiPlayer.move(currentState);
        Assert.assertEquals(secondMove,secondExpected);
    }
}