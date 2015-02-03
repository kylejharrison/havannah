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
    final private HexButton button;

    public Hex(int xAxis, int yAxis){
        this(xAxis, yAxis, null);
    }

    public Hex(int xAxis, int yAxis, HexButton button){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.button = button;
    }


    //All getters
    public int getXAxis(){
        return this.xAxis;
    }

    public int getYAxis(){
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

    @Override
    public int hashCode() {
        int result = xAxis;
        result = 31 * result + yAxis;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hex hex = (Hex) o;

        return xAxis == hex.xAxis && yAxis == hex.yAxis;

    }

    @Override
    public String toString(){
        return String.format("%d,%d", xAxis, yAxis);
    }

}
