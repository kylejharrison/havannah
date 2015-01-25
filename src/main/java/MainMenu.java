/**
 * Created by kyle on 1/18/15.
 * Main Menu - main class for game
 */

import ui.Title;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JPanel{
    public static void main(String[] args){
        //Create the main game window
        JFrame frame = new JFrame("Havannah Main Menu");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        Container container = frame.getContentPane();
        frame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        //Add the title text at the top
        Title title = new Title("Welcome to Havannah");
        frame.add(title);

        //Add a button to start new Game
        ui.Button newGame = new ui.Button("Start New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // static defaults for now
                int boardSize = 8;
                int difficulty = 0;
                Player ky = new Player("KY", "Blue", true);
                Player ai = new Player("ai", "Red", false);
                //Hands off the running of the game to the GameRunner
                GameRunner gameRunner = new GameRunner(boardSize, difficulty, ky, ai);
                gameRunner.run();
            }
        });
        frame.add(newGame);

        //Set size and make the window visible
        frame.setSize(450, 350);
        frame.setVisible(true);
    }
}
