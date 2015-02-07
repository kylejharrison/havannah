package game;

import game.elements.Hex;
import game.elements.HexImpl;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HexGeneratorTest {

    @Test
    public void testHexCollectionContainsCorrectNumberOfHexes() throws Exception {
        for (int boardSize = 2; boardSize < 50; boardSize++) {
            HexGenerator hexGenerator = new HexGenerator(boardSize);
            Set<HexImpl> hexList = hexGenerator.generateHexes();
            assertEquals(hexList.size(), (int)((3 * (Math.pow(boardSize,2))) - (3 * boardSize) +1));
        }
    }

    @Test
    public void testHexCoordinatesAreNotOutOfRange() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            HexGenerator hexGenerator = new HexGenerator(boardSize);
            Set<HexImpl> hexList = hexGenerator.generateHexes();
            for (Hex hex : hexList) {
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
                        assertTrue(yAxis < boardSize, String.format("Positive Ys cannot be bigger than boardSize " +
                                "when X is negative. BoardSize = %s. Hex = %s. All Hexes= %s", boardSize, hex.toString(), hexList));
                    } else {
                        assertTrue(yAxis > xAxis - boardSize, "Negative Ys cannot be smaller than X - boardSize when X is negative");
                    }
                }
            }
        }
    }
}