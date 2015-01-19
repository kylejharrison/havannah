import UiElements.TitlePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kyle on 1/18/15.
 * Runs the game n shit.
 */
public class GameRunner extends JPanel{
    private int boardSize;
    private int difficulty;
    private Player player1;
    private Player player2;

    public GameRunner(int boardSize, int difficulty, Player player1, Player player2){
        this.boardSize = boardSize;
        this.difficulty = difficulty;
        this.player1 = player1;
        this.player2 = player2;
    }
    public void run(){
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
