package game.elements;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HexImplTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testThrowsErrorWhenBothAnEdgeAndACorner() throws Exception {
        new HexImpl(0,0,Edge.BOTTOMLEFT,Corner.BOTTOM);
    }

    @Test
    public void testIsNextToReturnsTrueWhenHexesAreAdjacent() throws Exception {
        HexImpl hex1 = new HexImpl(0,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        HexImpl hex2 = new HexImpl(0,1, Edge.NOTANEDGE, Corner.NOTACORNER);
        HexImpl hex3 = new HexImpl(-1,1, Edge.NOTANEDGE, Corner.NOTACORNER);
        Assert.assertTrue(hex1.isNextTo(hex2));
        Assert.assertTrue(hex1.isNextTo(hex3));
    }

    @Test
    public void testIsNextToReturnsFalseWhenHexesAreNotAdjacent() throws Exception {
        HexImpl hex1 = new HexImpl(0,0, Edge.NOTANEDGE, Corner.NOTACORNER);
        HexImpl hex2 = new HexImpl(-1,-1, Edge.NOTANEDGE, Corner.NOTACORNER);
        HexImpl hex3 = new HexImpl(0,2, Edge.NOTANEDGE, Corner.NOTACORNER);
        Assert.assertFalse(hex1.isNextTo(hex2));
        Assert.assertFalse(hex1.isNextTo(hex3));
        Assert.assertFalse(hex1.isNextTo(hex1), "The same hex is not next to itself");
    }
}