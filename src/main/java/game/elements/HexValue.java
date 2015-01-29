package game.elements;

/**
 * Represents the value (which team it belongs) of the hex
 * Created by steve on 29/01/15.
 */
public enum HexValue {

    BLUE(true),
    RED(true),
    EMPTY(false);

    private boolean validForPlayer;

    HexValue(boolean validForPlayer) {
        this.validForPlayer = validForPlayer;
    }

    public boolean isValidForPlayer() {
        return validForPlayer;
    }
}
