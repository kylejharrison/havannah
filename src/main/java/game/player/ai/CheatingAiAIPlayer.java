package game.player.ai;

import game.elements.Hex;
import game.elements.HexImpl;
import game.elements.HexValue;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * See if we can manipulate the game state that we receive to win the game instantly.
 * Created by steve on 29/01/15.
 */
class CheatingAiAIPlayer extends AbstractAIPlayer {

    private static final Logger LOG = Logger.getLogger(CheatingAiAIPlayer.class.getName());

    public CheatingAiAIPlayer(HexValue playerColour) {
        super(playerColour);
    }

    @Override
    public Hex move(Set<Hex> currentState) {
        //can I change to state of the Hexes to ensure I win?
        LOG.info("THERE'S A KILL SCREEN COMING UP!");
        setAllHexMyColour(currentState);
        return getMove(currentState);
    }

    private Hex getMove(Set<Hex> currentState) {
        //now make one of them not so I can make a legal move (in-case the game checks for illegal moves before a winning move)
        Hex firstHex = currentState.iterator().next();
        HexImpl newHex = new HexImpl(firstHex.getXAxis(),firstHex.getYAxis(),firstHex.getEdge(),firstHex.getCorner());
        newHex.setHexValue(HexValue.EMPTY);
        currentState.remove(firstHex);
        currentState.add(newHex);
        return newHex;
    }

    private void setAllHexMyColour(Set<Hex> currentState) {
        //make them all my colour!
        Set<Hex> newCurrentState = new HashSet<Hex>();
        for (Hex hex : currentState){
            HexImpl myHex = new HexImpl(hex.getXAxis(),hex.getYAxis(),hex.getEdge(),hex.getCorner());
            myHex.setHexValue(playerColour);
            newCurrentState.add(myHex);
        }
        currentState.removeAll(currentState);
        currentState.addAll(newCurrentState);
        LOG.info("It's all happening.");
    }

}
