package game.ui;

import game.elements.PathHelper;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Creates the Hexagon border used by the button for hex
 * Created by kyle on 1/24/15.
 */
public class HexBorder extends AbstractBorder{
    private final static int MARGIN = 5;
    private final Color color;
    public HexBorder(Color color){
        this.color = color;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
        Path2D hexagon = PathHelper.getHexagonPath();
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
