package game.elements;

import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * Created by kyle on 1/18/15.
 * The cell object for the board
 */
public class Hex {
    private int xAxis;
    private int yAxis;
    private int zAxis;
    private ArrayList<Hex> allConnected;
    public static Double oneUp = (double) Math.round(Math.sin(Math.toRadians(60.0)) * Board.hexSize);
    public static Double oneAcross = 0.5 * Board.hexSize;
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
        Double[] xPoints = {oneAcross, oneAcross * 3, oneAcross * 4, oneAcross * 3, oneAcross, 0.0};
        Double[] yPoints = {oneUp * 2, oneUp *2, oneUp, 0.0, 0.0, oneUp};
        Path2D hexagon = new Path2D.Double();
        hexagon.moveTo(0, oneUp);
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
