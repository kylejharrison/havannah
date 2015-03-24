package game;

import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;
import org.testng.annotations.Test;

import java.util.*;

import static game.GameHelpers.*;
import static junit.framework.Assert.*;

public class GameHelpersTest {

    @Test
    public void testGetNeighboursReturnsExpectedHexesSimple() throws Exception{
        HexImpl testHex = new HexImpl(0,0);
        testHex.setHexValue(HexValue.BLUE);
        Set<Hex> neighbourHexes = new HashSet<>();
        addHexToSet(neighbourHexes, 0, -1, HexValue.BLUE);
        addHexToSet(neighbourHexes, 1, -1, HexValue.BLUE);
        addHexToSet(neighbourHexes, 1, 0, HexValue.BLUE);
        addHexToSet(neighbourHexes, 0, 1, HexValue.BLUE);
        addHexToSet(neighbourHexes, -1, 1, HexValue.BLUE);
        addHexToSet(neighbourHexes, -1, 0, HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.addAll(neighbourHexes);
        currentState.add(testHex);
        Map<Hex, Hex> hexMap = getHashMapFromSet(currentState);
        assertEquals(neighbourHexes, getNeighboursOfSameValue(hexMap, testHex));
    }

    @Test
    public void testGetNeighboursReturnsExpectedHexesWithGotchas() throws Exception{
        HexImpl testHex = new HexImpl(0,0);
        testHex.setHexValue(HexValue.BLUE);
        Set<Hex> neighbourHexes = new HashSet<>();
        addHexToSet(neighbourHexes, 0, -1, HexValue.BLUE);
        addHexToSet(neighbourHexes, -1, 1, HexValue.BLUE);
        addHexToSet(neighbourHexes, -1, 0, HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.addAll(neighbourHexes);
        currentState.add(testHex);
        addHexToSet(currentState, 1, -1, HexValue.RED);
        addHexToSet(currentState, 1, 0, HexValue.EMPTY);
        addHexToSet(currentState, 0, 1, HexValue.RED);
        Map<Hex, Hex> hexMap = getHashMapFromSet(currentState);
        assertEquals(neighbourHexes, getNeighboursOfSameValue(hexMap, testHex));
    }

    @Test
    public void testGetNeighboursReturnsExpectedHexesWhenOnSide() throws Exception{
        HexImpl testHex = new HexImpl(-1,0);
        testHex.setHexValue(HexValue.BLUE);
        Set<Hex> neighbourHexes = new HashSet<>();
        addHexToSet(neighbourHexes, 0, -1, HexValue.BLUE);
        addHexToSet(neighbourHexes, -1, 1, HexValue.BLUE);
        addHexToSet(neighbourHexes, 0, 0, HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.addAll(neighbourHexes);
        currentState.add(testHex);
        addHexToSet(currentState, 1, -1, HexValue.RED);
        addHexToSet(currentState, 1, 0, HexValue.EMPTY);
        addHexToSet(currentState, 0, 1, HexValue.RED);
        Map<Hex, Hex> hexMap = getHashMapFromSet(currentState);
        assertEquals(neighbourHexes, getNeighboursOfSameValue(hexMap, testHex));
    }

    @Test
    public void testGetNeighboursReturnsEmptyWhenNone() throws Exception{
        HexImpl testHex = new HexImpl(0,0);
        testHex.setHexValue(HexValue.BLUE);
        Set<Hex> neighbourHexes = new HashSet<>();
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHex);
        addHexToSet(currentState, 0, -1, HexValue.RED);
        addHexToSet(currentState, -1, 1, HexValue.RED);
        addHexToSet(currentState, -1, 0, HexValue.EMPTY);
        addHexToSet(currentState, 1, -1, HexValue.RED);
        addHexToSet(currentState, 1, 0, HexValue.EMPTY);
        addHexToSet(currentState, 0, 1, HexValue.RED);
        Map<Hex, Hex> hexMap = getHashMapFromSet(currentState);
        assertEquals(neighbourHexes, getNeighboursOfSameValue(hexMap, testHex));
    }

    @Test
    public void testHasPathToReturnsTrueWithSimplePath() throws Exception{
        HexImpl testHexFrom = new HexImpl(0,0);
        testHexFrom.setHexValue(HexValue.BLUE);
        HexImpl testHexTo = new HexImpl(0,2);
        testHexTo.setHexValue(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHexFrom);
        currentState.add(testHexTo);
        addHexToSet(currentState, 0, 1, HexValue.BLUE);
        assertTrue(hasPathTo(currentState, testHexFrom, testHexTo));
    }

    @Test
    public void testHasPathToReturnsTrueWithMoreComplicatedPath() throws Exception{
        HexImpl testHexFrom = new HexImpl(1,0);
        testHexFrom.setHexValue(HexValue.BLUE);
        HexImpl testHexTo = new HexImpl(0,-1);
        testHexTo.setHexValue(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHexFrom);
        currentState.add(testHexTo);
        addHexToSet(currentState, 0, 1, HexValue.BLUE);
        addHexToSet(currentState,-1,1,HexValue.BLUE);
        addHexToSet(currentState,-1,0,HexValue.BLUE);
        addHexToSet(currentState,0,0,HexValue.RED);
        addHexToSet(currentState,1,-1,HexValue.EMPTY);
        assertTrue(hasPathTo(currentState, testHexFrom, testHexTo));
    }

    @Test
    public void testHasPathToReturnsFalseWhenNoPath() throws Exception{
        HexImpl testHexFrom = new HexImpl(1,0);
        testHexFrom.setHexValue(HexValue.BLUE);
        HexImpl testHexTo = new HexImpl(0,-1);
        testHexTo.setHexValue(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHexFrom);
        currentState.add(testHexTo);
        addHexToSet(currentState, 0, 1, HexValue.BLUE);
        addHexToSet(currentState,-1,1,HexValue.BLUE);
        addHexToSet(currentState,-1,0,HexValue.RED);
        addHexToSet(currentState,0,0,HexValue.RED);
        addHexToSet(currentState, 1, -1, HexValue.EMPTY);
        assertFalse(hasPathTo(currentState, testHexFrom, testHexTo));
    }

    @Test
    public void testShortestPathReturnsShortestPathWithTwoOptions() throws Exception{
        HexImpl testHexFrom = new HexImpl(0,0);
        testHexFrom.setHexValue(HexValue.BLUE);
        HexImpl testHexTo = new HexImpl(0,-2);
        testHexTo.setHexValue(HexValue.BLUE);
        HexImpl onPath = new HexImpl(0,-1);
        onPath.setHexValue(HexValue.EMPTY);
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHexFrom);
        currentState.add(testHexTo);
        currentState.add(onPath);
        addHexToSet(currentState,1,-1,HexValue.EMPTY);
        addHexToSet(currentState,1,-2,HexValue.EMPTY);
        addHexToSet(currentState,1,0,HexValue.BLACK);
        ArrayList<Hex> shortestPath = new ArrayList<>();
        shortestPath.add(testHexFrom);
        shortestPath.add(onPath);
        shortestPath.add(testHexTo);
        assertEquals(shortestPath, findShortestPath(currentState, testHexFrom, testHexTo, HexValue.BLUE).get());
    }

    @Test
    public void testShortestPathReturnsShortestPathWhenPathOfSameValueExists() throws Exception{
        HexImpl testHexFrom = new HexImpl(0,0);
        testHexFrom.setHexValue(HexValue.BLUE);
        HexImpl testHexTo = new HexImpl(0,-3);
        testHexTo.setHexValue(HexValue.BLUE);
        HexImpl onPath = new HexImpl(1,-1);
        onPath.setHexValue(HexValue.EMPTY);
        HexImpl onPath2 = new HexImpl(2,-2);
        onPath2.setHexValue(HexValue.BLUE);
        HexImpl onPath3 = new HexImpl(2,-3);
        onPath3.setHexValue(HexValue.BLUE);
        HexImpl onPath4 = new HexImpl(1,-3);
        onPath4.setHexValue(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHexFrom);
        currentState.add(testHexTo);
        currentState.add(onPath);
        currentState.add(onPath2);
        currentState.add(onPath3);
        currentState.add(onPath4);
        addHexToSet(currentState,0,-1,HexValue.EMPTY);
        addHexToSet(currentState,0,-2,HexValue.EMPTY);
        addHexToSet(currentState,1,-2,HexValue.EMPTY);
        ArrayList<Hex> shortestPath = new ArrayList<>();
        shortestPath.add(testHexFrom);
        shortestPath.add(onPath);
        shortestPath.add(onPath2);
        shortestPath.add(onPath3);
        shortestPath.add(onPath4);
        shortestPath.add(testHexTo);
        assertEquals(shortestPath, findShortestPath(currentState, testHexFrom, testHexTo, HexValue.BLUE).get());
    }

    @Test
    public void testShortestPathReturnsShortestPathWhenPathIsBehindStarting() throws Exception{
        HexImpl testHexFrom = new HexImpl(0,0);
        testHexFrom.setHexValue(HexValue.BLUE);
        HexImpl testHexTo = new HexImpl(0,-3);
        testHexTo.setHexValue(HexValue.BLUE);
        HexImpl onPath = new HexImpl(0,1);
        onPath.setHexValue(HexValue.EMPTY);
        HexImpl onPath2 = new HexImpl(0,2);
        onPath2.setHexValue(HexValue.BLUE);
        HexImpl onPath3 = new HexImpl(1,2);
        onPath3.setHexValue(HexValue.BLUE);
        HexImpl onPath4 = new HexImpl(2,1);
        onPath4.setHexValue(HexValue.BLUE);
        HexImpl onPath5 = new HexImpl(3,0);
        onPath5.setHexValue(HexValue.BLUE);
        HexImpl onPath6 = new HexImpl(3,-1);
        onPath6.setHexValue(HexValue.BLUE);
        HexImpl onPath7 = new HexImpl(3,-2);
        onPath7.setHexValue(HexValue.BLUE);
        HexImpl onPath8 = new HexImpl(3,-3);
        onPath8.setHexValue(HexValue.BLUE);
        HexImpl onPath9 = new HexImpl(2,-3);
        onPath9.setHexValue(HexValue.BLUE);
        HexImpl onPath10 = new HexImpl(1,-3);
        onPath10.setHexValue(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHexFrom);
        currentState.add(testHexTo);
        currentState.add(onPath);
        currentState.add(onPath2);
        currentState.add(onPath3);
        currentState.add(onPath4);
        currentState.add(onPath5);
        currentState.add(onPath6);
        currentState.add(onPath7);
        currentState.add(onPath8);
        currentState.add(onPath9);
        currentState.add(onPath10);
        addHexToSet(currentState, 0, -1, HexValue.EMPTY);
        addHexToSet(currentState,0,-2,HexValue.EMPTY);
        addHexToSet(currentState,1,1,HexValue.EMPTY);
        addHexToSet(currentState,1,0,HexValue.EMPTY);
        addHexToSet(currentState,1,-1,HexValue.EMPTY);
        addHexToSet(currentState,1,-2,HexValue.EMPTY);
        addHexToSet(currentState,2,0,HexValue.EMPTY);
        addHexToSet(currentState,2,-1,HexValue.EMPTY);
        addHexToSet(currentState,2,-2,HexValue.EMPTY);
        ArrayList<Hex> shortestPath = new ArrayList<>();
        shortestPath.add(testHexFrom);
        shortestPath.add(onPath);
        shortestPath.add(onPath2);
        shortestPath.add(onPath3);
        shortestPath.add(onPath4);
        shortestPath.add(onPath5);
        shortestPath.add(onPath6);
        shortestPath.add(onPath7);
        shortestPath.add(onPath8);
        shortestPath.add(onPath9);
        shortestPath.add(onPath10);
        shortestPath.add(testHexTo);
        assertEquals(shortestPath, findShortestPath(currentState, testHexFrom, testHexTo, HexValue.BLUE).get());
    }

    @Test
    public void testHexDistanceReturnsGreaterValueForHexesFurtherApart() throws Exception{
        Hex testFrom = new HexImpl(0,0);
        Hex farApart = new HexImpl(0,4);
        Hex closer = new HexImpl(0,-2);
        Hex closeDiagonal = new HexImpl(3,0);
        Hex furtherDiagonal = new HexImpl(-5,0);
        ArrayList<Hex> orderByDistanceDescending = new ArrayList<>();
        orderByDistanceDescending.add(furtherDiagonal);
        orderByDistanceDescending.add(farApart);
        orderByDistanceDescending.add(closeDiagonal);
        orderByDistanceDescending.add(closer);
        TreeMap<Double, Hex> distances = new TreeMap<>();
        distances.put(hexDistance(testFrom,farApart),farApart);
        distances.put(hexDistance(testFrom,closer), closer);
        distances.put(hexDistance(testFrom,closeDiagonal), closeDiagonal);
        distances.put(hexDistance(testFrom,furtherDiagonal), furtherDiagonal);
        ArrayList<Hex> calculatedOrderByDistance = new ArrayList<>();
        while (!distances.isEmpty()){
            calculatedOrderByDistance.add(distances.lastEntry().getValue());
            distances.remove(distances.lastKey());
        }
        assertEquals(orderByDistanceDescending,calculatedOrderByDistance);
    }
    @Test
    public void testShortestPathReturnsNothingWhenNoPath() throws Exception{
        HexImpl testHexFrom = new HexImpl(1,0);
        testHexFrom.setHexValue(HexValue.BLUE);
        HexImpl testHexTo = new HexImpl(0,-1);
        testHexTo.setHexValue(HexValue.BLUE);
        Set<Hex> currentState = new HashSet<>();
        currentState.add(testHexFrom);
        currentState.add(testHexTo);
        addHexToSet(currentState, 0, 1, HexValue.RED);
        addHexToSet(currentState,1,1,HexValue.RED);
        addHexToSet(currentState,-1,0,HexValue.RED);
        addHexToSet(currentState,0,0,HexValue.RED);
        assertFalse(findShortestPath(currentState, testHexFrom, testHexTo, HexValue.BLUE ).isPresent());
    }

    @Test
    public void testAllHexesConnectedToHexReturnsAllHexesWhenAllSameColour() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            Set<HexImpl> gameBoard = new HexGenerator(boardSize).generateHexes();
            Set<Hex> currentState = new HashSet<>();
            for(HexImpl hex: gameBoard){
                hex.setHexValue(HexValue.BLUE);
                currentState.add(hex);
            }
            HexImpl move = new HexImpl(0,0);
            move.setHexValue(HexValue.BLUE);
            assertEquals(currentState,allHexesConnectedToHex(currentState,move));
        }
    }

    @Test
    public void testGetUnconnected() throws Exception{
        Set<HexImpl> gameBoard = new HexGenerator(3).generateHexes();
        Set<Hex> currentState = new HashSet<>();
        Set<Set<Hex>> unconnected = new HashSet<>();
        Set<Hex> loop = new HashSet<>();
        addHexToSet(loop,-1,0,HexValue.BLUE);
        addHexToSet(loop,0,-1,HexValue.BLUE);
        addHexToSet(loop,1,-1,HexValue.BLUE);
        addHexToSet(loop,1,0,HexValue.BLUE);
        addHexToSet(loop,0,1,HexValue.BLUE);
        addHexToSet(loop,-1,1,HexValue.BLUE);
        for(HexImpl hex: gameBoard){
            if(loop.contains(hex)){
                hex.setHexValue(HexValue.BLUE);
            }
            currentState.add(hex);
        }
        HexImpl testHex = new HexImpl(-1,0);
        testHex.setHexValue(HexValue.BLUE);
        //unconnected = outer ring + 1 in middle
        Set<Hex> outerRing = createOuterRing();
        Set<Hex> middle = new HashSet<>();
        addHexToSet(middle,0,0,HexValue.EMPTY);
        unconnected.add(outerRing);
        unconnected.add(middle);
        assertEquals(unconnected,GameHelpers.getAllUnconnectedHexes(currentState,testHex));
    }
    private Set<Hex> createOuterRing(){
        Set<Hex> outerRing = new HashSet<>();
        addHexToSet(outerRing,0,-2,HexValue.EMPTY);
        addHexToSet(outerRing,1,-2,HexValue.EMPTY);
        addHexToSet(outerRing,2,-2,HexValue.EMPTY);
        addHexToSet(outerRing,2,-1,HexValue.EMPTY);
        addHexToSet(outerRing,2,0,HexValue.EMPTY);
        addHexToSet(outerRing,1,1,HexValue.EMPTY);
        addHexToSet(outerRing,0,2,HexValue.EMPTY);
        addHexToSet(outerRing,-1,2,HexValue.EMPTY);
        addHexToSet(outerRing,-2,2,HexValue.EMPTY);
        addHexToSet(outerRing,-2,1,HexValue.EMPTY);
        addHexToSet(outerRing,-2,0,HexValue.EMPTY);
        addHexToSet(outerRing,-1,-1,HexValue.EMPTY);
        return outerRing;
    }
}
