package game.elements;

import game.ui.GameWindow;

/**
 * Created by kyle on 1/18/15.
 * Controls all aspects of the game
 */
public class Game {
    private int boardSize;

    public Game(int boardSize) {
        this.boardSize = boardSize;
    }

    public void run() {
        GameWindow gameWindow = new GameWindow();
        addBoard(gameWindow);
        gameWindow.showGameFrame();
    }

    private void addBoard(GameWindow gameWindow){
        Board board = new Board(boardSize);
        gameWindow.addGameElement(board);
    }

}