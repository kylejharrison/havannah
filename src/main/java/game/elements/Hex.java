package game.elements;

import java.awt.geom.Path2D;
import java.util.ArrayList;

import static game.elements.Board.hexWidth;

/**
 * Created by kyle on 1/18/15.
 * The cell object for the board
 */
public class Hex {
    private int xAxis;
    private int yAxis;
    private int zAxis;
    private ArrayList<Hex> allConnected;
    private HexValue hexValue = HexValue.EMPTY;

    public void Hex(int xAxis, int yAxis, int zAxis, int maxConnections){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.zAxis = zAxis;
        this.allConnected = new ArrayList<Hex>(maxConnections);
    }

    //All getters
    public int getxAxis(){
        return this.xAxis;
    }

    public int getyAxis(){
        return this.yAxis;
    }

    public int getzAxis(){
        return this.zAxis;
    }

    public ArrayList<Hex> getAllConnected(){
        return this.allConnected;
    }

    public void addConnected(Hex connected){
        this.allConnected.add(connected);
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
    //In order to removed connected hexes when undoing a move
    public void removeConnected(Hex connected){
        this.allConnected.remove(connected);
    }

    public HexValue getHexValue() {
        return hexValue;
    }

    public void setHexValue(HexValue hexValue) {
        this.hexValue = hexValue;
    }
}
