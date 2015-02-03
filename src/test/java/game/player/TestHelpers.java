package game.player;

import game.elements.Hex;
import game.elements.HexValue;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kyle on 2/3/15.
 * helper methods for tests
 */
public class TestHelpers {
    public static Set<Hex> getHexCollection(int numberOfBlue, int numberOfRed, int numberOfEmpty) {
        Set<Hex> hexes = new HashSet<Hex>();
        hexes.addAll(getHexCollectionForValue(HexValue.BLUE, numberOfBlue + hexes.size(), hexes.size()));
        hexes.addAll(getHexCollectionForValue(HexValue.RED, numberOfRed + hexes.size(), hexes.size()));
        hexes.addAll(getHexCollectionForValue(HexValue.EMPTY, numberOfEmpty + hexes.size(), hexes.size()));
        return hexes;
    }

    private static Set<Hex> getHexCollectionForValue(HexValue value, int number, int startNumber) {
        Set<Hex> hexes = new HashSet<Hex>();
        for (int i = startNumber; i < number; i++) {
            Hex e = new Hex(0, i);
            e.setHexValue(value);
            hexes.add(e);
        }
        return hexes;
    }
}
