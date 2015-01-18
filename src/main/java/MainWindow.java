/**
 * Created by kyle on 1/18/15.
 * Main Menu - main class for game
 */
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JPanel{
    public static void main(String[] args){
        //Create the main game window
        JFrame frame = new JFrame("Havannah Main Menu");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        //Add the title text at the top
        TitlePanel title = new TitlePanel("Welcome to Havannah");
        frame.add(title);

        //Set size and make the window visible
        frame.setSize(450, 350);
        frame.setVisible(true);
    }
}
