package game;

import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class checkGameStateImplTest {

    @Test
    public void testGetGameStateReturnsDrawWhenMoveIsLastEmptyHex() throws Exception {
        Set<Hex> testState = TestHelpers.getHexCollection(9,9,0);
        Hex drawingMove = new HexImpl(-1, -1);
        testState.add(drawingMove);
        assertEquals(new CheckGameStateImpl().getGameState(testState, drawingMove, HexValue.BLUE), GameState.DRAW);
    }
    @Test
    public void testGetGameStateReturnsStillToPlayForWhenEmptyHexesRemain() throws Exception{
        Set<Hex> testState = TestHelpers.getHexCollection(9,9,1);
        Hex move = new HexImpl(-1, -1);
        testState.add(move);
        assertEquals(new CheckGameStateImpl().getGameState(testState, move, HexValue.BLUE), GameState.STILLTOPLAYFOR);
    }


}