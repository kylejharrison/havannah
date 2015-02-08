package game;

import game.elements.Corner;
import game.elements.Hex;
import game.elements.HexImpl;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
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
    @Test
    public void testHexCornersAreCreatedCorrectly() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            HexGenerator hexGenerator = new HexGenerator(boardSize);
            Set<HexImpl> allHexes = hexGenerator.generateHexes();
            Map<HexImpl, HexImpl> hexMap = new HashMap<HexImpl, HexImpl>();
            for (HexImpl hex: allHexes){
                hexMap.put(hex,hex);
            }
            HexImpl top = new HexImpl(0, 1 - boardSize);
            allHexes.remove(top);
            HexImpl topRight = new HexImpl(boardSize - 1, 1 - boardSize);
            allHexes.remove(topRight);
            HexImpl bottomRight = new HexImpl(boardSize - 1, 0);
            allHexes.remove(bottomRight);
            HexImpl bottom = new HexImpl(0, boardSize - 1);
            allHexes.remove(bottom);
            HexImpl bottomLeft = new HexImpl(1 - boardSize, boardSize - 1);
            allHexes.remove(bottomLeft);
            HexImpl topLeft = new HexImpl(1 - boardSize, 0);
            allHexes.remove(topLeft);
            assertEquals(hexMap.get(top).getCorner(), Corner.TOP);
            assertEquals(hexMap.get(topRight).getCorner(), Corner.TOPRIGHT);
            assertEquals(hexMap.get(bottomRight).getCorner(), Corner.BOTTOMRIGHT);
            assertEquals(hexMap.get(bottom).getCorner(), Corner.BOTTOM);
            assertEquals(hexMap.get(bottomLeft).getCorner(), Corner.BOTTOMLEFT);
            assertEquals(hexMap.get(topLeft).getCorner(), Corner.TOPLEFT);
            for (HexImpl hex: allHexes){
                assertEquals(hex.getCorner(),Corner.NOTACORNER);
            }
        }
    }
}