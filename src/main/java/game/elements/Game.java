package game.elements;

import java.util.ArrayList;

/**
 * Created by kyle on 1/18/15.
 * Controls all aspects of the game
 */
public class Game {
    private ArrayList<HexImpl> allHexes;
    private boolean winner;
    private boolean finished;
    private Player currentPlayer;
    private int boardSize;
    private Player[] allPlayers = new Player[2];

    public Game (int boardSize, Player player1, Player player2){
        this.boardSize = boardSize;
        this.allPlayers[0] = player1;
        this.allPlayers[1] = player2;

    }

    public ArrayList<HexImpl> getAllHexes(){
        return allHexes;
    }

    public boolean isWinner(){
        return winner;
    }

    public boolean isFinished(){
        return finished;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }


}
