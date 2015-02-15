package game.elements;

/*
 * Represents the Corner the Hex is, or NOTACORNER if not
 * Created by kyle on 2/4/15.
 */
public enum Corner {
    TOP(true),
    TOPRIGHT(true),
    BOTTOMRIGHT(true),
    BOTTOM(true),
    BOTTOMLEFT(true),
    TOPLEFT(true),
    NOTACORNER(false);

    private final boolean aCorner;

    Corner(boolean aCorner){
        this.aCorner = aCorner;
    }

    public boolean isACorner(){
        return aCorner;
    }
}
