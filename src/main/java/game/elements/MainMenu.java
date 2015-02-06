package game.elements; /**
 * Created by kyle on 1/18/15.
 * Main Menu - main class for game
 */

import game.ui.Title;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel{
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        // Stolen from http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame window = createUI();
                addElements(window);
                showUI(window);
            }
        });
    }

    private static JFrame createUI() {
        //Create the main game window
        JFrame frame = new JFrame("Havannah Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        frame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        //Set size and make the window visible
        frame.setSize(450, 350);

        return frame;
    }

    private static void showUI(JFrame frame) {
        frame.setVisible(true);
    }

    private static void addElements(JFrame frame) {
        //add elements to the window in order
        addTitle(frame);
        addNewGameButton(frame);
    }

    private static void addTitle(JFrame frame) {
        //Add the title text at the top
        Title title = new Title("Welcome to Havannah");
        frame.add(title);
    }

    private static void addNewGameButton(JFrame frame){
        //Add a button to start new game.elements.Game
        game.ui.Button newGame = new game.ui.Button("Start New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // static defaults for now
                int boardSize = 8;
                //Hands off the running of the game to the game.controls.GameRunner
                Game game = new Game(boardSize);
                game.run();
            }
        });
        frame.add(newGame);
    }
}
