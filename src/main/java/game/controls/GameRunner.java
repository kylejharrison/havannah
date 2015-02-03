package game.controls;

import game.elements.Board;
import ui.Title;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kyle on 1/18/15.
 * Runs the game n shit.
 */
public class GameRunner extends JPanel {
    private int boardSize;

    public GameRunner(int boardSize) {
        this.boardSize = boardSize;
    }

    public void run() {
        JFrame gameWindow = createGameFrame();
        addGameElements(gameWindow);
        showGameFrame(gameWindow);
    }

    private JFrame createGameFrame() {
        JFrame gameFrame = new JFrame("Game Window");
        Container container = gameFrame.getContentPane();
        gameFrame.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        gameFrame.setSize(450, 350);

        return gameFrame;
    }
    private void showGameFrame(JFrame gameFrame){
        //make the window visible
        gameFrame.setVisible(true);
    }

    private void addGameElements(JFrame gameFrame){
        addGameTitle(gameFrame);
        addGameBoard(gameFrame);
    }
    private void addGameTitle(JFrame gameFrame) {
        Title title = new Title("This is a new game");
        gameFrame.add(title);
    }

    private void addGameBoard(JFrame gameFrame){
        Board gameBoard = new Board(boardSize);
        gameFrame.add(gameBoard);
    }

}
