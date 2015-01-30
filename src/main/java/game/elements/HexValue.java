package game.elements;

import java.awt.*;

/**
 * Represents the value (which team it belongs) of the hex
 * Created by steve on 29/01/15.
 */
public enum HexValue {

    BLUE(true, Color.BLUE),
    RED(true, Color.RED),
    EMPTY(false, Color.WHITE);

    private boolean validForPlayer;
    private Color color;

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
