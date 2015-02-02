package game.elements;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class BoardTest {

    @Test
    public void testHexListContainsCorrectNumberOfHexes() throws Exception {
        Board testBoard = new Board(4);
        ArrayList<Hex> hexList = testBoard.getHexList();
        assertEquals(hexList.size(), 37);
    }
}