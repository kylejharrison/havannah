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
    //In order to removed connected hexes when undoing a move
    public void removeConnected(Hex connected){
        this.allConnected.remove(connected);
    }

}
