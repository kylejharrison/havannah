package game.controls;

import game.elements.Board;
import game.elements.Player;
import ui.Title;

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

        Title title = new Title("This is a new game");
        gameFrame.add(title);

        Board gameBoard = new Board(boardSize);
        gameFrame.add(gameBoard);

        //Set size and make the window visible
        gameFrame.setSize(450, 350);
        gameFrame.setVisible(true);
    }
}
