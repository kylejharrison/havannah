package game;

import game.elements.Corner;
import game.elements.Edge;
import game.elements.Hex;
import game.elements.HexImpl;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.*;

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
            Map<HexImpl,HexImpl> hexMap = getHexMap(boardSize);
            assertAllCorners(hexMap, boardSize);
            assertRemainingHexesAreNotACorner(hexMap);
        }
    }
    @Test
    public void testHexEdgesAreCreatedCorrectly() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            Map<HexImpl,HexImpl> hexMap = getHexMap(boardSize);
            assertAllEdges(hexMap,boardSize);
            assertRemainingHexesAreNotAnEdge(hexMap);
        }
    }

    @Test
    public void testCorrectNumberOfCorners() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            HexGenerator hexGenerator = new HexGenerator(boardSize);
            Set<HexImpl> allHexes = hexGenerator.generateHexes();
            int numCorners = 0;
            for (HexImpl hex: allHexes){
                if (hex.getCorner().isACorner()) numCorners++;
            }
            assertEquals(numCorners,6);
        }
    }

    @Test
    public void testAllCornersAreUnique() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            HexGenerator hexGenerator = new HexGenerator(boardSize);
            Set<HexImpl> allHexes = hexGenerator.generateHexes();
            Set<Corner> allCorners = new HashSet<Corner>();
            for (HexImpl hex: allHexes){
                if (hex.getCorner().isACorner()){
                    assertFalse(allCorners.contains(hex.getCorner()));
                    allCorners.add(hex.getCorner());
                }
            }
        }
    }

    @Test
    public void testCorrectNumberOfEdges() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            HexGenerator hexGenerator = new HexGenerator(boardSize);
            Set<HexImpl> allHexes = hexGenerator.generateHexes();
            int numEdges = 0;
            for (HexImpl hex: allHexes){
                if (hex.getEdge().isAnEdge()) numEdges++;
            }
            assertEquals(numEdges, 6 * (boardSize - 2));
        }
    }

    @Test
    public void testNoHexesAreBothACornerAndAnEdge() throws Exception{
        for (int boardSize = 2; boardSize < 20; boardSize++) {
            HexGenerator hexGenerator = new HexGenerator(boardSize);
            Set<HexImpl> allHexes = hexGenerator.generateHexes();
            for (HexImpl hex: allHexes){
                assertFalse(hex.getCorner().isACorner() && hex.getEdge().isAnEdge());
            }
        }
    }

    private void assertAllEdges(Map<HexImpl,HexImpl> hexMap, int boardSize){
        assertTopRightEdge(hexMap, boardSize);
        assertRightEdge(hexMap,boardSize);
        assertBottomRightEdge(hexMap,boardSize);
        assertBottomLeftEdge(hexMap,boardSize);
        assertLeftEdge(hexMap,boardSize);
        assertTopLeftEdge(hexMap,boardSize);
    }
    private void assertRemainingHexesAreNotAnEdge(Map<HexImpl,HexImpl> hexMap){
        for (Map.Entry<HexImpl,HexImpl> hex: hexMap.entrySet()) {
            assertEquals(hex.getValue().getEdge(), Edge.NOTANEDGE,String.format("AllHexes: %s, Hex: %s",hexMap,hex.getValue().toString()));
        }
    }
    private void assertTopRightEdge(Map<HexImpl,HexImpl> hexMap, int boardSize){
        Set<HexImpl> topRightHexes = getTopRightEdges(boardSize);
        for (HexImpl hex: topRightHexes){
            assertEquals(hexMap.get(hex).getEdge(), Edge.TOPRIGHT);
            hexMap.remove(hex);
        }
    }
    private Set<HexImpl> getTopRightEdges(int boardSize){
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        for (int x = 1; x < boardSize -1; x++) {
            hexes.add(new HexImpl(x,1 - boardSize));
        }
        return hexes;
    }
    private void assertRightEdge(Map<HexImpl,HexImpl> hexMap, int boardSize){
        Set<HexImpl> RightHexes = getRightEdges(boardSize);
        for (HexImpl hex: RightHexes){
            assertEquals(hexMap.get(hex).getEdge(), Edge.RIGHT);
            hexMap.remove(hex);
        }
    }
    private Set<HexImpl> getRightEdges(int boardSize){
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        for (int y = 2 - boardSize; y < 0; y++) {
            hexes.add(new HexImpl(boardSize - 1,y));
        }
        return hexes;
    }
    private void assertBottomRightEdge(Map<HexImpl, HexImpl> hexMap, int boardSize){
        Set<HexImpl> BottomRightHexes = getBottomRightEdges(boardSize);
        for (HexImpl hex: BottomRightHexes){
            assertEquals(hexMap.get(hex).getEdge(), Edge.BOTTOMRIGHT);
            hexMap.remove(hex);
        }
    }
    private Set<HexImpl> getBottomRightEdges(int boardSize){
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        int y = boardSize - 2;
        for (int x = 1; x < boardSize -1; x++) {
            hexes.add(new HexImpl(x,y));
            y--;
        }
        return hexes;
    }

    private void assertBottomLeftEdge(Map<HexImpl, HexImpl> hexMap, int boardSize){
        Set<HexImpl> BottomLeftHexes = getBottomLeftEdges(boardSize);
        for (HexImpl hex: BottomLeftHexes){
            assertEquals(hexMap.get(hex).getEdge(), Edge.BOTTOMLEFT);
            hexMap.remove(hex);
        }
    }
    private Set<HexImpl> getBottomLeftEdges(int boardSize){
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        for (int x = 2 - boardSize; x < 0; x++) {
            hexes.add(new HexImpl(x,boardSize -1));
        }
        return hexes;
    }

    private void assertLeftEdge(Map<HexImpl, HexImpl> hexMap, int boardSize){
        Set<HexImpl> LeftHexes = getLeftEdges(boardSize);
        for (HexImpl hex: LeftHexes){
            assertEquals(hexMap.get(hex).getEdge(), Edge.LEFT);
            hexMap.remove(hex);
        }
    }
    private Set<HexImpl> getLeftEdges(int boardSize){
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        for (int y = 1 ; y < boardSize - 1; y++) {
            hexes.add(new HexImpl(1 - boardSize,y));
        }
        return hexes;
    }

    private void assertTopLeftEdge(Map<HexImpl, HexImpl> hexMap, int boardSize){
        Set<HexImpl> TopLeftHexes = getTopLeftEdges(boardSize);
        for (HexImpl hex: TopLeftHexes){
            assertEquals(hexMap.get(hex).getEdge(), Edge.TOPLEFT);
            hexMap.remove(hex);
        }
    }
    private Set<HexImpl> getTopLeftEdges(int boardSize){
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        int y = -1;
        for (int x = 2 - boardSize ; x < 0; x++) {
            hexes.add(new HexImpl(x,y));
            y--;
        }
        return hexes;
    }

    private void assertAllCorners(Map<HexImpl,HexImpl> hexMap, int boardSize){
        assertTopCorner(hexMap, boardSize);
        assertTopRightCorner(hexMap, boardSize);
        assertBottomRightCorner(hexMap,boardSize);
        assertBottomCorner(hexMap,boardSize);
        assertBottomLeftCorner(hexMap,boardSize);
        assertTopLeftCorner(hexMap,boardSize);
    }
    private void assertRemainingHexesAreNotACorner(Map<HexImpl,HexImpl> hexMap){
        for (Map.Entry<HexImpl,HexImpl> hex: hexMap.entrySet()) {
            assertEquals(hex.getValue().getCorner(), Corner.NOTACORNER);
        }
    }
    private void assertTopCorner(Map<HexImpl,HexImpl> hexMap, int boardSize){
        HexImpl top = new HexImpl(0, 1 - boardSize);
        assertEquals(hexMap.get(top).getCorner(), Corner.TOP);
        hexMap.remove(top);
    }
    private void assertTopRightCorner(Map<HexImpl,HexImpl> hexMap, int boardSize){
        HexImpl topRight = new HexImpl(boardSize - 1, 1 - boardSize);
        assertEquals(hexMap.get(topRight).getCorner(), Corner.TOPRIGHT);
        hexMap.remove(topRight);
    }
    private void assertBottomRightCorner(Map<HexImpl,HexImpl> hexMap, int boardSize){
        HexImpl bottomRight = new HexImpl(boardSize - 1, 0);
        assertEquals(hexMap.get(bottomRight).getCorner(), Corner.BOTTOMRIGHT);
        hexMap.remove(bottomRight);
    }
    private void assertBottomCorner(Map<HexImpl,HexImpl> hexMap, int boardSize){
        HexImpl bottom = new HexImpl(0, boardSize - 1);
        assertEquals(hexMap.get(bottom).getCorner(), Corner.BOTTOM);
        hexMap.remove(bottom);
    }
    private void assertBottomLeftCorner(Map<HexImpl,HexImpl> hexMap, int boardSize){
        HexImpl bottomLeft = new HexImpl(1 - boardSize, boardSize - 1);
        assertEquals(hexMap.get(bottomLeft).getCorner(), Corner.BOTTOMLEFT);
        hexMap.remove(bottomLeft);
    }
    private void assertTopLeftCorner(Map<HexImpl,HexImpl> hexMap, int boardSize){
        HexImpl topLeft = new HexImpl(1 - boardSize, 0);
        assertEquals(hexMap.get(topLeft).getCorner(), Corner.TOPLEFT);
        hexMap.remove(topLeft);
    }
    private static Map<HexImpl,HexImpl> getHexMap(int boardSize){
        HexGenerator hexGenerator = new HexGenerator(boardSize);
        Set<HexImpl> allHexes = hexGenerator.generateHexes();
        Map<HexImpl, HexImpl> hexMap = new HashMap<HexImpl, HexImpl>();
        for (HexImpl hex: allHexes){
            hexMap.put(hex,hex);
        }
        return hexMap;
    }
}