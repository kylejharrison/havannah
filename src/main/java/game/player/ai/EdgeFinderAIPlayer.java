package game.player.ai;

import game.AIPlayerAlgos;
import game.elements.Hex;
import game.elements.HexValue;
import game.player.AIPlayer;
import game.player.AbstractPlayer;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An AI player which will try and connect 3 edges
 * Created by kyle on 3/2/15.
 */
public class EdgeFinderAIPlayer extends AbstractPlayer implements AIPlayer{

    protected EdgeFinderAIPlayer(HexValue playerColour) {
        super(playerColour);
    }

    @Override
    public Hex move(Set<Hex> currentState) {
        Set<Hex> possibleMoves = currentState.stream().filter(h -> h.getHexValue().equals(HexValue.EMPTY)).collect(Collectors.toSet());
        Optional<Hex> winningMove = AIPlayerAlgos.getWinningMove(this, currentState, possibleMoves);
        return winningMove.isPresent() ? winningMove.get() : makeNextMove(currentState);
    }

    private Hex makeNextMove(Set<Hex> currentState) {
        if(takeACorner().isPresent()){
            return takeACorner().get();
        }
        return new RandomAiAIPlayer(playerColour).move(currentState);
    }

    private Optional<Hex> takeACorner() {
        return Optional.empty();
    }
}
