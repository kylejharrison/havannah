package game;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GameRunnerTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsANonIntegerBoardSize() throws Exception {
        GameRunner.main(new String[]{"a", "b"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsOnlyOneArgument() throws Exception {
        GameRunner.main(new String[]{"3"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsBoardSizeOfLessThanTwo() throws Exception {
        GameRunner.main(new String[]{"1","RANDOM"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsANonPlayerType() throws Exception {
        GameRunner.main(new String[]{"3","HopefullyWillNeverBeAPlayerType"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsAHUMANPlayerType() throws Exception {
        GameRunner.main(new String[]{"3","HUMAN","RANDOM"});
    }

    @Test
    public void testMainRunsWithValidArguments() throws Exception{
        GameRunner.main(new String[]{"3","RANDOM"});
        GameRunner.main(new String[]{"4","RANDOM","RANDOM"});
        GameRunner.main(new String[]{"5","RANDOM","CROWDING"});
    }
}