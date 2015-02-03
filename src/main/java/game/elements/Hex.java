package game.elements;

import ui.HexButton;

import java.awt.geom.Path2D;

import static game.elements.Board.hexWidth;

/**
 * Created by kyle on 1/18/15.
 * The cell object for the board
 */
public class Hex {
    private int xAxis;
    private int yAxis;
    private HexValue hexValue = HexValue.EMPTY;
    final private int hashCode;
    final private HexButton button;

    public Hex(int xAxis, int yAxis){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.hashCode = generateHashCode();
        button = null;
    }

    public Hex(int xAxis, int yAxis, HexButton button){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.hashCode = generateHashCode();
        this.button = button;
    }

    private int generateHashCode(){
        return 100 * xAxis + yAxis;
    }

    //All getters
    public int getxAxis(){
        return this.xAxis;
    }

    public int getyAxis(){
        return this.yAxis;
    }

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
    public HexValue getHexValue() {
        return hexValue;
    }

    public void setHexValue(HexValue hexValue) {
        this.hexValue = hexValue;
        if (button != null){
            button.changeColor(hexValue.getColor());
        }
    }

    public int getHashCode() {
        return hashCode;
    }

}
