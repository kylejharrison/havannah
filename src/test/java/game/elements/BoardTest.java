package game.elements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BoardTest {

    @Test
    public void testHexListContainsCorrectNumberOfHexes() throws Exception {
        Board testBoard = new Board(8);
        ArrayList<Hex> hexList = testBoard.getHexList();
        assertEquals(hexList.size(), 37);
    }
}