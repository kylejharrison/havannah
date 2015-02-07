package game.player;

import game.elements.Hex;

import java.util.Set;

/**
 * The contract of what a player (ai) needs to do to play the game.
 * Created by steve on 29/01/15.
 */
public interface AIPlayer extends Player{

    /**
     * The move to be made by the player.
     * @param currentState current state of the board
     * @return the single Hex that the player wants to play.
     */
    Hex move(Set<Hex> currentState);

}
