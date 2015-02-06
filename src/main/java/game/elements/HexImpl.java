package game.elements;

import game.ui.HexButton;

/**
 * Created by kyle on 1/18/15.
 * The cell object for the board
 */
public class HexImpl implements Hex {
    private int xAxis;
    private int yAxis;
    private HexValue hexValue = HexValue.EMPTY;
    final private HexButton button;
    final private Edge edge;
    final private Corner corner;

    public HexImpl(int xAxis, int yAxis, Edge edge, Corner corner){
        this(xAxis, yAxis, edge, corner, null);
    }

    public HexImpl(int xAxis, int yAxis, Edge edge, Corner corner, HexButton button){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.button = button;
        this.edge = edge;
        this.corner = corner;
    }


    @Override
    public int getXAxis(){
        return this.xAxis;
    }

    @Override
    public int getYAxis(){
        return this.yAxis;
    }

    @Override
    public HexValue getHexValue() {
        return hexValue;
    }

    @Override
    public boolean isNextTo(Hex anotherHex) {
        if (anotherHex.equals(this)) return false;
        int diffXAxis = this.xAxis - anotherHex.getXAxis();
        int diffYAxis = this.yAxis - anotherHex.getYAxis();
        return (diffXAxis == 0 || diffYAxis == 0) && (Math.abs(diffXAxis) == 1 || Math.abs(diffYAxis) == 1)
                || (diffXAxis == 1 && diffYAxis == -1) || (diffXAxis == -1 && diffYAxis == 1);
    }

    @Override
    public Edge getEdge() {
        return this.edge;
    }

    @Override
    public Corner getCorner() {
        return this.corner;
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

        HexImpl hex = (HexImpl) o;

        return xAxis == hex.xAxis && yAxis == hex.yAxis;

    }

    @Override
    public String toString(){
        return String.format("%d,%d", xAxis, yAxis);
    }

}
