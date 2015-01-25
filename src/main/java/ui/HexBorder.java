package ui;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by kyle on 1/24/15.
 */
public class HexBorder extends AbstractBorder{
    private final static int MARGIN = 5;
    private Color color;
    public HexBorder(Color color){
        this.color = color;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
        Double oneUp = Math.sin(Math.toRadians(60.0)) * 20.0;
        Double oneAcross = Math.cos(Math.toRadians(60.0)) * 20.0;
        Double[] xPoints = {20.0, 40.0, 40.0 + oneAcross, 40.0, 20.0, oneAcross};
        Double[] yPoints = {2 * oneUp, 2 * oneUp, oneUp, 0.0, 0.0, oneUp};
        Path2D hexagon = new Path2D.Double();
        hexagon.moveTo(oneAcross,oneUp);
        for(int i = 0; i < xPoints.length; ++i) {
            hexagon.lineTo(xPoints[i], yPoints[i]);
        }
        g.setColor(color);
        ((Graphics2D) g).draw(hexagon);
    }
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(MARGIN, MARGIN, MARGIN, MARGIN);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = MARGIN;
        insets.top = MARGIN;
        insets.right = MARGIN;
        insets.bottom = MARGIN;
        return insets;
    }
}
