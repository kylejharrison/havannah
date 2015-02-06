package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * An AI player that will just try and crowd the other player with no aim of going for a win.
 * Created by steve on 29/01/15.
 */
class CrowdingAiPlayer extends AbstractPlayer {

    private static final Logger LOG = Logger.getLogger(CrowdingAiPlayer.class.getName());
    private final Map<Hex, Hex> opponentsState;

    public CrowdingAiPlayer(HexValue playerColour) {
        super(playerColour);
        opponentsState = new HashMap<Hex, Hex>();
    }

    @Override
    public Hex move(Set<Hex> currentState) {
        Hex lastMove = getLastMove(currentState);
        Hex hex = firstAdjacentHex(currentState, lastMove);
        if (hex != null){
            opponentsState.put(lastMove, lastMove);
            return hex;
        }else{
            return RandomAiPlayer.getRandomHex(currentState);
        }
    }

    private Hex firstAdjacentHex(Set<Hex> currentState, Hex lastMove) {
        if (lastMove != null){
            for (Hex current : currentState){
                if (HexValue.EMPTY.equals(current.getHexValue()) && current.isNextTo(lastMove)){
                    return current;
                }
            }
        }
        return null;
    }

    private Hex getLastMove(Set<Hex> currentState) {
        for (Hex current : currentState){
            if (!HexValue.EMPTY.equals(current.getHexValue()) && !playerColour.equals(current.getHexValue())){
                //an opponents move
                Hex hex = opponentsState.get(current);
                //if null, this is new to us
                if (hex == null){
                    return current;
                }
            }
        }
        return null;
    }
}
