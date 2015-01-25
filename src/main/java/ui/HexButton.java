package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;

/**
 * Created by kyle on 1/24/15.
 */
public class HexButton extends JButton {
    private Color color;
    public HexButton (Color color){
        this.color = color;
        final Border hex;
        Border empty;
        empty = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        hex = BorderFactory.createCompoundBorder(empty, new HexBorder(Color.BLACK));
        this.setBorderPainted(true);
        this.setFocusPainted(false);
        this.setBorder(hex);
        this.setOpaque(true);
    }
    @Override
    public void paintComponent(Graphics g){
        Double oneUp = Math.sin(Math.toRadians(60.0)) * 20.0;
        Double oneAcross = Math.cos(Math.toRadians(60.0)) * 20.0;
        Double[] xPoints = {20.0, 40.0, 40.0 + oneAcross, 40.0, 20.0, oneAcross};
        Double[] yPoints = {2 * oneUp, 2 * oneUp, oneUp, 0.0, 0.0, oneUp};
        Path2D hexagon = new Path2D.Double();
        hexagon.moveTo(oneAcross,oneUp);
        for(int i = 0; i < xPoints.length; ++i) {
            hexagon.lineTo(xPoints[i], yPoints[i]);
        }
//        Polygon hexagon = new Polygon(xPoints, yPoints, 6);
//        ((Graphics2D) g).setRenderingHint(
//                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        ((Graphics2D) g).fill(hexagon);
    }
}
