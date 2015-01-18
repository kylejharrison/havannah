import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kyle on 1/18/15.
 * Will draw and update the game board panel
 */
public class Board extends JPanel{
    private ArrayList<Hex> allHexes;

    public Board (ArrayList<Hex> allHexes){
        this.allHexes = allHexes;
    }
    public void displayBoard(){
        JFrame gameFrame = new JFrame("Game Window");
        Container container = gameFrame.getContentPane();
        gameFrame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        TitlePanel title = new TitlePanel("This is a new game");
        gameFrame.add(title);

        TitlePanel board = new TitlePanel("The board will go here");
        gameFrame.add(board);

        //Set size and make the window visible
        gameFrame.setSize(450, 350);
        gameFrame.setVisible(true);
    }
}
