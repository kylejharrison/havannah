package ui;

import javax.swing.border.AbstractBorder;
import java.awt.*;

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
        int[] xPoints = {0,0,40,40};
        int[] yPoints = {0,40,40,0};
        Polygon test = new Polygon(xPoints, yPoints, 4);
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.drawPolygon(test);
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
