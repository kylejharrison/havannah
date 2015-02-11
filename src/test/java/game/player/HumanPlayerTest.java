package game.player;

import game.elements.HexValue;
import org.testng.annotations.Test;

public class HumanPlayerTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void testCreationThrowsExceptionOnBadColour() throws Exception {
        new HumanPlayer(HexValue.EMPTY);
    }

}