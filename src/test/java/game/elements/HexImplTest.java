package game.elements;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HexImplTest extends TestCase {

    public void testIsNextToReturnsTrueWhenHexesAreAdjacent() throws Exception {
        HexImpl hex1 = new HexImpl(0,0);
        HexImpl hex2 = new HexImpl(0,1);
        HexImpl hex3 = new HexImpl(-1,1);
        Assert.assertTrue(hex1.isNextTo(hex2));
        Assert.assertTrue(hex1.isNextTo(hex3));
    }

    public void testIsNextToReturnsFalseWhenHexesAreNotAdjacent() throws Exception {
        HexImpl hex1 = new HexImpl(0,0);
        HexImpl hex2 = new HexImpl(-1,-1);
        HexImpl hex3 = new HexImpl(0,2);
        Assert.assertFalse(hex1.isNextTo(hex2));
        Assert.assertFalse(hex1.isNextTo(hex3));
        Assert.assertFalse("The same hex is not next to itself", hex1.isNextTo(hex1));
    }
}