package game.player;

import game.elements.HexImpl;
import game.elements.HexValue;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kyle on 2/3/15.
 * helper methods for tests
 */
public class TestHelpers {
    public static Set<HexImpl> getHexCollection(int numberOfBlue, int numberOfRed, int numberOfEmpty) {
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        hexes.addAll(getHexCollectionForValue(HexValue.BLUE, numberOfBlue + hexes.size(), hexes.size()));
        hexes.addAll(getHexCollectionForValue(HexValue.RED, numberOfRed + hexes.size(), hexes.size()));
        hexes.addAll(getHexCollectionForValue(HexValue.EMPTY, numberOfEmpty + hexes.size(), hexes.size()));
        return hexes;
    }

    private static Set<HexImpl> getHexCollectionForValue(HexValue value, int number, int startNumber) {
        Set<HexImpl> hexes = new HashSet<HexImpl>();
        for (int i = startNumber; i < number; i++) {
            HexImpl e = new HexImpl(0, i);
            e.setHexValue(value);
            hexes.add(e);
        }
        return hexes;
    }
}
