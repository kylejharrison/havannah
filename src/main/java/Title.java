import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Created by kyle on 1/18/15.
 * Title panel for MainMenu
 */
public class Title extends JPanel{
    // Set the title text and style
    public void paint(Graphics graphic){
        Graphics2D title = (Graphics2D) graphic;
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        title.setFont(new Font("Serif", Font.PLAIN, 48));
        paintHorizontallyCenteredText(title, "Hello KY", 200, 75);
    }
    // Draw it centered - TODO: Should this be in a separate class?
    protected void paintHorizontallyCenteredText(Graphics2D graphic, String text, float centreX, float baselineY){
        FontRenderContext frc = graphic.getFontRenderContext();
        Rectangle2D bounds = graphic.getFont().getStringBounds(text, frc);
        float width = (float) bounds.getWidth();
        graphic.drawString(text, centreX - width / 2, baselineY);
    }
}
