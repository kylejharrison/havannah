package game.player;

import game.elements.Hex;
import game.elements.HexValue;

/**
 * An AbstractPLayer to share common values and methods.
 * Created by steve on 29/01/15.
 */
public abstract class AbstractPlayer implements Player {

    protected final HexValue playerColour;

    protected AbstractPlayer(HexValue playerColour) {
        validatePlayerColour(playerColour);
        this.playerColour = playerColour;
    }

    public static void validatePlayerColour(HexValue playerColour) {
        if (!playerColour.isValidForPlayer()){
            throw new RuntimeException("FUCK OFF, that not a valid colour for a player you twat!");
        }
    }

    public static boolean isValidMove(Hex move){
        return HexValue.EMPTY.equals(move.getHexValue());
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public HexValue getPlayerHexValue() {
        return playerColour;
    }
}
