package game.player.ai;

import game.elements.HexImpl;
import game.elements.HexValue;

import java.util.Set;
import java.util.logging.Logger;

/**
 * See if we can manipulate the game state that we receive to win the game instantly.
 * Created by steve on 29/01/15.
 */
class CheatingAiPlayer extends AbstractPlayer {

    private static final Logger LOG = Logger.getLogger(CheatingAiPlayer.class.getName());

    public CheatingAiPlayer(HexValue playerColour) {
        super(playerColour);
    }

    @Override
    public HexImpl move(Set<HexImpl> currentState) {
        //can I change to state of the Hexes to ensure I win?
        LOG.info("THERE'S A KILL SCREEN COMING UP!");
        setAllHexMyColour(currentState);
        return getMove(currentState);
    }

    private HexImpl getMove(Set<HexImpl> currentState) {
        //now make one of them not so I can make a legal move (in-case the game checks for illegal moves before a winning move)
        HexImpl firstHex = currentState.iterator().next();
        firstHex.setHexValue(HexValue.EMPTY);
        return firstHex;
    }

    private void setAllHexMyColour(Set<HexImpl> currentState) {
        //make them all my colour!
        for (HexImpl hex : currentState){
            hex.setHexValue(playerColour);
        }
    }
}
