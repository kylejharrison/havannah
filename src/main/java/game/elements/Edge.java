package game.elements;

/**
 * Represents the Edge the Hex is on- or NOTANEDGE if not
 * Created by kyle on 2/4/15.
 */
public enum Edge {

    TOPRIGHT(true),
    RIGHT(true),
    BOTTOMRIGHT(true),
    BOTTOMLEFT(true),
    LEFT(true),
    TOPLEFT(true),
    NOTANEDGE(false);

    private boolean anEdge;

    Edge(boolean anEdge){
        this.anEdge = anEdge;
    }

    public boolean isAnEdge(){
        return anEdge;
    }
}
