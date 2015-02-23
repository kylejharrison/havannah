package game.elements;

import java.awt.*;

/**
 * Represents the value (which team it belongs) of the hex
 * Created by steve on 29/01/15.
 */
public enum HexValue {

    BLUE(true, Color.BLUE),
    RED(true, Color.RED),
    GREEN(true, Color.GREEN),
    BLACK(true, Color.BLACK),
    ORANGE(true, Color.ORANGE),
    EMPTY(false, Color.WHITE);


    private final boolean validForPlayer;
    private final Color color;

    HexValue(boolean validForPlayer, Color color) {
        this.validForPlayer = validForPlayer;
        this.color = color;
    }

    public boolean isValidForPlayer() {
        return validForPlayer;
    }

    public Color getColor() {
        return color;
    }
}
