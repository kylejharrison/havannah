package game;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

import static game.player.PlayerType.PlayerOption;

public class GameRunnerImplTest {
    private static final Logger LOG = Logger.getLogger(GameRunnerImplTest.class.getName());

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsANonIntegerBoardSize() throws Exception {
        GameRunnerImpl.main(new String[]{"1", "boardSize", "RANDOM"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsANonIntegerNumberOfGames() throws Exception {
        GameRunnerImpl.main(new String[]{"NumberOfGames", "2", "RANDOM"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsOnlyOneArgument() throws Exception {
        GameRunnerImpl.main(new String[]{"3"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsOnlyTwoArguments() throws Exception {
        GameRunnerImpl.main(new String[]{"3", "3"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsBoardSizeOfLessThanTwo() throws Exception {
        GameRunnerImpl.main(new String[]{"1", "1", "RANDOM"});
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsNumberOfGamesOfLessThanOne() throws Exception {
        GameRunnerImpl.main(new String[]{"-1", "2", "RANDOM"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsANonPlayerType() throws Exception {
        GameRunnerImpl.main(new String[]{"2", "3", "HopefullyWillNeverBeAPlayerType"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMainRejectsAHUMANPlayerType() throws Exception {
        GameRunnerImpl.main(new String[]{"1", "3", "HUMAN", "RANDOM"});
    }

    @Test
    public void testMainRunsWithValidArguments() throws Exception{
        GameRunnerImpl.main(new String[]{"1", "3", "RANDOM"});
        GameRunnerImpl.main(new String[]{"2", "4", "RANDOM", "RANDOM"});
        GameRunnerImpl.main(new String[]{"2", "5", "CROWDING", "RANDOM"});
    }
    @Test
    public void testGameRunnerResultsTotalNumberOfGames() throws Exception{
        for (int numberOfGames = 1; numberOfGames < 20; numberOfGames++) {
            ArrayList<PlayerOption> players = new ArrayList<>();
            players.add(PlayerOption.CROWDING);
            players.add(PlayerOption.RANDOM);
            GameRunner gameRunner = new GameRunnerImpl(numberOfGames,8, players);
            gameRunner.run();
            while (!gameRunner.areGamesFinished()){
                LOG.info("Games still Running");
                Thread.sleep(1000);
            }
            HashSet<RunnerPlayer> playerResults = gameRunner.getPlayerResults();
            Assert.assertEquals(numberOfGames, playerResults.stream()
                    .mapToInt(RunnerPlayer::getWins).sum() + gameRunner.getDraws());
        }
    }
}