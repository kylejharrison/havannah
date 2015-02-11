package game;

import game.elements.Hex;
import game.elements.HexValue;

import java.util.Set;

/**
 * Defines the contracts of whether a move means the game is over.
 * Created by kyle on 2/8/15.
 */
public interface CheckGameState {

    /**
     * returns what the game state will be after the next move
     * @param currentState Set of the game hexes
     * @param move the hex of the move to test
     * @param hexValue of the player making the move
     * @return the GameOverState enum which describes whether the player's move will end the game
     */
    GameState getGameState(Set<Hex> currentState, Hex move, HexValue hexValue);

}
