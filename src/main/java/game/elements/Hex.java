package game.elements;

/**
 * Created by kyle on 2/4/15.
 * The contract of what you can do with the Hex-  the cell, tile or button (depending on what I'm feeling at the time)
 * of the game board.
 */
public interface Hex {


    /**
     * The hexes are flat topped.
     * The X axis is the vertical column of hexes, where 0 is the middle column, and increases from left to right.
     * therefore the range will be 1 - boardSize on the left edge to boardSize -1 on the right edge.
     * @return the X Axis value of the Hex.
     */
    int getXAxis();


    /**
     * As the Board is hexagon shaped (flat topped), the y Axis is the diagonal from the top left corner to the bottom right corner.
     * The values increase going downwards and decrease going upwards (obviously).
     * E.g. the top left corner hex is 0 and the bottom left is 0;
     * the top right is 1 - boardSize and bottom left is boardSize -1
     * @return the Y Axis value of the Hex.
     */
    int getYAxis();

    /**
     * HexValue is the value (team it belongs to) of the Hex
     * @return hexValue of the hex
     */
    HexValue getHexValue();

    /**
     * Next to meaning is one of the maximum 6 possible hexes immediately adjacent to the hex
     * NOTE: a hex is not next to itself
     * @param anotherHex to check if the hex is next to it
     * @return boolean whether the two hexes are next to each other
     */
    boolean isNextTo(Hex anotherHex);

    /**
     * The edge a hex belongs to. Note, an edge IS NOT a corner.
     * @return the edge it belongs to, NOTANEDGE if not on any edge
     */
    Edge getEdge();

    /**
     * Which corner a hex is. Note, a corner IS NOT an edge
     * @return which corner the hex is, NOTACORNER if not a corner
     */
    Corner getCorner();
}
