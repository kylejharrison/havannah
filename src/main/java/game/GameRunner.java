package game;

import java.util.HashSet;

/**
 * Contract for a GameRunner which automates game play
 * Created by kyle on 2/28/15.
 */
public interface GameRunner {
    /**
     * Get the results of the Games
     * @return a set of Runner Players
     */
    public HashSet<RunnerPlayer> getPlayerResults();

    /**
     * Games can draw too!
     * @return number of draws
     */
    public int getDraws();

    /**
     * Check whether all the games have finished
     * @return true if all the games are finished
     */
    public boolean areGamesFinished();

    /**
     * Run all the games
     */
    public void run();
}
