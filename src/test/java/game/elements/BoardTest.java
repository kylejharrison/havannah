package game.elements;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testHexListContainsCorrectNumberOfHexes() throws Exception {
        Board testBoard = new Board(4);
        ArrayList<Hex> hexList = testBoard.getHexList();
        assertEquals(hexList.size(), 37);
    }

    @Test
    public void testHexListContainsAllUniqueValues() throws Exception{
        Board testBoard = new Board(50);
        ArrayList<Hex> hexList = testBoard.getHexList();
        Set<Hex> hexSet = new HashSet<Hex>();
        for (Hex hex: hexList){
            hexSet.add(hex);
        }
        assertEquals(hexSet.size(), hexList.size());
    }

    @Test
    public void testHexCoordinatesAreNotOutOfRange() throws Exception{
        int boardSize = 5;
        Board testBoard = new Board(boardSize);
        ArrayList<Hex> hexList = testBoard.getHexList();
        for (Hex hex: hexList){
            assertTrue(Math.abs(hex.getXAxis()) < boardSize && Math.abs(hex.getYAxis()) < boardSize);
        }
    }
}