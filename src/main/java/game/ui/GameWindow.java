package game.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kyle on 1/18/15.
 * Runs the game n shit.
 */
public class GameWindow extends JPanel {

    private JFrame gameFrame = createGameFrame();

    public GameWindow() {
        addGameTitle();
    }

    private JFrame createGameFrame() {
        JFrame gameFrame = new JFrame("Game Window");
        Container container = gameFrame.getContentPane();
        gameFrame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        gameFrame.setSize(450, 350);
        return gameFrame;
    }
    public void showGameFrame(){
        //make the window visible
        gameFrame.setVisible(true);
    }

    private void addGameTitle() {
        Title title = new Title("This is a new game");
        gameFrame.add(title);
    }

    public void addGameElement(Component component){
        gameFrame.add(component);
    }

}
