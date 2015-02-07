package game;

import game.elements.Board;
import game.elements.HexImpl;
import game.ui.GameWindow;

import java.util.Set;

/**
 * Created by kyle on 1/18/15.
 * Controls and runs all aspects of the game
 */
public class Game {
    private int boardSize;
    private Set<HexImpl> gameState;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        setGameState();
    }

    private void setGameState(){
        HexGenerator hexGenerator = new HexGenerator(boardSize);
        this.gameState = hexGenerator.generateHexes();
    }

    public void run() {
        createGameWindow();
    }


    private void createGameWindow(){
        GameWindow gameWindow = new GameWindow();
        addBoard(gameWindow);
        gameWindow.showGameFrame();

    }
    private void addBoard(GameWindow gameWindow){
        Board board = new Board(boardSize, gameState);
        gameWindow.addGameElement(board);
    }

}