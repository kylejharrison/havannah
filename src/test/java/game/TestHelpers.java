package game;

import game.elements.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kyle on 2/3/15.
 * helper methods for tests
 */
public class TestHelpers {
    public static Set<Hex> getHexCollection(int numberOfBlue, int numberOfRed, int numberOfEmpty) {
        Set<Hex> hexes = new HashSet<>();
        hexes.addAll(getHexCollectionForValue(HexValue.BLUE, numberOfBlue + hexes.size(), hexes.size()));
        hexes.addAll(getHexCollectionForValue(HexValue.RED, numberOfRed + hexes.size(), hexes.size()));
        hexes.addAll(getHexCollectionForValue(HexValue.EMPTY, numberOfEmpty + hexes.size(), hexes.size()));
        return hexes;
    }

    private static Set<HexImpl> getHexCollectionForValue(HexValue value, int number, int startNumber) {
        Set<HexImpl> hexes = new HashSet<>();
        for (int i = startNumber; i < number; i++) {
            HexImpl e = new HexImpl(0, i, Edge.NOTANEDGE, Corner.NOTACORNER);
            e.setHexValue(value);
            hexes.add(e);
        }
        return hexes;
    }

    public static Map<HexImpl,HexImpl> getHexMap(int boardSize){
        HexGenerator hexGenerator = new HexGenerator(boardSize);
        Set<HexImpl> allHexes = hexGenerator.generateHexes();
        Map<HexImpl, HexImpl> hexMap = new HashMap<>();
        for (HexImpl hex: allHexes){
            hexMap.put(hex,hex);
        }
        return hexMap;
    }

}
