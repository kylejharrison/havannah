package game.player;

import game.elements.Hex;
import game.elements.HexValue;

/**
 * Created by steve on 29/01/15.
 */
public abstract class AbstractPlayer implements Player {

    private HexValue playerColour;

    public AbstractPlayer(HexValue playerColour) {
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
