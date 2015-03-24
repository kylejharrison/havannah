package game.player.ai;

import game.elements.*;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class EdgeFinderAIPlayerTest {

    @Test
    public void testFirstMoveIsACorner() throws Exception {
        EdgeFinderAIPlayer edgeFinderAIPlayer = new EdgeFinderAIPlayer(HexValue.BLACK);
        Set<Hex> currentState = new HashSet<>();
        Hex expected = new HexImpl(1,0, Edge.NOTANEDGE, Corner.BOTTOM);
        currentState.add(expected);
        Hex notExpected = new HexImpl(0,0,Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(notExpected);
        Hex move = edgeFinderAIPlayer.move(currentState);
        assertEquals(move,expected);
    }

    @Test
    public void testFirstMoveIsACornerWhenMultipleCornersPresent() throws Exception{
        EdgeFinderAIPlayer edgeFinderAIPlayer = new EdgeFinderAIPlayer(HexValue.BLACK);
        Set<Hex> currentState = new HashSet<>();
        Hex expected = new HexImpl(1,0, Edge.NOTANEDGE, Corner.BOTTOM);
        currentState.add(expected);
        Hex notExpected = new HexImpl(0,0,Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(notExpected);
        Hex expected2 = new HexImpl(1,1, Edge.NOTANEDGE, Corner.TOP);
        currentState.add(expected2);
        Hex notExpected2 = new HexImpl(2,3,Edge.RIGHT,Corner.NOTACORNER);
        currentState.add(notExpected2);
        Set<Hex> allExpected = new HashSet<>();
        allExpected.add(expected);
        allExpected.add(expected2);
        Hex move = edgeFinderAIPlayer.move(currentState);
        assertTrue(allExpected.contains(move));
    }

    @Test
    public void testFirstMoveIsValidWhenNoCorners() throws Exception {
        EdgeFinderAIPlayer edgeFinderAIPlayer = new EdgeFinderAIPlayer(HexValue.BLACK);
        Set<Hex> currentState = new HashSet<>();
        Hex expected = new HexImpl(1,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        currentState.add(expected);
        Hex move = edgeFinderAIPlayer.move(currentState);
        assertEquals(move,expected);
    }

    @Test
    public void testFirstMoveIsOppositeCornerWhenOppositionPlaysACorner() throws Exception {
        EdgeFinderAIPlayer edgeFinderAIPlayer = new EdgeFinderAIPlayer(HexValue.BLACK);
        Set<Hex> currentState = new HashSet<>();
        Hex expected = new HexImpl(1,0, Edge.NOTANEDGE, Corner.TOP);
        currentState.add(expected);
        HexImpl opposition = new HexImpl(-1,0,Edge.NOTANEDGE,Corner.BOTTOM);
        opposition.setHexValue(HexValue.BLUE);
        currentState.add(opposition);
        Hex wrongCorner1 = new HexImpl(-1,-1,Edge.NOTANEDGE,Corner.TOPLEFT);
        currentState.add(wrongCorner1);
        Hex wrongCorner2 = new HexImpl(2,2,Edge.NOTANEDGE,Corner.BOTTOMLEFT);
        currentState.add(wrongCorner2);
        Hex move = edgeFinderAIPlayer.move(currentState);
        assertEquals(move,expected);
    }

    @Test
    public void testMovesMakeCornerShapeWhenNotImpeded() throws Exception{
        EdgeFinderAIPlayer edgeFinderAIPlayer = new EdgeFinderAIPlayer(HexValue.BLACK);
        Set<Hex> currentState = new HashSet<>();
        HexImpl previousMove = new HexImpl(-3,0,Edge.NOTANEDGE,Corner.TOPLEFT);
        previousMove.setHexValue(HexValue.BLACK);
        currentState.add(previousMove);
        Hex expectedEdge1 = new HexImpl(-3,1, Edge.LEFT, Corner.NOTACORNER);
        currentState.add(expectedEdge1);
        Hex expectedEdge2 = new HexImpl(-2,-1,Edge.TOPLEFT,Corner.NOTACORNER);
        currentState.add(expectedEdge2);
        Hex expectedPlant = new HexImpl(-1,0,Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(expectedPlant);
        HexImpl wrongCorner = new HexImpl(3,-3,Edge.NOTANEDGE,Corner.TOPRIGHT);
        wrongCorner.setHexValue(HexValue.BLACK);
        currentState.add(wrongCorner);
        HexImpl otherPlayerImpedingWrongCorner = new HexImpl(3,-2,Edge.RIGHT,Corner.NOTACORNER);
        otherPlayerImpedingWrongCorner.setHexValue(HexValue.BLUE);
        currentState.add(otherPlayerImpedingWrongCorner);
        Set<Hex> expectedEdges = new HashSet<>();
        expectedEdges.add(expectedEdge1);
        expectedEdges.add(expectedEdge2);
        Hex theGap1 = new HexImpl(-2, 1, Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(theGap1);
        Hex theGap2 = new HexImpl(-2, 0, Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(theGap2);
        Hex theGap3 = new HexImpl(-1, -1, Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(theGap3);
        //Make moves
        Hex move = edgeFinderAIPlayer.move(currentState);
        assertTrue(expectedEdges.contains(move));
        expectedEdges.remove(move);
        updateCurrentStateWithMove(currentState, move, HexValue.BLACK);
        Hex move2 = edgeFinderAIPlayer.move(currentState);
        assertTrue(expectedEdges.contains(move2));
        updateCurrentStateWithMove(currentState, move2, HexValue.BLACK);
        Hex move3 = edgeFinderAIPlayer.move(currentState);
        assertEquals(move3,expectedPlant);
    }

    @Test
    public void testSwitchesCornerWhenImpeded() throws Exception{
        EdgeFinderAIPlayer edgeFinderAIPlayer = new EdgeFinderAIPlayer(HexValue.BLACK);
        Set<Hex> currentState = new HashSet<>();
        HexImpl previousMove = new HexImpl(-3,0,Edge.NOTANEDGE,Corner.TOPLEFT);
        previousMove.setHexValue(HexValue.BLACK);
        currentState.add(previousMove);
        Hex expectedEdge1 = new HexImpl(-3,1, Edge.LEFT, Corner.NOTACORNER);
        currentState.add(expectedEdge1);
        Hex expectedEdge2 = new HexImpl(-2,-1,Edge.TOPLEFT,Corner.NOTACORNER);
        currentState.add(expectedEdge2);
        Hex expectedPlant = new HexImpl(-1,0,Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(expectedPlant);
        HexImpl wrongCorner = new HexImpl(3,-3,Edge.NOTANEDGE,Corner.TOPRIGHT);
        wrongCorner.setHexValue(HexValue.BLACK);
        currentState.add(wrongCorner);
        HexImpl otherPlayerImpedingWrongCorner = new HexImpl(3,-2,Edge.RIGHT,Corner.NOTACORNER);
        otherPlayerImpedingWrongCorner.setHexValue(HexValue.BLUE);
        currentState.add(otherPlayerImpedingWrongCorner);
        Set<Hex> expectedEdges = new HashSet<>();
        expectedEdges.add(expectedEdge1);
        expectedEdges.add(expectedEdge2);
        Hex theGap1 = new HexImpl(-2, 1, Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(theGap1);
        Hex theGap2 = new HexImpl(-2, 0, Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(theGap2);
        Hex theGap3 = new HexImpl(-1, -1, Edge.NOTANEDGE,Corner.NOTACORNER);
        currentState.add(theGap3);
        Hex cornerToSwitchTo = new HexImpl(0, 3, Edge.NOTANEDGE, Corner.BOTTOM);
        currentState.add(cornerToSwitchTo);
        //Make moves
        Hex move = edgeFinderAIPlayer.move(currentState);
        assertTrue(expectedEdges.contains(move));
        expectedEdges.remove(move);
        updateCurrentStateWithMove(currentState, move, HexValue.BLACK);
        updateCurrentStateWithMove(currentState, theGap1, HexValue.BLUE);
        Hex move2 = edgeFinderAIPlayer.move(currentState);
        assertTrue(expectedEdges.contains(move2));
        updateCurrentStateWithMove(currentState, move2, HexValue.BLACK);
        updateCurrentStateWithMove(currentState, theGap2, HexValue.BLUE);
        Hex move3 = edgeFinderAIPlayer.move(currentState);
        assertEquals(move3,cornerToSwitchTo);
    }


    private void updateCurrentStateWithMove(Set<Hex> currentState,Hex move, HexValue hexValue) {
        HexImpl updatedMove = new HexImpl(move.getXAxis(),move.getYAxis(),move.getEdge(),move.getCorner());
        updatedMove.setHexValue(hexValue);
        currentState.remove(move);
        currentState.add(updatedMove);
    }
}