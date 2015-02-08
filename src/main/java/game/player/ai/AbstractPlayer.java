package game.player.ai;

import game.elements.Hex;
import game.elements.HexValue;
import game.player.Player;

/**
 * An AbstractPLayer to share common values and methods.
 * Created by steve on 29/01/15.
 */
abstract class AbstractPlayer implements Player {

    final HexValue playerColour;

    AbstractPlayer(HexValue playerColour) {
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
}
