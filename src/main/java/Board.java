import javax.swing.*;
import java.awt.*;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    public void displayBoard(){
        JFrame gameFrame = new JFrame("Game Window");
        Container container = gameFrame.getContentPane();
        gameFrame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        TitlePanel title = new TitlePanel("This is a new game");
        gameFrame.add(title);

        //Set size and make the window visible
        gameFrame.setSize(450, 350);
        gameFrame.setVisible(true);
    }
}
