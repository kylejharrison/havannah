/**
 * Created by kyle on 1/18/15.
 * Main Menu - main class for game
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class MainMenu extends JPanel{
    public void paint(Graphics graphic){
        Graphics2D title = (Graphics2D) graphic;
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        title.setFont(new Font("Serif", Font.PLAIN, 48));
        paintHorizontallyCenteredText(title, "Hello KY", 200, 75);
    }
    protected void paintHorizontallyCenteredText(Graphics2D graphic, String text, float centreX, float baselineY){
        FontRenderContext frc = graphic.getFontRenderContext();
        Rectangle2D bounds = graphic.getFont().getStringBounds(text, frc);
        float width = (float) bounds.getWidth();
        graphic.drawString(text, centreX - width / 2, baselineY);
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        frame.setContentPane(new MainMenu());
        frame.setSize(40, 40);
        frame.setVisible(true);
    }
}
