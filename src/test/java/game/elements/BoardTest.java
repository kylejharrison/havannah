package game.elements;

import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BoardTest {

    @Test
    public void testHexCollectionContainsCorrectNumberOfHexes() throws Exception {
        for (int i = 2; i < 100; i++) {
            Board testBoard = new Board(i);
            Set<HexImpl> hexList = testBoard.getAllHexes();
            assertEquals(hexList.size(), (int)((3 * (Math.pow(i,2))) - (3 * i) +1));
        }
    }

    @Test
    public void testHexCoordinatesAreNotOutOfRange() throws Exception{
        for (int boardSize = 2; boardSize < 100; boardSize++) {
            Board testBoard = new Board(boardSize);
            Set<HexImpl> hexList = testBoard.getAllHexes();
            for (HexImpl hex : hexList) {
                int xAxis = hex.getXAxis();
                int yAxis = hex.getYAxis();
                assertTrue(Math.abs(xAxis) < boardSize, "all x cannot be greater than boardSize");
                if (xAxis >= 0) {
                    if (yAxis >= 0) {
                        assertTrue(yAxis < boardSize - xAxis, "Positive Y cannot be bigger than boardSize - X");
                    } else {
                        assertTrue(yAxis > -boardSize, "Negative Ys cannot be smaller than boardSize");
                    }
                } else {
                    if (yAxis >= 0) {
                        assertTrue(yAxis < boardSize, "Positive Ys cannot be bigger than boardSize when X is negative");
                    } else {
                        assertTrue(yAxis > xAxis - boardSize, "Negative Ys cannot be smaller than X - boardSize when X is negative");
                    }
                }
            }
        }
    }
}