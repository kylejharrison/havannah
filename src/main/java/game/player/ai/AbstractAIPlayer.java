package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;
import game.player.AIPlayer;

/**
 * An AbstractPLayer to share common values and methods.
 * Created by steve on 29/01/15.
 */
abstract class AbstractAIPlayer implements AIPlayer {

    final HexValue playerColour;

    AbstractAIPlayer(HexValue playerColour) {
        validatePlayerColour(playerColour);
        this.playerColour = playerColour;
    }

    private void validatePlayerColour(HexValue playerColour) {
        if (!playerColour.isValidForPlayer()){
            throw new RuntimeException("FUCK OFF, that not a valid colour for a player you twat!");
        }
    }

    static boolean isValidMove(Hex move){
        return HexValue.EMPTY.equals(move.getHexValue());
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public HexValue getPlayerColour() {
        return playerColour;
    }
}
