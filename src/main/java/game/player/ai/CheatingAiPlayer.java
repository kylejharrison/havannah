package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;

import java.util.List;
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
    public Hex move(List<Hex> currentState) {
        //can I change to state of the Hexes to ensure I win?
        LOG.info("THERE'S A KILL SCREEN COMING UP!");
        setAllHexMyColour(currentState);
        return getMove(currentState);
    }

    private Hex getMove(List<Hex> currentState) {
        //now make one of them not so I can make a legal move (in-case the game checks for illegal moves before a winning move)
        Hex hex = currentState.get(0);
        hex.setHexValue(HexValue.EMPTY);
        return hex;
    }

    private void setAllHexMyColour(List<Hex> currentState) {
        //make them all my colour!
        for (Hex hex : currentState){
            hex.setHexValue(playerColour);
        }
    }
}
