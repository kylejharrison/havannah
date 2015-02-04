package game.elements;

import java.awt.geom.Path2D;

import static game.elements.Board.hexWidth;

/**
 * Helps when
 * Created by kyle on 2/7/15.
 */
public class PathHelper {
    public static Path2D getHexagonPath(){
        Double[] xPoints = {hexWidth / 4.0, hexWidth * 0.75, hexWidth, hexWidth * 0.75, hexWidth / 4.0, 0.0};
        Double[] yPoints = {Board.hexHeight, Board.hexHeight, Board.hexHeight / 2.0 , 0.0, 0.0, Board.hexHeight / 2.0};
        Path2D hexagon = new Path2D.Double();
        hexagon.moveTo(0, Board.hexHeight / 2.0);
        for(int i = 0; i < xPoints.length; ++i) {
            hexagon.lineTo(xPoints[i], yPoints[i]);
        }
        return hexagon;
    }
}
