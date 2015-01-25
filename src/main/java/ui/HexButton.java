package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kyle on 1/24/15.
 */
public class HexButton extends JButton {
    private Color color;
    public HexButton (Color color){
        this.color = color;
    }
    @Override
    public void paintComponent(Graphics g){
        int[] xPoints = {0,0,20,20};
        int[] yPoints = {0,20,20,0};
        Polygon test = new Polygon(xPoints, yPoints, 4);
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        g.fillPolygon(test);
    }
}
