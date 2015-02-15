package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;
import game.player.AIPlayer;
import game.player.AbstractPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * An AI player that will just try and crowd the other player with no aim of going for a win.
 * Created by steve on 29/01/15.
 */
public class CrowdingAiAIPlayer extends AbstractPlayer implements AIPlayer {

    private static final Logger LOG = Logger.getLogger(CrowdingAiAIPlayer.class.getName());
    private final Map<Hex, Hex> opponentsState;

    public CrowdingAiAIPlayer(HexValue playerColour) {
        super(playerColour);
        opponentsState = new HashMap<>();
    }

    @Override
    public Hex move(Set<Hex> currentState) {
        Hex lastMove = getLastMove(currentState);
        Hex hex = firstAdjacentHex(currentState, lastMove);
        if (hex != null){
            opponentsState.put(lastMove, lastMove);
            return hex;
        }else{
            Hex move = RandomAiAIPlayer.getRandomHex(currentState);
            while(!isValidMove(move)) {
                move = RandomAiAIPlayer.getRandomHex(currentState);
            }
            return move;
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
