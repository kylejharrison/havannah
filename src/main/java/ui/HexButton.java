package ui;

import game.elements.Hex;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Path2D;


/**
 * Created by kyle on 1/24/15.
 * Overrides a normal button to be hexagon shaped
 */
public class HexButton extends JButton {
    private Color color;

    public HexButton (Color color){
        this.color = color;
        drawButton();
    }
    private void drawButton(){
        final Border hex;
        Border empty;
        empty = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        hex = BorderFactory.createCompoundBorder(empty, new HexBorder(Color.BLACK));
        this.setBorderPainted(true);
        this.setFocusPainted(false);
        this.setBorder(hex);
        this.setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g){
        Path2D hexagon = Hex.getHexagonPath();
        g.setColor(color);
        ((Graphics2D) g).fill(hexagon);
    }
    public void changeColor(Color color){
        this.color = color;
        this.paintComponent(this.getGraphics());
    }
}
