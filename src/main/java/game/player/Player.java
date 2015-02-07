package game.player;

import game.elements.HexValue;

/**
 * Contract of what each player needs to have (AI and Human)
 * Created by kyle on 2/7/15.
 */
public interface Player {
    /**
     * Each Player can be either Human or AI
     * @return true if the Player is a human player, false if AI
     */
    boolean isHuman();

    /**
     * Each player is designated a HexValue colour
     * @return the HexValue of the player
     */
    HexValue getPlayerColour();
}
