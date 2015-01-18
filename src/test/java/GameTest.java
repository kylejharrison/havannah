import junit.framework.TestCase;
import org.junit.Test;

public class GameTest extends TestCase {

    @Test
    public void testCalculateMaxNumberOfHexes(){
        Game game = new Game(8, 0, true);
        assertEquals(game.cal);
    }
}